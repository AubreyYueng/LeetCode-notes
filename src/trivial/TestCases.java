package trivial;

import org.junit.Test;

/**
 * Created by Yiyun On 2021/5/28 20:19
 */
public class TestCases {

    @Test
    public void case1() {
        BinaryTree<Integer> t1 = new BinaryTree<>(1);
        BinaryTree<Integer> t2 = new BinaryTree<>(2);
        BinaryTree<Integer> t3 = new BinaryTree<>(3);
        BinaryTree<Integer> t4 = new BinaryTree<>(4);
        BinaryTree<Integer> t5 = new BinaryTree<>(5);
        BinaryTree<Integer> t6 = new BinaryTree<>(6);
        BinaryTree<Integer> t7 = new BinaryTree<>(7);

        t1.addLeftChild(t2);
        t1.addRightChild(t3);
        t2.addLeftChild(t4);
        t2.addRightChild(t5);
        t3.addLeftChild(t6);
        t3.addRightChild(t7);

        for (Integer i : t1) {
            System.out.println(i);
        }
    }
}
