package il.co.ilrd.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HashMapTest {

	HashMap<Integer, String> testmap;
	
	@BeforeEach
	void before()
	{
		testmap = new HashMap<Integer, String>(50);
	}
	
	@Test
	void testPut() 
	{
		testmap.put(1000, "Shahar");
		assertEquals("Shahar", testmap.get(1000));
		assertTrue(testmap.containsKey(1000));
		assertTrue(testmap.containsValue("Shahar"));
		testmap.put(1000, "Liri");
		assertFalse(testmap.containsValue("Shahar"));
		testmap.put(2000, "Lola");
		assertNotEquals("Shahar", testmap.get(1000));
	}
	
	@Test
	void testSize() 
	{
		assertEquals(0, testmap.size());
		testmap.put(1000, "Shahar");
		assertEquals(1, testmap.size());
		testmap.remove(1000);
		assertEquals(0, testmap.size());
		testmap.put(1000, "Shosh");
		testmap.put(1000, "orel");
		testmap.put(1000, "nesh");
		assertEquals(1, testmap.size());
		testmap.put(2000, "orel");
		testmap.put(3000, "nesh");
		assertEquals(3, testmap.size());
	}

	@Test
	void testIsEmpty() 
	{	
		assertTrue(testmap.isEmpty());
		testmap.put(1000, "Shir");
		assertFalse(testmap.isEmpty());
	}

	@Test
	void testContainsKey() 
	{
		testmap.put(1000, "Shani");
		assertTrue(testmap.containsKey(1000));
	}
	

	@Test
	void testContainsValue() 
	{
		testmap = new HashMap<Integer, String>(50);
		
		testmap.put(1000, "Shani");
		assertTrue(testmap.containsValue("Shani"));
		assertFalse(testmap.containsValue("shosho"));
		testmap.put(1000, "Lilach");
		assertTrue(testmap.containsValue("Lilach"));
		testmap.put(3000, "libi");
		assertTrue(testmap.containsValue("libi"));
		testmap.remove(1000);
		assertFalse(testmap.containsValue("Lilach"));
	}

	@Test
	void testGet() 
	{
		testmap = new HashMap<Integer, String>(50);
		
		testmap.put(1000, "Shani");
		assertEquals("Shani", testmap.get(1000));
		testmap.put(2000, "shipi");
		assertNotEquals("shipi", testmap.get(1000));
		assertEquals("shipi", testmap.get(2000));
	}



	@Test
	void testRemove() 
	{
		testmap.put(1000, "Shaniki");
		testmap.remove(1000);
		assertFalse(testmap.containsKey(1000));
	}

	@Test
	void testPutAll()
	{
		HashMap<Number, String> newtestmap = new HashMap<Number, String>(50);
		HashMap<Integer, String> sectestmap  = new HashMap<Integer, String>(5);
		
		newtestmap.put(123.5, "todobom");
		newtestmap.put(1000, "roy");
		newtestmap.put(13.5, "baby");
		
		sectestmap.put(1000, "Lulu");
		sectestmap.put(2000, "Loren");
		sectestmap.put(3000, "Noa");
		sectestmap.put(4000, "Rona");
		
		newtestmap.putAll(sectestmap);
		
		assertEquals("Noa", newtestmap.get(3000));
		assertEquals(6, newtestmap.size());
		assertEquals("Lulu", newtestmap.get(1000));
		
	}

	@Test
	void testClear() 
	{
		testmap.put(1000, "Shahar");
		testmap.put(2000, "robert");
		testmap.put(6000, "wili");
		testmap.put(5000, "wonka");
		testmap.clear();
		assertFalse(testmap.containsKey(1000));
		assertEquals(0, testmap.size());
	}

	@Test
	void testKeySet() 
	{
		testmap.put(1000, "Tal");
		testmap.put(2000, "Robert");
		testmap.put(6000, "Lili");
		testmap.put(5000, "Refael");
		
		System.out.println();
		System.out.println("*******Test keySet**********");
		System.out.println(testmap.keySet());
	}

	@Test
	void testValues() 
	{
		testmap.put(1000, "Shahar");
		testmap.put(2000, "robert");
		testmap.put(6000, "Liran");
		testmap.put(5000, "Tal");
		
		System.out.println();
		System.out.println("*******Test Values**********");
		System.out.println(testmap.values());
	}

	@Test
	void testEntrySet()
	{
		testmap.put(1000, "Shosh");
		testmap.put(2000, "Robert");
		testmap.put(6000, "Wili");
		testmap.put(5000, "Liroy");
		
		System.out.println();
		System.out.println("*******Test EntrySet********");
		System.out.println(testmap.entrySet());
	}
	
	@Test
	void testEquals() 
	{
		HashMap<Number, String> newtestmap = new HashMap<Number, String>(5);
		HashMap<Integer, String> sectestmap  = new HashMap<Integer, String>(50);
		
		newtestmap.put(1000, "Todobom");
		newtestmap.put(2000, "Roy");
		newtestmap.put(3000, "baby");
		
		sectestmap.put(1000, "Todobom");
		sectestmap.put(2000, "Roy");
		sectestmap.put(3000, "baby");
		
		assertTrue(newtestmap.equals(sectestmap));
		
		sectestmap.put(4000, "baby");
		assertFalse(newtestmap.equals(sectestmap));

	}
	
	@Test
	void testforEach() 
	{		
		testmap.put(1000, "Todobom");
		testmap.put(2000, "Roy");
		testmap.put(3000, "Liora");
		
		System.out.println();
		System.out.println("********Test ForEach values**********");
		testmap.values().forEach(e->System.out.println(e));
		
		System.out.println();
		System.out.println("********Test ForEach keys**********");
		testmap.keySet().forEach(e->System.out.println(e + 5));
		
		System.out.println();
		System.out.println("********Test ForEach pairs**********");
		testmap.entrySet().forEach(e->System.out.println(e + " testing forEach :)"));
	}

}
