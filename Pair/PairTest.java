package il.co.ilrd.Pair;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PairTest {
	
	Pair<String, String> testPair;
	
	@BeforeEach
	void before()
	{
		testPair = Pair.of("hello", "hi");
	}

	@Test
	void testMinMax() {
		
		Integer[] keys = {2, 4, 5, 1, 7, 9, 3};
		
		Comparator<Integer> comp = (Integer num1, Integer num2) -> num1.compareTo(num2);
		
		Pair<Integer, Integer> minmaxPair = Pair.minMax(keys, comp);
		
		assertEquals(1, minmaxPair.getKey());
		assertEquals(9, minmaxPair.getValue());
		assertNotEquals(0, minmaxPair.getKey());
	}

	@Test
	void testGetKey() {
		
		assertEquals("hello", testPair.getKey());
		assertNotEquals("hi", testPair.getKey());

	}

	@Test
	void testGetValue() {
		
		assertEquals("hi", testPair.getValue());
		assertNotEquals("hello", testPair.getValue());
	}

	@Test
	void testSetValue() {	
		
		String oldString = testPair.setValue("hola");
		assertEquals("hi", oldString);
		assertEquals("hola", testPair.getValue());
	}

	@Test
	void testEqualsObject() {
		Pair<String, String> testPair2 = Pair.of("hello", "hi");
		Pair<String, String> testPair3 = Pair.of("hi", "hi");
		
		assertTrue(testPair.equals(testPair2));
		assertFalse(testPair.equals(testPair3));
	}

	@Test
	void testToString() {
		
		assertEquals("k is: hello and v is: hi", testPair.toString());
		;
	}

}
