package lab78;

import java.util.NoSuchElementException;

public class BSTgood<T> {
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

    public BSTgood() {
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
        Node found = search(elem);
        Node succ = successorNode(found);
        return succ==null ? null : succ.value;

    }

    @SuppressWarnings("unchecked")
    private Node successorNode(Node node) throws NoSuchElementException{
        if(node.right != null)
            return getMin(node.right);
        Node y = node.parent;
        while(y != null && node == y.right){
            node = y;
            y = y.parent;
        }
        return getMin(y);
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
        if (root == null) {
            root = new Node(elem);
            size++;
            return true;
        }
        Node current = root;
        Node parent =  null;
        boolean isLeft = false;
        while (true) {
            if (current == null) {
                if(isLeft) {
                    parent.left = new Node(elem);
                    parent.left.parent = parent;
                }
                else {
                    parent.right = new Node(elem);
                    parent.right.parent = parent;
                }
                size++;
                return true;
            }
            int cmp = ((Comparable<T>) elem).compareTo(current.value);
            if (cmp < 0) {
                parent = current;
                current = current.left;
                isLeft = true;
            }
            else if (cmp > 0) {
                parent = current;
                current = current.right;
                isLeft = false;
            }
            else
                return false;
        }
    }


    public T remove(T value) {
        Node current = root;
        Node parent = null;
        boolean isLeftChild = false;
/*
        while (current != null && ((Comparable<T>) value).compareTo(current.value) !=0) {
            parent = current;
            int cmp = ((Comparable<T>) value).compareTo(current.value);
            if (cmp < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }*/

        current = search(value);
        if (current == null) {
            return null;
        }
        if(current != root){
            parent = current.parent;
           isLeftChild = ((Comparable<T>) current.value).compareTo(parent.value) < 0;
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        else {
            Node successor = successorNode(current);
            if (successor.parent != current) {
                successor.parent.left = successor.right;
                if(successor.right != null)
                    successor.right.parent = successor.parent;
                successor.right = current.right;
                current.right.parent =  successor;
            }

            if (current == root) {
                root = successor;
                successor.parent =null;
            } else if (isLeftChild) {
                parent.left = successor;
                successor.parent = parent;
            } else {
                parent.right = successor;
                successor.parent = parent;
            }
            successor.left = current.left;
            current.left.parent = successor;
        }
        size--;
        return current.value;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public int numLeaves()
    {
        return numberLeaves(root);
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



}
