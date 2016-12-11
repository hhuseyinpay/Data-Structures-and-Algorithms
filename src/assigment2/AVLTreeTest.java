package assigment2;

/**
 * Created by Hasan Hüseyin Pay on 5.12.2016.
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        AugmentAVLTree augtree = new AugmentAVLTree();

        double end, start;
        long sum;
        System.out.println("------ AVL-Tree ------");
        start = System.nanoTime();
        for (int i = 1000; i > 0; i--)
            tree.insert(i);
        end = System.nanoTime();
        System.out.println("All items were inserted.");
        System.out.format("The time elapsed for the insertion of all items is %f nanoseconds\n", ((end - start) / 1000000000.0));

        System.out.println("The result of GETSUMSMALLER for the item with value 100" + " is " + tree.getSumSmaller(tree.search(100)));
        System.out.println("The maximum value of all items is " + tree.getMax());
        System.out.println("The minimum value of all items is " + tree.getMin());

        start = System.nanoTime();
        sum = tree.getAllSum();
        end = System.nanoTime();
        System.out.println("The summation of all items is " + sum);
        System.out.format("The time elapsed for GETSUM is %f nanoseconds\n", ((end - start) / 1000000000.0));
        //tree.inorder();

        System.out.println("------ Augmented AVL-Tree ------");


        start = System.nanoTime();
        for (int i = 3000; i > 0; i--) {
            augtree.insert(i);
            //System.out.println(i);
        }
        end = System.nanoTime();
        System.out.println("All items were inserted.");
        System.out.format("The time elapsed for the insertion of all items is %f nanoseconds\n", ((double) (end - start) / 1000000000.0));

        //System.out.println("The result of GETSUMSMALLER for the item with value 100" + " is " + augtree.getSumSmaller(augtree.search(100)));
        System.out.println("The maximum value of all items is " + augtree.getMax());
        System.out.println("The minimum value of all items is " + augtree.getMin());

        start = System.nanoTime();
        sum = augtree.getAllSumAugment();
        end = System.nanoTime();
        System.out.println("The summation of all items is " + sum);
        System.out.format("The time elapsed for GETSUM is %f nanoseconds\n", ((double) (end - start) / 1000000000.0));
        augtree.inorder();

    }
}
