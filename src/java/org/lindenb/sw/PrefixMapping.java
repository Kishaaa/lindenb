/**
 * 
 */
package org.lindenb.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.lindenb.sw.vocabulary.DC;
import org.lindenb.sw.vocabulary.FOAF;
import org.lindenb.sw.vocabulary.RDF;
import org.lindenb.sw.vocabulary.RDFS;

/**
 * @author pierre
 *
 */
public class PrefixMapping
{
private HashMap<String, String> uri2prefix= new HashMap<String, String>();

public PrefixMapping(boolean initialize)
	{
	if(initialize)
		{
		setNsPrefix("rdf", RDF.NS);
		setNsPrefix("rdfs", RDFS.NS);
		setNsPrefix("dc", DC.NS);
		setNsPrefix("foaf", FOAF.NS);
		}
	}

public PrefixMapping()
	{
	this(true);
	}

public void setNsPrefix(String prefix, String namespaceuri)
	{
	uri2prefix.put(namespaceuri,prefix);
	}
/**  Answer the prefix for the given URI, or null if there isn't one. */
public String getNsURIPrefix(java.lang.String uri) 
	{
	return this.uri2prefix.get(uri);
	}
/** Get the URI bound to a specific prefix, null if there isn't one.*/
public String getNsPrefixURI(String prefix) 
	{
	for(String ns: this.uri2prefix.keySet())
		{
		if(getNsURIPrefix(ns).equals(prefix))
			{
			return ns;
			}
		}
	return null;
	}



/**  Answer a qname with the expansion of the given uri, or null if no such qname can be constructed using the mapping's prefixes. */
public String qnameFor(java.lang.String uri)
	{
	if(uri==null) return null;
	int n= uri.lastIndexOf('#');
	if(n==-1) n= uri.lastIndexOf('/');
	if(n==-1) return null;
	String prefix= getNsURIPrefix(uri.substring(0,n+1));
	if(prefix==null) return null;
	return prefix+":"+uri.substring(n+1);
	}

/** Compress the URI using the prefix mappings if possible.*/
public String shortForm(java.lang.String uri)
	{
	String s= qnameFor(uri);
	return s==null?uri:s;
	}

public Set<String> getPrefixes()
	{
	HashSet<String> set= new HashSet<String>(this.uri2prefix.size());
	set.addAll(this.uri2prefix.values());
	return set;
	}

}
