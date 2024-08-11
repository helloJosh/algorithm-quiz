package classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Beak10942 {
    public static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        String line;
        while ((line = st.nextToken()) != null) {
            numbers.add(Integer.parseInt(line));

            if (!st.hasMoreElements()) {
                break;
            }
        }

        int m = Integer.parseInt(br.readLine());

        String line2;
        while ((line2 = br.readLine()) != null) {
            st = new StringTokenizer(line2);

            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            List<Integer> inputNumbers = numbers.subList(val1 - 1, val2);

            if (isPelin(inputNumbers)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    public static boolean isPelin(List<Integer> numbers) {
        int i = 0;
        int j = numbers.size() - 1;
        while (i <= j) {
            if (!Objects.equals(numbers.get(i), numbers.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
