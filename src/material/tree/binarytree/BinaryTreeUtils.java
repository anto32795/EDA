package material.tree.binarytree;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.Iterator;

public class BinaryTreeUtils<E> {
	
	private BinaryTree<E> tree;
	
	public BinaryTreeUtils(BinaryTree<E> tree) {
		this.tree = tree;
	}
	
	/** 
	  * Given a tree the method returns a new tree where all left children 
	  * become right children and vice versa
	 *
	 * 	*/
	/*
	public BinaryTree<E> mirror() {
		BinaryTree<E> reverse_tree = new LinkedBinaryTree<>();
		reverse_tree.addRoot(this.tree.root().getElement());


	}

	private Position<E> node_mirror(Position<E> node){
		if (node == null)
			return node;
		Position<E> left = node_mirror(this.tree.left(node));
		Position<E> right = node_mirror(this.tree.right(node));

		this.tree.left(node) = right;


	}*/
	/** 
	  * Determines whether the element e is the tree or not
	 *
	 * Tree has no ordered nodes, so O(n)
	*/
	public boolean contains (E e) {
		Iterator<Position<E>> it = this.tree.iterator();
		Position<E> aux;
		while(it.hasNext()){
			aux = it.next();
			if(aux.getElement().equals(e))
				return true;
		}
		return false;
	}
	/** 
	  * Determines the level of a node in the tree (root is located at level 1)
	*/
	public int level(Position<E> p) {
		return calculateLevel(1, p, p);
	}

	private int calculateLevel(int currentLevel, Position<E> node, Position<E> currentNode){

		if(currentNode != null) {
			if(node.getElement().equals(currentNode.getElement()))
				return currentLevel;

			calculateLevel(currentLevel+1, node, tree.left(currentNode));
			calculateLevel(currentLevel+1, node, tree.right(currentNode));
		}
		return -1;
	}

}
