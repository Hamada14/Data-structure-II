package eg.edu.alexu.csd.filestructure.avl;

import java.util.Random;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

	Node<T> root;

	public AVLTree() {
		root = null;
	}

	@Override
	public void insert(T key) {
		if (root == null) {
			root = new Node<T>(key);
		}
		root = (Node<T>) root.insert(key);
	}

	@Override
	public boolean delete(T key) {
		if (root == null || (!search(key))){
			System.out.println("The current Element doesn't exist in the tree");
			return false;
		}
		root = (Node<T>) root.delete(key);
		return true;
	}

	@Override
	public boolean search(T key) {
		INode<T> current = (Node<T>) root;
		while (current != null) {
			if (current.getValue().compareTo(key) == 0) {
				return true;
			} else if (current.getValue().compareTo(key) > 0) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild();
			}
		}
		return false;
	}

	@Override
	public int height() {
		if (root == null)
			return 0;
		return ((Node<T>) root).getHeight();
	}

	@Override
	public INode<T> getTree() {
		return root;
	}

	private int getAppropHeight(int len){
		return (int)((Math.log(len)/ Math.log(2))) + height();
	}
	public void printTree() {
		if(root == null) return;
		int len = getMaxLen(root);
		int bestVal = getAppropHeight(len);
		int h = (1 << bestVal) + height();
		int width = 2 * h + 2 * len;
		char[][] printedTree = new char[h][width];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < width; j++)
				printedTree[i][j] = ' ';
		printNode(printedTree, root, 0, width / 2, bestVal - 1, len, len / 4);
		for (int i = 0; i < printedTree.length; i++) {
			for (int j = 0; j < printedTree[i].length; j++)
				System.out.print(printedTree[i][j]);
			System.out.println();
		}
	}

	private int getMaxLen(Node<T> root2) {
		if (root2 == null)
			return 0;
		int max = root2.getValue().toString().length();
		return Math.max(max,
				Math.max(getMaxLen((Node<T>) root2.getLeftChild()), getMaxLen((Node<T>) root2.getRightChild())));
	}

	private void printNode(char arr[][], Node<T> node, int x, int y, int h, int len, int shift) {
		String tStr = node.getValue().toString();
		for (int k = 0; k < tStr.length(); k++)
			arr[x][y + k - shift] = tStr.charAt(k);
		int tx = x, ty = y;
		if (node.getLeftChild() != null) {
			for (int i = 0; i < (1 << h); i++) {
				arr[++tx][--ty] = '/';
			}
			printNode(arr, (Node<T>) node.getLeftChild(), tx + 1, ty - (len / 2), h - 1, len, len/4);
		}
		if (node.getRightChild() != null) {
			for (int i = 0; i < (1 << h); i++) {
				arr[++x][++y] = '\\';
			}
			printNode(arr, (Node<T>) node.getRightChild(), x + 1, y + (len / 2), h - 1, len, len/2);
		}
	}

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<Integer>();
		Random rd = new Random();
		for(int i = 0; i < 10; i++){
			avl.printTree();
			avl.insert(rd.nextInt(100));
		}
		avl.printTree();
	}
}
