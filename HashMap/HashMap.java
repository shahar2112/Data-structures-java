package il.co.ilrd.HashMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.junit.runner.manipulation.Sortable;

import il.co.ilrd.Pair.Pair;
import java.util.List;
import java.sql.Time;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class HashMap<K, V> implements Map <K, V>
{
    private List<LinkedList<Map.Entry<K, V>>> table;
    public Values values;
    public Keys keys;
    public PairSet pairs;
    private int capacity;
    private int modcount;

    public HashMap(int capacity) 
    {
    	this.values = new Values();
    	this.keys = new Keys();
    	this.pairs = new PairSet();
    	this.capacity = capacity;
    	
    	table = new ArrayList<>(capacity);
    	
    	for(int i = 0; i < capacity; ++i)
		{
    		table.add(i, new LinkedList<Map.Entry<K, V>>());
		}
   	}
    
    
    /***************************************/
    
    class Values extends AbstractCollection<V>
    {
		@Override
		public Iterator<V> iterator() 
		{
			return new ValuesIterator();
		}

		@Override
		public int size() 
		{
			return HashMap.this.size();
		}
		
		class ValuesIterator implements Iterator<V>
		{
			private int bucket;
			private Iterator<Map.Entry<K, V>> iter;
			private int expectedModCount;
			
			ValuesIterator()
			{
				this.bucket = 0;
				this.expectedModCount = HashMap.this.modcount;
				this.iter = HashMap.this.table.get(0).iterator();
			}

			@Override
			public boolean hasNext() 
			{	
			
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						
						return true;
					}
					else
					{
						nextBucket();
					}
				}
				return false;
			}
			
			
			@Override
			public V next() 
			{
				concurrentExcCheck(this.expectedModCount);
				
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						
						return (iter.next()).getValue();
					}
					else
					{
						nextBucket();
					}
				}
				throw new NoSuchElementException();
			}
			
			private void nextBucket()
			{
				++bucket;
				if(bucket < HashMap.this.capacity)
				{
					iter = HashMap.this.table.get(bucket).iterator();					
				}
			}

		}
		
		
		@Override
		public void forEach(Consumer<? super V> action)
		{
			Iterator<V> iter = this.iterator();

			while(iter.hasNext())
			{
				action.accept(iter.next());	
			}
		}
    } 
    
    
    /***************************************/
    class Keys extends AbstractSet<K>
    {
		
		@Override
		public Iterator<K> iterator() 
		{
			return new KeysIterator();
		}

		@Override
		public int size()
		{
			return HashMap.this.size();
		}
		
		class KeysIterator implements Iterator<K>
		{
			private int bucket;
			private Iterator<Map.Entry<K, V>> iter;
			private int expectedModCount;
			
			KeysIterator() 
			{
				this.bucket = 0;
				this.expectedModCount = HashMap.this.modcount;
				this.iter = HashMap.this.table.get(0).iterator();
			}
			
			
			@Override
			public boolean hasNext()
			{		
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						return true;
					}
					else
					{
						nextBucket();
					}
				}
				return false;
			}
			
			
			@Override
			public K next() 
			{
				concurrentExcCheck(this.expectedModCount);
				
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						
						return ((Map.Entry<K, V>)iter.next()).getKey();
					}
					else
					{
						nextBucket();
					}
				}
				throw new NoSuchElementException();
			}
			
			
			
		    private void nextBucket()
			{
		    	++bucket;
				if(bucket < HashMap.this.capacity)
				{
					iter = HashMap.this.table.get(bucket).iterator();					
				}
			}  
			
		}
		
		
		@Override
		public void forEach(Consumer<? super K> action)
		{
			Iterator<K> iter = this.iterator();
			
			while(iter.hasNext())
			{
				action.accept(iter.next());	
			}
		}
    }
    
    
    /************************************/
    class PairSet extends AbstractSet<Map.Entry<K, V>>
    {
    	
		@Override
		public Iterator<Map.Entry<K, V>> iterator() 
		{
			return new PairIterator();
		}

		@Override
		public int size() 
		{
			return HashMap.this.size();
		}
		
		class PairIterator implements Iterator<Map.Entry<K, V>>
		{
			private int bucket;
			private Iterator<Map.Entry<K, V>> iter;
			private int expectedModCount;
			
			PairIterator()
			{
				this.bucket = 0;
				this.expectedModCount = HashMap.this.modcount;
				this.iter = HashMap.this.table.get(0).iterator();
			}
			
			@Override
			public boolean hasNext() 
			{
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						return true;
					}
					else
					{
						nextBucket();
					}
				}
				return false;
			}
			
			@Override
			public Map.Entry<K, V> next() 
			{
				concurrentExcCheck(this.expectedModCount);
				
				while(bucket < HashMap.this.capacity)
				{
					if(iter.hasNext())
					{
						
						return ((Map.Entry<K, V>)iter.next());
					}
					else
					{
						nextBucket();
					}
				}
				throw new NoSuchElementException();
			}
			
			private void nextBucket()
			{
				++bucket;
				if(bucket < HashMap.this.capacity)
				{
					iter = HashMap.this.table.get(bucket).iterator();					
				}
			}
		}
		
		@Override
		public void forEach(Consumer<? super Map.Entry<K, V>> action)
		{
			Iterator<Map.Entry<K, V>> iter = this.iterator();
			
			while(iter.hasNext())
			{
				action.accept(iter.next());	
			}
		}
		
    }
    
    /*****Utility methods******/
    
    private void concurrentExcCheck(int expectedModCount)
    {
    	if(HashMap.this.modcount != expectedModCount)
		{
			throw new ConcurrentModificationException();
		}
    }
	
    
    /*****************************************/
    
    //returns the number of key-value pair elements
	@Override
	public int size() 
	{
		int ctr = 0;
		
		for(LinkedList<Map.Entry<K, V>> buckets : table)
		{
			ctr+= buckets.size();
		}
				
		return ctr;
	}
	

    private static <K> int hashFunc(K key, int capacity)
    {
		return Math.abs(key.hashCode()%(capacity -1));
	}
	
	
	@Override
	public boolean isEmpty() 
	{
		return (size() == 0);
	}

	
	//checks if the key exist in map
	@Override
	public boolean containsKey(Object key) 
	{
		int bucket = hashFunc(key, capacity);
	
		for(Map.Entry<K, V> pair_set : this.table.get(bucket))
		{
			if(pair_set.getKey().equals(key))
			{
				return true;
			}
		}
		return false;
	}

	
	//checks if specific value exist in map
	@Override
	public boolean containsValue(Object value)
	{	
		Iterator<V> myIterator = this.values.iterator();
		
		while(myIterator.hasNext())
		{
			if(myIterator.next().equals(value))
			{
				return true;
			}
		}
		return false;
	}

	//returns the value of a specific key
	@Override
	public V get(Object key) 
	{
		int bucket = hashFunc(key, capacity);

		
		for(Map.Entry<K, V> pair_set : this.table.get(bucket))
		{
			if(pair_set.getKey().equals(key))
			{
				return pair_set.getValue();
			}
		}	
		return null;
	}

	//adds a pair to the map. 
	//If the key exist so change current value to the new one sent in method
	@Override
	public V put(K key, V value)
	{
		int bucket = hashFunc(key, capacity);
		
		++this.modcount;

		for(Map.Entry<K, V> pair_set : this.table.get(bucket))
		{
			if(pair_set.getKey().equals(key))
			{
				V to_return = pair_set.getValue();
				pair_set.setValue(value);
				return to_return;
			}
		}
		
		table.get(bucket).add(new Pair<K, V>(key, value));

		return null;
	}

	
	@Override
	public V remove(Object key) 
	{
		int bucket = hashFunc(key, capacity);
		V value = null;
		
		++this.modcount;
		
		for(Map.Entry<K, V> pair_set : this.table.get(bucket))
		{
			if(pair_set.getKey().equals(key))
			{
				value = pair_set.getValue();
				this.table.get(bucket).remove(pair_set);
				return value;
			}
		}
		return null;
	}

	
	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		for(Map.Entry<? extends K, ? extends V> pair_entry : m.entrySet())
		{
			K key = pair_entry.getKey();
			V value = pair_entry.getValue();
			
			this.put(key, value);
		}
	}
	

	@Override
	public void clear() 
	{
		for(LinkedList<Map.Entry<K, V>> buckets : table)
		{
			modcount += buckets.size();
			buckets.clear();
		}
	}

	
	@Override
	public Set<K> keySet() 
	{
		return this.keys;
	}
	

	@Override
	public Collection<V> values()
	{
		return this.values;
	}

	
	@Override
	public Set<Entry<K, V>> entrySet() 
	{
		return this.pairs;
	}

	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result + modcount;
		result = prime * result + ((pairs == null) ? 0 : pairs.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	
	//function checks if two HashMaps contains the same pairs and have the same size (capacity and order doesn't matter)
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (null == obj)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		@SuppressWarnings("rawtypes")
		HashMap other = (HashMap) obj;
		
		if (modcount != other.modcount)
		{
			return false;
		}
		Iterator<Entry<K, V>> pair_iter = this.pairs.iterator();
		
		while(pair_iter.hasNext())
		{
			Map.Entry<K, V> curr_pair = pair_iter.next();
			V curr_value = curr_pair.getValue();
			
			if(!other.containsKey(curr_pair.getKey()))
			{
				return false;
			}
			
			if(!other.get(curr_pair.getKey()).equals(curr_value))
			{
				return false;
			}
		}	
		return true;
	}
}



