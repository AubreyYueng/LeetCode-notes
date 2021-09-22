import java.util.Iterator;
import java.util.LinkedList;

public class BinaryTree<T> implements Iterable<T> {

    T t;
    BinaryTree<T> left;
    BinaryTree<T> right;

    public BinaryTree() {
    }

    public BinaryTree(T t) {
        this.t = t;
    }

    public void addLeftChild(BinaryTree<T> left) {
        this.left = left;
    }

    public void addRightChild(BinaryTree<T> right) {
        this.right = right;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<T>(this);
    }

    private static class BinaryTreeIterator<T> implements Iterator<T> {

        LinkedList<BinaryTree<T>> queue;
        BinaryTree<T> root;

        public BinaryTreeIterator(BinaryTree<T> root) {
            queue = new LinkedList<>();
            queue.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (queue.isEmpty())
                return null;
            BinaryTree<T> peek = queue.poll();
            if (peek.left != null)
                queue.offer(peek.left);
            if (peek.right != null)
                queue.offer(peek.right);
            return peek.t;
        }
    }

}
