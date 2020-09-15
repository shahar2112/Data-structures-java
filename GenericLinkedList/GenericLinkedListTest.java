package il.co.ilrd.GenericLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


class GenericLinkedListTest {

	public static void main(String[] args)
	{
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		GenericLinkedList<Integer> testlist2 = new GenericLinkedList<Integer>();
		GenericLinkedList<Integer> merged_list;
		
		System.out.println("is the list empty? (expected true)");
		System.out.println(testlist.isEmpty());
		System.out.println("what is the list size? (expected 0)");
		System.out.println(testlist.size());
		testlist.pushFront(5);

		System.out.println("is the list empty? (expected false)");
		System.out.println(testlist.isEmpty());
		System.out.println("what is the list size? (expected 1)");
		System.out.println(testlist.size());
		
		testlist.pushFront(3);
		testlist.pushFront(2);
		testlist2.pushFront(6);
		testlist2.pushFront(5);
		testlist2.pushFront(4);
		testlist2.pushFront(1);
		
		
		GenericLinkedList.print(testlist);
		GenericLinkedList.print(testlist2);
		
		merged_list = GenericLinkedList.sortMerge(testlist, testlist2);
		GenericLinkedList.print(merged_list);
		

	}
	


	@Test
	void testPopFront() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		testlist.pushFront(5);
		testlist.popFront();
		assertTrue(testlist.isEmpty());
		testlist.pushFront(3);
		testlist.pushFront(2);
		testlist.popFront();
		assertEquals(3, testlist.begin().getData());
		testlist.pushFront(4);
		testlist.pushFront(6);
		testlist.popFront();
		testlist.popFront();
		assertEquals(3, testlist.begin().getData());
	}

	@Test
	void testSize() 
	{

		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		assertEquals(0, testlist.size());
		testlist.pushFront(5);
		testlist.pushFront(5);
		assertEquals(2, testlist.size());
		testlist.popFront();
		assertEquals(1, testlist.size());
		testlist.popFront();
		assertEquals(0, testlist.size());
	}

	@Test
	void testPushFront() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		testlist.pushFront(5);
		assertEquals(5, testlist.begin().getData());
		testlist.pushFront(3);
		testlist.pushFront(2);
		
		assertEquals(2, testlist.begin().getData());
		
	}
	
	@Test
	void testIsEmpty() {
		
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		assertTrue(testlist.isEmpty());
		
		testlist.pushFront(5);
		assertFalse(testlist.isEmpty());
		
		testlist.pushFront(5);
		testlist.pushFront(5);
		testlist.popFront();
		testlist.popFront();
		testlist.popFront();
		assertTrue(testlist.isEmpty());
	}

	@Test
	void testBegin() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		
		testlist.pushFront(5);
		assertEquals(5, testlist.begin().getData());
		
		testlist.pushFront(2);
		assertEquals(2, testlist.begin().getData());
	}

	@Test
	void testFind() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		testlist.pushFront(5);
		testlist.pushFront(2);
		testlist.pushFront(3);
		
		assertEquals(2, testlist.find(2).getData());
		
		assertEquals(5, testlist.find(5).getData());
	}


	@Test
	void testSortMerge() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		GenericLinkedList<Integer> testlist2 = new GenericLinkedList<Integer>();
		GenericLinkedList<Integer> merged_list;
		
		testlist.pushFront(3);
		testlist.pushFront(2);
		testlist2.pushFront(6);
		testlist2.pushFront(5);
		testlist2.pushFront(4);
		testlist2.pushFront(1);
		
		merged_list = GenericLinkedList.sortMerge(testlist, testlist2);
		GenericLinkedList.print(merged_list);
		
		assertEquals(1, merged_list.begin().getData());
		assertEquals(6, merged_list.size());
		
		
	}
	
	
	@Test
	public void testNullException() 
	{
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			 testlist.popFront();
		    });
		
		testlist.pushFront(5);
		testlist.pushFront(2);
		testlist.pushFront(3);
		
		 assertThrows(NoSuchElementException.class, () -> {
			 testlist.find(1).getData();
		    });
	}
	

	@Test
	void testFailiterator() {
		
		GenericLinkedList<Integer> testlist = new GenericLinkedList<Integer>();
		
		testlist.pushFront(5);
		testlist.pushFront(2);
		FailFastIterator<Integer> iterator = testlist.begin();
		testlist.pushFront(3);
		
		 assertThrows(ConcurrentModificationException.class, () -> {
			 iterator.next();
		    });
		
	}
	

}
