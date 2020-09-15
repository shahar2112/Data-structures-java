package il.co.ilrd.GenericLinkedList;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.omg.CORBA.PRIVATE_MEMBER;




public class GenericLinkedList<T> 
{
	
	private Node head;
	private int modCount;
	
	public GenericLinkedList() 
	{
		this.head = null;
		this.modCount = 0;
	}
	
	private class Node
	{
		T data;
		Node next;
		
		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	
	private class FailFastIteratorImpl implements FailFastIterator<T>
	{
		
		private Node current;
		private int expectedModCount;
		
		public FailFastIteratorImpl(Node node) 
		{
			this.current = node;
			this.expectedModCount = modCount;
		}
		
		@Override
		public boolean hasNext() 
		{
			if(current == null || current.next == null)
			{
				return false;
			}
			return true;
		}
		
		@Override
		public T next() 
		{
			Node temp_node;
			
			if(current == null)
			{
				throw new NoSuchElementException();
			}
			
			if(expectedModCount != GenericLinkedList.this.modCount)
			{
				throw new ConcurrentModificationException();
			}
			
			temp_node = current;
			
			current = current.next;
			return temp_node.data;
		}
		
		@Override
		public T getData()
		{
			return current.data;
		}
	}
	

	
	public void pushFront(T data)
	{
		Node newnode = new Node(data, head);
		
		head = newnode;
		
		++modCount;
		
	}
	
	
	public T popFront()
	{
		T temp = head.data;
		
	   if(isEmpty())
	   {
		   throw new NoSuchElementException();
	   }
		   
		head = head.next;
		
		++modCount;
		
		return temp;
	}
	
	
	public int size()
	{
		FailFastIterator<T> iterator = begin();
		int counter = 1;
		
		if(isEmpty())
		{
			return 0;
		}
		
		while(iterator.hasNext())
		{
			++counter;
			iterator.next();
		}
		
		return counter;
	}
	
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	
	public FailFastIterator<T> begin()
	{	
		return new FailFastIteratorImpl(head);
	}
	
	
	public FailFastIterator<T> find(T item)
	{
		FailFastIteratorImpl iterator = (GenericLinkedList<T>.FailFastIteratorImpl) begin();
		Node currNode = head;
		
		while(currNode != null)
		{
			if(iterator.next().equals(item))
			{
				iterator.current = currNode;
				return iterator;
			}
			
			currNode = currNode.next;
		}	
		
		iterator.next();
		return iterator;
	}
	
	
	
	public static <T> void print(GenericLinkedList<T> list)
	{
		GenericLinkedList<T>.FailFastIteratorImpl iterator = (GenericLinkedList<T>.FailFastIteratorImpl)list.begin();
		
		System.out.println("-->printing list<--");
		
		while(iterator.current != null)
		{
			System.out.println(iterator.getData());
			iterator.next();
		}
	}
	
	
	
	public static <T extends Comparable<T>> GenericLinkedList<T> sortMerge(GenericLinkedList<T> list1, GenericLinkedList<T> list2)
	{
		GenericLinkedList<T> merged_list = new GenericLinkedList<T>();
		
		GenericLinkedList<T>.FailFastIteratorImpl iterator1 = (GenericLinkedList<T>.FailFastIteratorImpl)list1.begin();
		GenericLinkedList<T>.FailFastIteratorImpl iterator2 = (GenericLinkedList<T>.FailFastIteratorImpl)list2.begin();
		
		while(iterator1.current != null && iterator2.current != null)
		{
			if(iterator1.getData().compareTo(iterator2.getData()) <= 0)
			{
				merged_list.pushFront(iterator1.getData());
				iterator1.next();
			}
			else
			{
				merged_list.pushFront(iterator2.getData());
				iterator2.next();
			}	
		}
		
		
		if(iterator1.current == null)
		{
			while(iterator2.current != null)
			{
				merged_list.pushFront(iterator2.getData());
				iterator2.next();
			}
		}
		
		if(iterator2.current == null)
		{
			while(iterator1.current != null)
			{
				merged_list.pushFront(iterator1.getData());
				iterator1.next();
			}
		}
		
		merged_list.flipList();
		
		return merged_list;
	}
	
	
	private void flipList()
	{
		Node prev_node = null;
		Node curr_node = head;
		Node next_node = head;
		
		while(curr_node != null)
		{
			next_node = curr_node.next;
			curr_node.next = prev_node;
			prev_node = curr_node;
			curr_node = next_node;
		}
		head = prev_node;
	}
	
}