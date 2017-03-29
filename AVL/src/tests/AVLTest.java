package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import eg.edu.alexu.csd.filestructure.avl.AVLTree;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;

public class AVLTest {

	private static final int MAX_NODES = 1000000;
	private static final String randomString[] = new String[] { "War", "CSED", "Maria", "Race", "Apple",
			"Random String", "Zoro" };

	static Random randomValues;

	static {
		randomValues = new Random(1L << 28);
	}

	@Test
	public void testSpeedInsert() {
		AVLTree<Long> tree = new AVLTree<Long>();
		double startTime = System.currentTimeMillis();
		for (int i = 0; i < MAX_NODES; i++) {
			tree.insert(randomValues.nextLong());
		}
		double endTime = System.currentTimeMillis();
		assertTrue("Too long time needed to insert " + (endTime - startTime), endTime - startTime < 5000.0);
	}

	@Test
	public void testSpeedSearch() {
		AVLTree<Long> tree = new AVLTree<Long>();
		for (int i = 0; i < MAX_NODES; i++) {
			tree.insert(randomValues.nextLong());
		}
		double startTime = System.currentTimeMillis();
		for (int i = 0; i < MAX_NODES; i++) {
			tree.search(randomValues.nextLong());
		}
		double endTime = System.currentTimeMillis();
		assertTrue("Too long time needed to insert " + (endTime - startTime), endTime - startTime < 5000.0);
	}

	@Test
	public void testTrivial1() {
		AVLTree<Long> tree = new AVLTree<Long>();
		HashSet<Long> hs = new HashSet<Long>();
		for (int i = 0; i < MAX_NODES; i++) {
			Long val = randomValues.nextLong();
			tree.insert(val);
			hs.add(val);
			assertTrue("Invalid state", hs.contains(val) == tree.search(val));
		}
	}

	@Test
	public void testTrivial2() {
		AVLTree<String> tree = new AVLTree<String>();
		for (String str : randomString) {
			assertFalse("String found although not in tree", tree.search(str));
			assertFalse("String found although not in tree", tree.delete(str));
			tree.insert(str);
			assertTrue("String not found althou in tree", tree.search(str));
		}
		for (String str : randomString) {
			assertTrue("Can't find string although in tree", tree.search(str));
			assertTrue("Can't delete node although in tree", tree.delete(str));
		}
	}

	@Test
	public void testHeight() {
		AVLTree<Long> tree = new AVLTree<Long>();
		for (int i = 1; i < MAX_NODES; i++) {
			tree.insert(randomValues.nextLong());
			assertTrue(
					"Tree heigh is greater than logn, " + Math.log(i) / Math.log(1.5) + " " + tree.height() + " " + i,
					Math.log(i) / Math.log(1.5) >= tree.height());
		}
	}

	@Test
	public void test() {
		int testSize = 100;
		int elementsSize = MAX_NODES/100;
		for (int j = 0; j < testSize; j++) {
			IAVLTree<Integer> actual = new AVLTree<Integer>();
			Set<Integer> expected = new TreeSet<Integer>();
			for (int i = 0; i < elementsSize; i++) {
				int temp = randomValues.nextInt();
				expected.add(temp);
				actual.insert(temp);
				assertEquals(expected.contains(temp), actual.search(temp));
				temp = randomValues.nextInt();
				assertEquals(expected.remove(temp), actual.delete(temp));
				assertEquals(expected.contains(temp), actual.search(temp));
			}
		}
	}
}
