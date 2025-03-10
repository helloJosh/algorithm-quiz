package sw1.bruteforcenm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Beak15656 {
    public static List<Integer> numbers = new ArrayList<>();

    public static void go(List<Integer> as, int m, int index){
        if (m == index) {
            StringBuffer sb = new StringBuffer();
            for (Integer a : as) {
                sb.append(a);
                sb.append(" ");
            }
            System.out.println(sb);
            return;
        }
        for (Integer number : numbers) {
            as.add(number);
            go(as, m, index+1);
            as.remove(number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers.add(
                    Integer.parseInt(st.nextToken())
            );
        }

        Collections.sort(numbers);

        List<Integer> as = new ArrayList<>();

        go(as, m, 0);
    }
}
