package org.jointheleague.ecolban.immutablelist;

import java.util.NoSuchElementException;

import org.jointheleague.ecolban.immutablelist.EmptyList;
import org.jointheleague.ecolban.immutablelist.ImmutableList;
import org.jointheleague.ecolban.immutablelist.ListNode;
import org.junit.Test;

import junit.framework.TestCase;

public class ImmutableListTest extends TestCase {

    @Test
    public void testEmptyList() {
        ImmutableList<Integer> e = EmptyList.getInstance();
        assertTrue(e.isEmpty());
        assertEquals(e, e.remove(1));
        assertFalse(e.push(1).isEmpty());
        assertFalse(e.append(1).isEmpty());
        assertEquals(0, e.length());
        assertEquals("()", e.toString());
        try {
            e.head();
            fail("EmptyList.head() should throw a NoSuchElementException");
        } catch (NoSuchElementException exc) {
            assertEquals("EmptyList has no head.", exc.getMessage());
        } catch (Exception exc) {
            fail("EmptyList.head() should throw a NoSuchElementException");
        }
        try {
            e.tail();
            fail("EmptyList.tail() should throw a NoSuchElementException");
        } catch (NoSuchElementException exc) {
            assertEquals("EmptyList has no tail.", exc.getMessage());
        } catch (Exception exc) {
            fail("EmptyList.tail() should throw a NoSuchElementException");
        }

        assertTrue(e.equals(EmptyList.getInstance()));
    }

