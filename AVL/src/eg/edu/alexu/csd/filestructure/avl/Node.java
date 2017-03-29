package eg.edu.alexu.csd.filestructure.avl;

public class Node<T extends Comparable<T>> implements INode<T> {

	private Node<T> leftNode, rightNode;
	private T value;
	private int height, balanceFactor;

	
	public Node(T value) {
		this.value = value;
		this.height = 0;
		balanceFactor = 0;
	}

	@Override
	public INode<T> getLeftChild() {
		return leftNode;
	}

	@Override
	public INode<T> getRightChild() {
		return rightNode;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	private void setLeftChild(Node<T> left) {
		this.leftNode = left;
	}

	private void setRightChild(Node<T> right) {
		this.rightNode = right;
	}

	private Node<T> rightRotate() {
		Node<T> node = leftNode;
		this.leftNode = (Node<T>) node.getRightChild();
		node.setRightChild(this);
		updateHeight();
		node.updateHeight();
		return node;
	}

	private Node<T> leftRotate() {
		Node<T> node = rightNode;
		this.rightNode = (Node<T>) node.getLeftChild();
		node.setLeftChild(this);
		updateHeight();
		node.updateHeight();
		return node;
	}

	private void updateHeight() {
		int leftHeight = -1, rightHeight = -1;
		if (leftNode != null)
			leftHeight = leftNode.getHeight();
		if (rightNode != null)
			rightHeight = rightNode.getHeight();
		height = 1 + Math.max(leftHeight, rightHeight);
		balanceFactor = leftHeight - rightHeight;
	}

	public Node<T> rebalance() {
		updateHeight();
		if (balanceFactor < -1) {
			Node<T> rightChild = (Node<T>) getRightChild();
			if (rightChild.getBalanceFactor() <= 0) {
				return (Node<T>) leftRotate();
			} else {
				setRightChild(((Node<T>) getRightChild()).rightRotate());
				return (Node<T>) leftRotate();
			}
		} else if (balanceFactor > 1) {
			Node<T> leftChild = (Node<T>) getLeftChild();
			if (leftChild.getBalanceFactor() >= 0) {
				return (Node<T>) rightRotate();
			} else {
				setLeftChild(((Node<T>)getLeftChild()).leftRotate());
				return (Node<T>)rightRotate();
			}
		}
		return this;
	}

	public INode<T> insert(T key) {
		if (getValue().compareTo(key) > 0) {
			if (getLeftChild() == null)
				leftNode = new Node<T>(key);
			else
				leftNode = (Node<T>) leftNode.insert(key);
		} else if (getValue().compareTo(key) < 0) {
			if (getRightChild() == null)
				rightNode = new Node<T>(key);
			else
				rightNode = (Node<T>) rightNode.insert(key);
		}
		return rebalance();
	}
	
	public INode<T> delete(T key){
		if (getValue().compareTo(key) > 0) {
			leftNode = (Node<T>) leftNode.delete(key);
		} else if (getValue().compareTo(key) < 0) {
			rightNode = (Node<T>) rightNode.delete(key);
		}else{
			if(leftNode == null)
				return rightNode;
			else if(rightNode == null)
				return leftNode;
			else{
				T minVal = rightNode.getMinValue();
				this.value = minVal;
				rightNode.delete(minVal);
			}
		}
		return rebalance();
	}

	private T getMinValue() {
		Node<T> minNode = this;
		while(minNode.leftNode != null){
			minNode = minNode.leftNode;
		}
		return minNode.value;
	}
}
