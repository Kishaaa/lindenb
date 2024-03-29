package org.lindenb.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lexer
 * @author pierre
 *
 */
public class Lexer
	{
	public static final int EOF=-1;
	private StringBuilder buffer=new StringBuilder();
	private Reader in;
	
	/** constructor */
	public Lexer(Reader r)
		{
		this.in=r;
		}
	/** constructor */
	public Lexer(InputStream r)
		{
		this.in=new InputStreamReader(r);
		}
	
	/** @returns the internal reader */
	protected Reader getReader()
		{
		return this.in;
		}
	
	/** @returns the internal buffer */
	protected StringBuilder getBuffer()
		{
		return this.buffer;
		}
	
	/** consume all whitespaces.
	 * @throws an IOException if there was no whitespace
	 * @return the number of blanks read
	 **/
	public int mustWhitespaces()throws IOException
		{
		int c=0;
		while(!this.isEof() &&
			  Character.isWhitespace(this.get()))
			{
			c++;
			this.consumme(1);
			}
		if(c==0) throw new IOException("Error expected whitespace before "+toString());
		return c;
		}
	
	public int skipWhitespaces() throws IOException
		{
		int c;
		while((c=get())!=EOF &&
		   Character.isWhitespace(c))
			{
			consumme(1);
			}
		return get();
		}
	
	/** return the next char without removing it from the stack */
	public int get() throws IOException
		{
		return get(0);
		}
	
	/** return the index-th char without removing it from the stack */
	public int get(int index) throws IOException
		{
		while(this.buffer.length()<=index)
			{
			int c= this.in.read();
			if(c==EOF) return EOF;
			this.buffer.append((char)c);
			}
		return this.buffer.charAt(index);
		}
	
	/** pop and returns the next element in the stack */
	public int pop() throws IOException
		{
		if(this.buffer.length()!=0)
			{
			char c=this.buffer.charAt(0);
			this.buffer.deleteCharAt(0);
			return c;
			}
		return this.in.read();
		}
	
	public boolean inAvail(String s) throws IOException
		{
		return inAvail(0,s);
		}

	
	public boolean inAvail(int index,String s) throws IOException
		{
		for(int i=0;i< s.length();++i)
			{
			if(get(index+i)!=s.charAt(i)) return false;
			}
		return true;
		}
	
	/**
	 * scan for longest string matching the following regular expression
	 * @param index start index
	 * @param pattern
	 * @return null or the longest string match the regex
	 * @throws IOException
	 */
	public String regex(int index,Pattern pattern) throws IOException
		{
		//thank you SO http://stackoverflow.com/questions/2526756
		String return_value=null;
		StringBuilder b=new StringBuilder();
		int c;
		Matcher m=null;
		while((c=get( index + b.length() ))!=EOF)
			{
			b.append((char)c);
			m= pattern.matcher(b);
			if(m.matches())
				{
				return_value=b.toString();
				continue;//try to extend
				}
			if(!m.hitEnd()) break;
 			}
		return return_value;
		}
	

	/**
	 * scan for longest string matching the following regular expression
	 * @param pattern
	 * @return
	 * @throws IOException
	 */
	public String regex(Pattern pattern) throws IOException
		{
		return regex(0,pattern);
		}
	
	public int consumme(final int index,final int n) throws IOException
		{
		int n_read=0;
		while(	
				this.buffer.length()<index
				)
			{
			int c= this.in.read();
			if(c==EOF) return 0;
			this.buffer.append((char)c);
			}
		while(	n_read<n &&
				this.buffer.length()>index
				)
			{
			n_read++;
			this.buffer.deleteCharAt(index);
			}
		while(n_read<n)
			{
			if(this.in.read()==-1) break;
			n_read++;
			}
		return n_read;
		}
	
	public int consumme(final int n) throws IOException
		{
		return consumme(0,n);
		}
	
	public boolean isEof() throws IOException
		{
		return get()==EOF;
		}
	
	public String toString(int length)
		{
		StringBuilder b= new StringBuilder(length);
		try
			{
			for(int i=0;i< length && get(i)!=-1;++i)
				{
				b.append((char)get(i));
				}
			}
		catch (Exception e)
			{
			//ignore
			}
		return b.toString().replace("\n", "\\n");
		}
	
	@Override
	public int hashCode()
		{
		return -1;
		}
	
	@Override
	public boolean equals(Object obj)
		{
		return obj==this;
		}
	
	@Override
	public String toString()
		{
		return toString(30);
		}
	
	
	public static void main(String[] args) throws Exception
		{
		StringReader r= new StringReader("012345AAA6789");
		Lexer l=new Lexer(r);
		l.consumme(3);
		System.err.println("Result:"+l.toString());
		}
	
	}
