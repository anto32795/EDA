package material.tree.binarytree;

import material.Position;
import material.tree.narytree.LCRSTree;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayBinaryTree<E> implements BinaryTree<E> {
    private class BTNode<E> implements Position<E>{
        private E element;
        private int pos;
        private ArrayBinaryTree<E> myTree;

        @Override
        public E getElement() {
            return element;
        }
        public int getPos(){
            return pos;
        }
        public ArrayBinaryTree<E> getMyTree() { return myTree; }

        public BTNode(E element, int pos, ArrayBinaryTree<E> myTree) {
            this.element = element;
            this.pos = pos;
            this.myTree = myTree;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public void setMyTree(ArrayBinaryTree<E> myTree) {
            this.myTree = myTree;
        }
    }
    /**
     *  Methods and atributes for ArrayBinaryTree
     * */
    private ArrayList<BTNode<E>> bucket;
    private int size;

    public ArrayBinaryTree() {
        this.bucket = new ArrayList<>(10);
        this.size = 0;
    }

    public ArrayList<BTNode<E>> getBucket() {
        return bucket;
    }

    public void setBucket(ArrayList<BTNode<E>> bucket) {
        this.bucket = bucket;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Position<E> left(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        if(isLeaf(p))
            throw new RuntimeException("Node is leaf, has no left");
        return this.bucket.get(node.getPos()*2);

    }

    @Override
    public Position<E> right(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        if(isLeaf(p)) throw new RuntimeException("Node is leaf, has no right");
        return this.bucket.get(node.getPos()*2 +1);
    }

    @Override
    public boolean hasLeft(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return this.bucket.get((node.getPos()*2)) != null;
    }

    @Override
    public boolean hasRight(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return this.bucket.get(node.getPos()*2+1) != null;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E rtn = node.getElement();
        node.setElement(e);
        return rtn;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        return null;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {

    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        return null;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {

    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {

    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        return null;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        return null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return false;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return false;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return false;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        return null;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return null;
    }

    private BTNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof BTNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        BTNode<E> aux = (BTNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }
}