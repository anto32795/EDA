package material.tree.narytree;

import material.Position;

import java.util.Iterator;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez, Jesús Sánchez-Oro
 * @param <E> the elements stored in the tree
 */
public class LCRSTree<E> implements NAryTree<E> {

    private class TreeNode<T> implements Position<T>{
        private T elem;
        private TreeNode<T> parent, sibling, firstSon;
        private LCRSTree<E> myTree;

        /*public TreeNode(T elem) {
            this.elem = elem;
        }*/

        public TreeNode(T elem, TreeNode<T> parent, TreeNode<T> sibling, TreeNode<T> firstSon, LCRSTree<E> t) {
            this.elem = elem;
            this.parent = parent;
            this.sibling = sibling;
            this.firstSon = firstSon;
            this.myTree = t;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        public TreeNode<T> getSibling() {
            return sibling;
        }

        public void setSibling(TreeNode<T> sibling) {
            this.sibling = sibling;
        }

        public TreeNode<T> getFirstSon() {
            return firstSon;
        }

        public void setFirstSon(TreeNode<T> firstSon) {
            this.firstSon = firstSon;
        }

        public LCRSTree<E> getMyTree() {
            return myTree;
        }

        public void setMyTree(LCRSTree<E> myTree) {
            this.myTree = myTree;
        }

        @Override
        public T getElement() {
            return elem;
        }
    }

    /**
     *  LCRSTree methods and atributes
     * */
    private TreeNode<E> root;
    private int size;

    public LCRSTree() {
        root = null;
        size = 0;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v));
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return checkPosition(v).firstSon == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return checkPosition(v).parent == null;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if(this.root() != null)
            throw new RuntimeException("Tree already has a root.");
        TreeNode<E> node = new TreeNode<>(e, null, null, null, this);
        this.root = node;
        return node;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void remove(Position<E> p) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        throw new RuntimeException("Not yet implemented");
    }

    // check position method

    private TreeNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }
}
