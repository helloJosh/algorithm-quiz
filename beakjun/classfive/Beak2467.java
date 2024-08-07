package classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Beak2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        int low = 0;
        int high = n - 1;
        int min = Integer.MAX_VALUE;
        int ansLow = 0;
        int ansHigh = 0;

        while (low < high) {
            int sum = numbers.get(low) + numbers.get(high);

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                ansLow = low;
                ansHigh = high;
            }
            if (sum >= 0) {
                high--;
            } else {
                low++;
            }
        }

        System.out.println(numbers.get(ansLow) + " " + numbers.get(ansHigh));
    }
}
