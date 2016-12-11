package assigment1;

public class Sort {
    public String strYour_name = "Hasan Huseyin PAY";
    public long Your_number = 2014510058;

    public void Our_sort(int[] array, int p, int q, int digit) {

        if (digit == 0 || p >= q)
            return;

        int initialP = p;
        int initialQ = q;

        while (p <= q) {
            String binP = intToString(array[p]);
            String binQ = intToString(array[q]);

            if (binP.charAt(32 - digit) == '1' && binQ.charAt(32 - digit) == '0') {// 1-0
                // fastest swap
                array[p] ^= array[q];
                array[q] ^= array[p];
                array[p] ^= array[q];
                p++;
                q--;
            } else if (binP.charAt(32 - digit) == '1' && binQ.charAt(32 - digit) == '1') {// 1-1
                q--;
            } else if (binP.charAt(32 - digit) == '0' && binQ.charAt(32 - digit) == '0') {
                p++;
            } else if (binP.charAt(32 - digit) == '0' && binQ.charAt(32 - digit) == '1') {
                p++;
                q--;
            }
        }
        Our_sort(array, initialP, q, digit - 1);
        Our_sort(array, p, initialQ, digit - 1);
    }

    public static String intToString(int number) {
        StringBuilder result = new StringBuilder(Integer.toBinaryString(number));
        int len = 32 - result.length();
        for (int i = 0; i < len; i++)
            result.insert(0, "0");

        return result.toString();
    }
}
