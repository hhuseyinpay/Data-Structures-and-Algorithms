package assigment1;

public class Main {
    public static void main(String[] args) {

        Sort sort = new Sort();

        int[] a = {7, 2, 888, 4, 1, 12, 3, 2, 4, 7, 0};

        sort.Our_sort(a, 0, a.length - 1, 32);

        System.out.println("Sorted array:");
        for (int i = 0; i < a.length; i++)
            System.out.print(" " + a[i]);

    }

}