    @Test
    public void testListNodeConstructor() {
        ImmutableList<Integer> lst1 = EmptyList.getInstance();
        ImmutableList<Integer> lst2 = new ListNode<Integer>(1, lst1);
        assertEquals("(1)", lst2.toString());
        try {
            new ListNode<Integer>(1, null);
            fail("Calling the ListNode constructor with a null tail should throw an IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals("Tail cannot be null.", e.getMessage());
        } catch (Exception e) {
            fail("Calling the ListNode constructor with a null tail should throw an IllegalArgumentException.");
        }
    }

    @Test
    public void testListNodeWithInteger() {
        ImmutableList<Integer> lst0 = EmptyList.getInstance();
        ImmutableList<Integer> lst1 = lst0.push(1);
        ImmutableList<Integer> lst2 = lst1.append(2);
        ImmutableList<Integer> lst3 = lst2.append(3);
        ImmutableList<Integer> lst4 = lst3.remove(2);
        ImmutableList<Integer> lst5 = lst4.push(0);

        assertTrue(lst0.isEmpty());
        assertTrue(!lst1.isEmpty());
        assertTrue(!lst2.isEmpty());
        assertTrue(!lst3.isEmpty());
        assertTrue(!lst4.isEmpty());
        assertEquals(1, lst1.length());
        assertEquals(2, lst2.length());
        assertEquals(3, lst3.length());
        assertEquals(2, lst4.length());
        assertEquals("()", lst0.toString());
        assertEquals("(1)", lst1.toString());
        assertEquals("(1 2)", lst2.toString());
        assertEquals("(1 2 3)", lst3.toString());
        assertEquals("(1 3)", lst4.toString());
        assertEquals("(0 1 3)", lst5.toString());
        assertEquals(lst4.tail(), lst3.tail().tail());
        assertEquals(lst4, lst5.tail());
        assertEquals(Integer.valueOf(1), lst1.head());
        assertEquals(Integer.valueOf(0), lst5.head());
        assertTrue(lst1.tail().isEmpty());
        assertTrue(lst5.tail().tail().tail().isEmpty());

    }

    @Test
    public void testListNodeWithString() {
        ImmutableList<String> lst0 = EmptyList.getInstance();
        ImmutableList<String> lst1 = lst0.push("two");
        ImmutableList<String> lst2 = lst1.push("one");
        ImmutableList<String> lst3 = lst2.append("three");
        ImmutableList<String> lst4 = lst3.remove("two");
        ImmutableList<String> lst5 = lst4.push("zero");
        ImmutableList<String> lst6 = lst3.append("four").append("four").append("five");
        ImmutableList<String> lst7 = lst6.remove("four").remove("five");

        assertTrue(lst0.isEmpty());
        assertTrue(!lst1.isEmpty());
        assertTrue(!lst2.isEmpty());
        assertTrue(!lst3.isEmpty());
        assertTrue(!lst4.isEmpty());
        assertEquals(1, lst1.length());
        assertEquals(2, lst2.length());
        assertEquals(3, lst3.length());
        assertEquals(2, lst4.length());
        assertEquals(6, lst6.length());
        assertEquals("()", lst0.toString());
        assertEquals("(two)", lst1.toString());
        assertEquals("(one two)", lst2.toString());
        assertEquals("(one two three)", lst3.toString());
        assertEquals("(one three)", lst4.toString());
        assertEquals("(zero one three)", lst5.toString());
        assertEquals("(one two three four four five)", lst6.toString());
        assertEquals("(one two three)", lst7.toString());
        assertEquals("two", lst1.head());
        assertEquals("zero", lst5.head());
        assertNotSame(lst3, lst7);
        assertEquals(lst3, lst7);
        assertEquals(lst4.tail(), lst3.tail().tail());
        assertEquals(lst4, lst5.tail());
        assertFalse(lst2.equals(lst3));
        assertTrue(lst1.tail().isEmpty());
        assertTrue(lst5.tail().tail().tail().isEmpty());
        assertSame(lst0.push("one").remove("one"), lst0);
    }

    @Test
    public void testListNodeWithNull() {
    	ImmutableList<Integer> lst1 = EmptyList.<Integer>getInstance().push(3).push(2).push(1);
        ImmutableList<Integer> lst2 = lst1.push(null);
        ImmutableList<Integer> lst3 = lst1.push(null);
        ImmutableList<Integer> lst4 = lst1.push(0);
        ImmutableList<Integer> lst5 = lst2.append(null);
        ImmutableList<Integer> lst6 = lst5.remove(null);
        assertEquals("(1 2 3)", lst1.toString());
        assertEquals("(null 1 2 3)", lst2.toString());
        assertEquals("(null 1 2 3)", lst3.toString());
        assertEquals("(0 1 2 3)", lst4.toString());
        assertEquals("(null 1 2 3 null)", lst5.toString());
        assertEquals("(1 2 3)", lst6.toString());
        assertTrue(lst2.equals(lst3));
        assertFalse(lst2.equals(lst4));
        assertTrue(lst6.equals(lst1));
        assertFalse(lst2 == lst3);
        assertTrue(lst2.tail() == lst3.remove(null));
        assertTrue(lst2.tail() == lst4.remove(0));

    }
    
    @Test
    public void testDifferentTypes() {
    	ImmutableList<Object> lst1 = EmptyList.<Object>getInstance().push("3").push("2").push("1");
    	ImmutableList<String> lst2 = EmptyList.<String>getInstance().push("3").push("2").push("1");
    	ImmutableList<Integer> lst3 = EmptyList.<Integer>getInstance().push(3).push(2).push(1);
    	ImmutableList<Integer> lst4 = EmptyList.<Integer>getInstance();
    	ImmutableList<String> lst5 = EmptyList.<String>getInstance();
    	assertTrue(lst1.equals(lst2));
    	assertTrue(lst2.equals(lst1));
    	assertFalse(lst1.equals(lst3));
    	assertFalse(lst3.equals(lst1));
    	assertTrue(lst4.equals(lst5));
    }
    
    @Test
    public void testHashcode() {
    	ImmutableList<String> lst0 = EmptyList.<String>getInstance();
    	ImmutableList<String> lst1 = EmptyList.<String>getInstance().push(null).push(null).push(null);
    	ImmutableList<String> lst2 = EmptyList.<String>getInstance().push("3").push("2").push("1");
    	ImmutableList<String> lst3 = EmptyList.<String>getInstance().push("3").push("2").push("1");
    	ImmutableList<String> lst4 = EmptyList.<String>getInstance().push("3").push("2").push("2");
    	try {
    		int code = lst2.hashCode();
    		assertFalse(code == lst0.hashCode());
    		assertFalse(code == lst1.hashCode());
    		assertTrue(code == lst2.hashCode());
    		assertTrue(code == lst3.hashCode());
    		assertFalse(code == lst4.hashCode());
    	} catch(Exception e) {
    		fail("Exception thrown while computing hascode.");
    	}
    }

}
