package assigment2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hasan Hüseyin Pay on 5.12.2016.
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        AugmentAVLTree augtree = new AugmentAVLTree();

        double end, start;
        long sum;


        Random rnd = new Random();
        ArrayList<Integer> rndList = new ArrayList();

        int tmp;
        for (int i = 0; i < 10000; i++) {
            while (rndList.contains((tmp = rnd.nextInt(20000)))) ;
            rndList.add(tmp);
        }


        System.out.println("------ AVL-Tree ------");
        start = System.nanoTime();
        for (int i : rndList)
            tree.insert(i);
        end = System.nanoTime();
        System.out.println("All items were inserted.");
        System.out.format("The time elapsed for the insertion of all items is %f nanoseconds\n", ((end - start) / 1000000000.0));

        System.out.println("The result of GETSUMSMALLER for the item with value 1008" + " is " + tree.getSumSmaller(tree.search(1008)));
        System.out.println("The maximum value of all items is " + tree.getMax());
        System.out.println("The minimum value of all items is " + tree.getMin());

        start = System.nanoTime();
        sum = tree.getAllSum();
        end = System.nanoTime();
        System.out.println("The summation of all items is " + sum);
        System.out.format("The time elapsed for GETSUM is %f nanoseconds\n", ((end - start) / 1000000000.0));
        tree.print();


        System.out.println("------ Augmented AVL-Tree ------");
        start = System.nanoTime();
        for (int i : rndList)
            augtree.insert(i);
        end = System.nanoTime();
        System.out.println("All items were inserted.");
        System.out.format("The time elapsed for the insertion of all items is %f nanoseconds\n", ((end - start) / 1000000000.0));

        System.out.println("The result of GETSUMSMALLER for the item with value 1008" + " is " + augtree.getSumSmaller(augtree.search(1008)));
        System.out.println("The maximum value of all items is " + augtree.getMax());
        System.out.println("The minimum value of all items is " + augtree.getMin());

        start = System.nanoTime();
        sum = augtree.getAllSumAugment();
        end = System.nanoTime();
        System.out.println("The summation of all items is " + sum);
        System.out.format("The time elapsed for GETSUM is %f nanoseconds\n", ((end - start) / 1000000000.0));
        augtree.print();

    }
}
