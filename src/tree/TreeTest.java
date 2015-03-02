package tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tree API assignment for CIT594, Spring 2015.
 * @author James Park
 *
 */
public class TreeTest {
	
	Tree<Integer> child1;
	Tree<Integer> child2;
	Tree<Integer> child3;
	Tree<Integer> child4;
	Tree<Integer> child5;
	Tree<Integer> child6;
	Tree<Integer> child7;
	Tree<Integer> child8;
	
	Tree<Integer> tree1;
	Tree<Integer> tree2;
	Tree<String> tree3;
	Tree<String> tree4;
	
	@Before
    public void setUp(){
		child1 = new Tree<Integer>(5);
		child2 = new Tree<Integer>(10);
		child3 = new Tree<Integer>(15);
		child4 = new Tree<Integer>(20);
		child5 = new Tree<Integer>(25);
		child6 = new Tree<Integer>(30);
		child7 = new Tree<Integer>(35);
		child8 = new Tree<Integer>(40);
		
		tree1 = new Tree<Integer>(1, child1, child2);
	}
	
	@Test
	public void testHashCode() {
		child1.addChild(child3);
		child5.setValue(5); 
		child6.setValue(10);
		child7.setValue(15);
		tree2 = new Tree<Integer>(1, child5, child6);
		child6.addChild(child7);
		Tree<String>child9 = new Tree<String>("two");
		tree3 = new Tree<String>("one", child9);
		assertTrue(tree1.hashCode() == tree2.hashCode());
		assertFalse(tree3.hashCode() == tree1.hashCode());
	}
	
	@Test
	public void testTree() {
		assertTrue(tree1.getValue() == 1);
		assertTrue(tree1.contains(child1));
		assertTrue(tree1.contains(child2));
	}
	
	@Test
	public void testSetValue() {
		assertTrue(tree1.getValue() == 1);
		tree1.setValue(2);
		assertTrue(tree1.getValue() == 2);
	}

	@Test
	public void testGetValue() {
		assertTrue(tree1.getValue() == 1);
	}

