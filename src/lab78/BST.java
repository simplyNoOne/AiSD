package lab78;

import java.util.NoSuchElementException;

public class BST<T> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}		
	private Node root=null;
	private int size=0;

	public BST() {
	}

	public T getElement(T toFind) {
		Node node = search(toFind);
		return node ==null ? null : node.value;
	}

	@SuppressWarnings("unchecked")
	private Node search(T elem){
		Node node = root;
		int cmp = 0;
		while( node != null && (cmp= ((Comparable<T>)elem).compareTo(node.value)) != 0){
			node = cmp<0 ? node.left : node.right;
		}
		return node;
	}


	public T successor(T elem) {
		try {
			Node node = successorNode(root, elem);
			return node==null ? null : node.value;
		}catch(NoSuchElementException ex){return null;}

	}

	@SuppressWarnings("unchecked")
	private Node successorNode(Node node, T elem) throws NoSuchElementException{
		if (node == null) throw new NoSuchElementException();
		int cmp = ((Comparable<T>)elem).compareTo(node.value);
		if(cmp == 0){
			if (node.right != null){
				return getMin(node.right);
			}
			else return null;
		}
		else if ( cmp < 0){
			Node ret = successorNode(node.left, elem);
			return ret==null ? node : ret;
		}
		else
			return successorNode(node.right, elem);
	}


	private Node getMin(Node node){
		while ( node.left != null)
			node = node.left;
		return node;
	}

	public String toStringInOrder() {
		return inOrderWalk(root);
	}

	public String inOrderWalk (Node node){
		String part = "";
		if(node != null){
			part += inOrderWalk(node.left);
			part += node.value.toString() + ", ";
			part +=inOrderWalk(node.right);
		}
		return part;
	}

	public String toStringPreOrder() {
		return PreOrderWalk(root);
	}

	public String PreOrderWalk (Node node){
		String part = "";
		if(node != null){
			part += node.value.toString() + ", ";
			part += PreOrderWalk(node.left);
			part +=PreOrderWalk(node.right);
		}
		return part;
	}

	public String toStringPostOrder() {
		return PostOrderWalk(root);
	}

	public String PostOrderWalk (Node node){
		String part = "";
		if(node != null){
			part += PostOrderWalk(node.left);
			part +=PostOrderWalk(node.right);
			part += node.value.toString() + ", ";
		}
		return part;
	}

	public boolean add(T elem) {
		try {
			root = insert(root, elem);
		}catch (Exception ex){return false;}
		size++;
		return true;
	}
	@SuppressWarnings("unchecked")
	private Node insert(Node node, T elem) throws Exception{
		if(node == null)
			return new Node(elem);
		int cmp = ((Comparable<T>)elem).compareTo(node.value);
		if (cmp < 0)
			node.left = insert(node.left, elem);
		else if (cmp > 0)
			node.right = insert(node.right, elem);
		else
			throw new Exception();
		return node;
	}


	public T remove(T value) {
		T[] found = (T[]) new Object[1];
		try {
			root = delete(root, value, found);
		}catch (NoSuchElementException ex){
			return null;
		}
		size--;
		return found[0];
	}

	@SuppressWarnings("unchecked")
	private Node delete (Node node, T elem, T[] found) throws NoSuchElementException{
		if(node == null) throw new NoSuchElementException();
		int cmp = ((Comparable<T>)elem).compareTo(node.value);
		if (cmp < 0)
			node.left = delete(node.left, elem, found);
		else if (cmp > 0)
			node.right = delete(node.right, elem, found);
		else if (node.right != null && node.left != null) {
			found[0] = node.value;
			node.right = detachMin(node, node.right);
		}
		else{
			found[0] =node.value;
			node = (node.left != null ) ? node.left : node.right;
		}

		return node;
	}


	private Node detachMin(Node del, Node node){
		if (node.left != null) node.left = detachMin(del, node.left);
		else {
			del.value = node.value;
			node = node.right;
		}
		return node;
	}


	public void clear() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public int numberLeaves(Node start){
		if(start == null) {
			return 0;
		}
		if(start.left == null && start.right == null)
			return 1;
		else{
			return numberLeaves(start.left) + numberLeaves(start.right);
		}
	}

	public int numLeaves()
	{

		return numberLeaves(root);

	}


}
