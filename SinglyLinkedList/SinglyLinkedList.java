package il.co.ilrd.SinglyLinkedList;

import java.util.NoSuchElementException;


class Node {
    Node next;
    int data;
}


public class SinglyLinkedList
{
    private Node head = new Node(null, null);
    
    //constructor
    SinglyLinkedList()
    {
    	this.head = null;
    }
    
    private class Node
    {
        private Node next;
        private Object data;
        
        //constructor
        Node(Object data, Node next)
        {
        	this.data = data;
        	this.next = next;
        }
    }
    

    private class ListIteratorImpl implements ListIterator
    {
        private Node current;
        
        //constructor
        ListIteratorImpl(Node node)
        {
        	this.current = node;
        }
        
        @Override
        public boolean hasNext()
        {
        	if(current == null)
        	{
        		return false;
        	}
        	if(current.next == null)
        	{
        		return false;
        	}
        	
        	return true;
        }
        
        
        @Override
        public Object next()
        {        	
        	if((current != null) && (current.next != null))
        	{
        		Object temp = current.data;
        		current = current.next;
        		return temp;
        	}
        	if(current != null && current.next == null)
        	{
        		return current.data;
        	}	
        throw new NoSuchElementException();
        }
        
        
        @Override
        public Object getElement()
        {
        	return current.data;
        }
        
        @Override
        public Object getNextElement()
        {
        	return current.next.data;
        }
    }
    
    
    public Object getNextElement(ListIterator iter)
    {
    	return iter.getNextElement();
    }
    
    public Object getElement(ListIterator iter)
    {
    	return iter.getElement();
    } 

    
    public ListIterator begin()
    {
    	return new ListIteratorImpl(this.head);
    }
    
    
    
    
    public void pushFront(Object data)
    {
    	Node node = new Node(data, head);
     	head = node;
    }
    
    
    
    
   public void printList()
   {
	  Node currNode = head;
	  
	  System.out.println("printing list");
	  while(currNode != null)
	  {
		  System.out.println(currNode.data);
		  currNode = currNode.next;
	  }
   }
    
    
    
   public Object popFront()
   {
	   if(isEmpty())
	   {
		   throw new NoSuchElementException();
	   }
	   
	   Object temp = head.data;
	   head = head.next;
	   
	   return temp;
   }
   
   
   
    public int size()
    {
    	Node currNode = head;
    	int count = 0;
    	
    	while(currNode != null)
    	{
    		count++;
    		currNode = currNode.next;
    	}
    	return count;	
    }
    
    
    
    public boolean isEmpty()
    {
    	if(size() == 0)
    	{
    		return true;
    	}
    	return false;
    }
    
    
    
    public ListIterator find(Object obj)
    {
    	Node currNode = head;
    	ListIteratorImpl iter = new ListIteratorImpl(currNode);
    	
    	while(currNode != null)
    	{
    		if(iter.next().equals(obj) == true)
    		{
    			iter.current = currNode;
    			return iter;
    		}
    		
    		currNode = currNode.next;
    	}
    	
    	throw new NoSuchElementException();
    }
}
