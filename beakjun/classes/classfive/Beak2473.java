package classes.classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Beak2473 {
    public static List<Integer> array = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(array);

        long min = Long.MAX_VALUE;

        int val1 = 0;
        int val2 = 0;
        int val3 = 0;

        for (int i = 0; i < n-2; i++) {
            int low = i + 1;
            int high = n - 1;

            while (low < high) {
                long sum = (long)array.get(i) + (long)array.get(low) + (long)array.get(high);

                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    val1 = i;
                    val2 = low;
                    val3 = high;
                }
                if (sum == 0){
                    val1 = i;
                    val2 = low;
                    val3 = high;

                    StringBuilder sb = new StringBuilder();
                    sb.append(array.get(val1));
                    sb.append(" ");
                    sb.append(array.get(val2));
                    sb.append(" ");
                    sb.append(array.get(val3));
                    System.out.println(sb);

                    return;
                } else if(sum > 0) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array.get(val1));
        sb.append(" ");
        sb.append(array.get(val2));
        sb.append(" ");
        sb.append(array.get(val3));
        System.out.println(sb);
    }
}
