package sw1.bruteforce;

public class beak10972 {
    public static boolean nextPermutation(int[] a){
        int i = a.length-1;
        while(i>0 && a[i]<=a[i-1]){
            i -= 1;
        }
        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }


    public static void main(String[] args) {
        int[] a = new int[7];
    }
}
