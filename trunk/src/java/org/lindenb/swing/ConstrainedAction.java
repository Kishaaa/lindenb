/**
 * 
 */
package org.lindenb.swing;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.JTextComponent;

import org.lindenb.util.Pair;

/**
 * @author pierre
 *
 */
public abstract class ConstrainedAction<T> extends ObjectAction<T>
	{
	private Vector<Validator1<?>> validators= new Vector<Validator1<?>>(1,1);
	
	/**
	 * @param object
	 */
	public ConstrainedAction(T object)
		{
		super(object);
		}

	/**
	 * @param object
	 * @param name
	 */
	public ConstrainedAction(T object, String name)
		{
		super(object, name);
		}

	/**
	 * @param object
	 * @param name
	 * @param icon
	 */
	public ConstrainedAction(T object, String name, Icon icon)
		{
		super(object, name, icon);
		}

	
	private static abstract class Validator1<X extends JComponent>
		{
		X component;
		Validator1(X component)
			{
			this.component=component;
			}
		abstract String getErrorMessage();
		}
	
	private static abstract class Validator2<X extends JComponent,Y> extends  Validator1<X>
		{
		Y param;
		Validator2(X component,Y param)
			{
			super(component);
			this.param=param;
			}
		}
	
	
	
	
	public void mustMatchPattern(JTextComponent component,Pattern pattern)
		{
		addTextValidator(new Validator2<JTextComponent,Pattern>(component,pattern)
			{
			String getErrorMessage()
				{
				return (param.matcher(component.getText()).matches()?
					   "Doesn\'t match "+param.pattern():null);
				}
			});
		}
	
	public void mustBeARegexPattern(JTextComponent component)
		{
		addTextValidator(new Validator1<JTextComponent>(component)
			{
			String getErrorMessage()
				{
				try {
					Pattern.compile(component.getText());
					return null;
				} catch (PatternSyntaxException e) {
					return e.getMessage();
				}
				}
			});
		}
	
	public void mustNotEmpty(JTextComponent component)
		{
		addTextValidator(new Validator1<JTextComponent>(component)
			{
			String getErrorMessage()
				{
				return this.component.getText().trim().length()==0?"Empty Field":null;
				}
			});
		}
	
	public void mustBeInteger(JTextComponent component) {mustBeAClass(component, Integer.class);}
	public void mustBeShort(JTextComponent component) {mustBeAClass(component, Short.class);}
	public void mustBeLong(JTextComponent component) {mustBeAClass(component, Long.class);}
	public void mustBeFloat(JTextComponent component) {mustBeAClass(component, Float.class);}
	public void mustBeDouble(JTextComponent component) {mustBeAClass(component, Double.class);}
	public void mustBeURL(JTextComponent component) {mustBeAClass(component, URL.class);}
	public void mustBeURI(JTextComponent component) {mustBeAClass(component, URI.class);}
	
	public void mustBeAClass(JTextComponent component,Class<?> clazz)
		{
		addTextValidator(new Validator2<JTextComponent,Class<?> >(component,clazz)
			{
			@Override
			String getErrorMessage()
				{
				try {
					Constructor<?> cstor = this.param.getConstructor(String.class);
					cstor.newInstance(this.component.getText());
					return null;
					}
				catch (Exception e)
					{
					return e.getMessage();
					}
				}
			});
		}
	
	
	
	public void mustHaveRows(JTable table)
		{
		mustHaveRows(table,1,Integer.MAX_VALUE);
		}
	
	public void mustHaveRows(JTable table,int rowCount)
		{
		mustHaveRows(table,rowCount,rowCount+1);
		}
	
	public void mustHaveRows(JTable table,int minInclusive,int maxExclusive)
		{
		table.getModel().addTableModelListener(new TableModelListener()
			{
			@Override
			public void tableChanged(TableModelEvent e) {
				validate();
				}
			});
		
		addValidator(new Validator2<JTable,Pair<Integer, Integer>>(table,new Pair<Integer, Integer>(minInclusive,maxExclusive))
			{
			@Override
			String getErrorMessage()
				{
				int n= this.component.getModel().getRowCount();
				return n>= this.param.first() && n< this.param.second()?null:"Illegale Number of Selected Rows";
				}
			});
		}

	
	
	
	public void mustBeSelected(JTable table)
		{
		mustBeSelected(table,1,Integer.MAX_VALUE);
		}
	
	public void mustBeSelected(JTable table,int rowCount)
		{
		mustBeSelected(table,rowCount,rowCount+1);
		}
	
	public void mustBeSelected(JTable table,int minInclusive,int maxExclusive)
		{
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				validate();
				}
			});
		
		addValidator(new Validator2<JTable,Pair<Integer, Integer>>(table,new Pair<Integer, Integer>(minInclusive,maxExclusive))
			{
			@Override
			String getErrorMessage()
				{
				int n= this.component.getSelectedRowCount();
				return n>= this.param.first() && n< this.param.second()?null:"Illegale Number of Selected Rows";
				}
			});
		}

	
	protected Validator1<?> addTextValidator(Validator1<JTextComponent> v)
		{
		v.component.getDocument().addDocumentListener(new DocumentListener()
			{
			@Override
			public void changedUpdate(DocumentEvent e) {
				validate();
				}
			@Override
			public void insertUpdate(DocumentEvent e) {
				validate();
				}
			@Override
			public void removeUpdate(DocumentEvent e) {
				validate();
				}
			});
		return addValidator(v);
		}
	
	protected Validator1<?> addValidator(Validator1<?> v)
		{
		this.validators.addElement(v);
		validate();
		return v;
		}
	
	public String getErrorMessage()
		{
		String msg=null;
		for(Validator1<?> v:this.validators)
			{
			msg = v.getErrorMessage();
			if(msg!=null) break;
			}
		return msg;
		}
	
	protected void validate()
		{
		setEnabled(getErrorMessage()==null);
		}
	}
