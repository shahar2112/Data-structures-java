package il.co.ilrd.SinglyLinkedList;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

public class testSlist {
	
	public static void main(String[] args) 
	{
		SinglyLinkedList myList = new SinglyLinkedList();
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		Integer four = new Integer(4);
		
		myList.pushFront(one);
		myList.pushFront(two);
		myList.pushFront(three);
		myList.pushFront(four);
		
		myList.printList();
		
	}
	

@Test
public void testSize()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	
	assertEquals(0, myList.size());
	myList.pushFront(one);
	assertEquals(1, myList.size());
	myList.pushFront(one);
	assertEquals(2, myList.size());
	myList.pushFront(one);
	assertEquals(3, myList.size());
}


@Test
public void testIsEmpty()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	
	assertTrue(myList.isEmpty());
	myList.pushFront(one);
	assertFalse(myList.isEmpty());
	
	myList.popFront();
	assertTrue(myList.isEmpty());
}

@Test
public void testPushFront()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	Integer two = new Integer(2);
	Integer three = new Integer(3);

	myList.pushFront(one);
	assertEquals(one, myList.getElement(myList.begin()));
	myList.pushFront(two);
	assertEquals(two, myList.getElement(myList.begin()));
	myList.pushFront(three);
	assertEquals(three, myList.getElement(myList.begin()));
	
}

@Test
public void testPopFront()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	Integer two = new Integer(2);
	Integer three = new Integer(3);
	Integer four = new Integer(4);
	
	myList.pushFront(two);
	myList.popFront();
	assertTrue(myList.isEmpty());
	
	myList.pushFront(three);
	myList.pushFront(one);
	myList.popFront();
	assertEquals(three, myList.getElement(myList.begin()));
	
	myList.pushFront(one);
	myList.pushFront(two);
	myList.pushFront(three);
	myList.pushFront(four);
	myList.popFront();
	myList.popFront();
	myList.popFront();
	assertEquals(one, myList.getElement(myList.begin()));
	
}

@Test
public void testFind()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	Integer two = new Integer(2);
	Integer three = new Integer(3);
	
	myList.pushFront(one);
	myList.pushFront(two);
	myList.pushFront(three);
	
	assertEquals(one, myList.find(one).next());
	assertEquals(one, myList.find(one).next());
	assertEquals(two, myList.find(two).next());
	assertEquals(one, myList.find(one).next());
	assertEquals(three, myList.find(three).next());	
	
}





@Test(expected = NoSuchElementException.class)
public void testException()
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	
	myList.popFront(); //popping an empty list
	
	myList.find(one); //searching for element that doesn't exist

}


@Test(expected = NullPointerException.class)
public void testNullException() 
{
	SinglyLinkedList myList = new SinglyLinkedList();
	Integer one = new Integer(1);
	
	myList.pushFront(one);
	
	myList.getNextElement(myList.begin());
}


@Test
public void testHasNext()
{
	SinglyLinkedList myList = new SinglyLinkedList();

	Integer one = new Integer(1);
	Integer two = new Integer(2);
	
	assertFalse(myList.begin().hasNext());
	
	myList.pushFront(one);
	assertFalse(myList.begin().hasNext());
	
	myList.pushFront(two);
	assertTrue(myList.begin().hasNext());
	
	assertFalse(myList.find(one).hasNext());
	assertTrue(myList.find(two).hasNext());
	
	myList.popFront();
	assertFalse(myList.begin().hasNext());
}


@Test
public void testNext()
{
	SinglyLinkedList myList = new SinglyLinkedList();

	Integer one = new Integer(1);
	Integer two = new Integer(2);
	Integer three = new Integer(3);
	Integer four = new Integer(4);
	

	myList.pushFront(one);
	assertEquals(one, myList.begin().next());
	assertEquals(one, myList.begin().next());
	
	myList.pushFront(two);
	myList.pushFront(three);
	myList.pushFront(four);
	
	assertEquals(four, myList.begin().next());
	assertEquals(two, myList.find(two).next());
	assertEquals(two, myList.getNextElement(myList.find(three)));

}


}