	@Test
	public void testAddChildIntTreeOfV() {
		assertTrue(tree1.getNumberOfChildren() == 2);
		tree1.addChild(1, child3);
		assertTrue(tree1.getNumberOfChildren() == 3);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddChildIntIllegalArgumentException() {
		tree1.addChild(1, child1);
	}
	
	@Test
	public void testAddChildTreeOfV() {
		assertTrue(tree1.getNumberOfChildren() == 2);
		tree1.addChild(child3);
		assertTrue(tree1.getNumberOfChildren() == 3);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddChildIllegalArgumentException() {
		tree1.addChild(child1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddChildren() {
		assertTrue(tree1.getNumberOfChildren() == 2);
		tree1.addChildren(child3, child4, child5);
		assertTrue(tree1.getNumberOfChildren() == 5);
	}
	
	@SuppressWarnings("unchecked")
	@Test (expected = IllegalArgumentException.class)
	public void testAddChildrenIllegalArgumentException() {
		tree1.addChildren(child1, child2);
	}

	@Test
	public void testGetNumberOfChildren() {
		assertTrue(tree1.getNumberOfChildren() == 2);
		tree2 = new Tree<Integer>(3, child3, child4);
		assertTrue(tree2.getNumberOfChildren() == 2);
		assertFalse(tree2.getNumberOfChildren() == 3);
		tree2.addChild(child5);
		assertTrue(tree2.getNumberOfChildren() == 3);
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetChild() {
		assertTrue(tree1.getChild(0).equals(child1));
		assertTrue(tree1.getChild(1).equals(child2));
		tree1.addChildren(child3, child4, child5);
		assertTrue(tree1.getChild(2).equals(child3));
		assertTrue(tree1.getChild(3).equals(child4));
		assertTrue(tree1.getChild(4).equals(child5));
		tree1.addChild(child6);
		assertTrue(tree1.getChild(5).equals(child6));
		tree1.addChild(1, child7);
		assertTrue(tree1.getChild(0).equals(child1));
		assertTrue(tree1.getChild(1).equals(child7));
		assertTrue(tree1.getChild(2).equals(child2));
		assertTrue(tree1.getChild(3).equals(child3));
		assertTrue(tree1.getChild(4).equals(child4));
		assertTrue(tree1.getChild(5).equals(child5));
		assertTrue(tree1.getChild(6).equals(child6));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetChildIndexOutOfBoundsException() {
		tree1.getChild(200);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetChildIndexOutOfBoundsException2() {
		tree1.getChild(-2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testIterator() {
		tree1.addChildren(child3, child4);
		Iterator<Tree<Integer>> iterator = tree1.iterator();
		assertTrue(iterator.hasNext());
		for(int i = 0; i < tree1.getNumberOfChildren(); i++){
			assertTrue(iterator.hasNext());
			assertTrue(iterator.next().getValue() == tree1.getChild(i).getValue());
		}
	}

	@Test
	public void testContains() {
		Tree<Integer> child100 = new Tree<Integer>(5);
		assertTrue(tree1.contains(child1));
		assertTrue(tree1.contains(child2));
		assertTrue(tree1.getNumberOfChildren() == 2);
		assertFalse(tree1.getNumberOfChildren() != 2);
		assertTrue(child1.getNumberOfChildren() == 0);
		assertTrue(child2.getNumberOfChildren() == 0);
		
		assertFalse(tree1.contains(child100));
		assertFalse(tree1.contains(child3));
		child1.addChild(child3);
		assertTrue(child1.getNumberOfChildren() == 1);
		assertTrue(child1.contains(child3));
		assertTrue(tree1.contains(child3));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testToString() {
		Tree<String> child_2 = new Tree<String>("two");
		Tree<String> child_3 = new Tree<String>("three");
		Tree<String> child_4 = new Tree<String>("four");
		Tree<String> child_5 = new Tree<String>("five");
		Tree<String> child_6 = new Tree<String>("six");
		Tree<String> child_7 = new Tree<String>("seven");
		Tree<String> child_8 = new Tree<String>("eight");
		tree3 = new Tree<String>("one", child_2, child_3);
		child_3.addChildren(child_4, child_5);
		child_5.addChildren(child_6, child_7, child_8);
		assertTrue(tree3.toString().equals(" one ( two three ( four five ( six seven eight ) ) )"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEqualsObject() {
		child5.setValue(5);
		child6.setValue(10);
		child7.setValue(15);
		child8.setValue(20);
		tree1.addChildren(child3, child4);
		tree2 = new Tree<Integer>(1, child5, child6, child7, child8);
		assertTrue(tree1.equals(tree2));
	}

	@Test
	public void testParseString() {
		tree3 = Tree.parse("one (two three (four five (six seven eight)))");
		Tree<String> child_2 = new Tree<String>("two");
		Tree<String> child_3 = new Tree<String>("three");
		Tree<String> child_4 = new Tree<String>("four");
		Tree<String> child_5 = new Tree<String>("five");
		Tree<String> child_6 = new Tree<String>("six");
		Tree<String> child_7 = new Tree<String>("seven");
		Tree<String> child_8 = new Tree<String>("eight");
		
		assertTrue(tree3.getValue().equals("one"));
		assertTrue(tree3.getChild(0).getValue().equals(child_2.getValue()));
		assertTrue(tree3.getChild(1).getValue().equals(child_3.getValue()));	
		assertTrue(tree3.getChild(1).getChild(0).getValue().equals(child_4.getValue()));
		assertTrue(tree3.getChild(1).getChild(1).getValue().equals(child_5.getValue()));
		assertTrue(tree3.getChild(1).getChild(1).getChild(0).getValue().equals(child_6.getValue()));
		assertTrue(tree3.getChild(1).getChild(1).getChild(1).getValue().equals(child_7.getValue()));
		assertTrue(tree3.getChild(1).getChild(1).getChild(2).getValue().equals(child_8.getValue()));
	}
}
