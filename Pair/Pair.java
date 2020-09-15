package il.co.ilrd.Pair;

import java.util.Comparator;
import java.util.Map;


public class Pair<K, V> implements Map.Entry<K, V>
{
    private K k;
    private V v;

    public Pair(K k, V v)
    {
    	this.k = k;
    	this.v = v;
    }

    public static <K, V> Pair<K, V> of(K k, V v)
    {
    	return new Pair<K, V>(k, v);
    }
    
    //return a pair when key = min and value = max
    public static <K> Pair<K, K> minMax(K[] keys, Comparator<K> comp)
    {
    	if(null == keys)
    	{
    		return null;
    	}
    	
    	K max = keys[0];
    	K min = keys[0];
    	
    	for(K element : keys)
    	{
    		if(comp.compare(element, max) > 0)
    		{
    			max = element;
    		}
    		else if(comp.compare(element, min) < 0)
    		{
				min = element;
			}
    	}
    	
    	return of(min, max);
    }

    @Override
    public K getKey()
    {
    	return this.k;
    }
    
    @Override
    public V getValue()
    {
    	return this.v;
    }
    
    @Override
    public V setValue(V v)
    {
    	V tempV = this.v;
    	this.v = v;
    	
    	return tempV;
    }

    
    @Override
    public boolean equals(Object obj)
    {
    	if(this == obj)
    	{
    		return true;
    	}
    	
    	if(null == obj)
    	{
    		return false;
    	}
    	
    	if(this.getClass() != obj.getClass())
		{
    		return false;
		}
    	
    	Pair<?, ?> obj_pair_ref = ((Pair<?, ?>)obj);
    	
    	if(this.k == null && obj_pair_ref.k != null)
    	{
    		return false;
    	}
    	
    	if(this.v == null && obj_pair_ref.v != null)
    	{
    		return false;
    	}
    	
    	return (this.k.equals(obj_pair_ref.k) && this.v.equals(obj_pair_ref.v));	
    }
    
    
    @Override
    public int hashCode()
    {
    	return this.k.hashCode()*this.v.hashCode();
    }
    
    
    @Override
    public String toString()
    {
    	return ("k is: " + this.k + " and v is: " + this.v);
    }
}




