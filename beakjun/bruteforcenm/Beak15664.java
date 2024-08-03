package bruteforcenm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak15664 {
    public static List<Integer> numbers = new ArrayList<>();
    public static List<String> answers = new ArrayList<>();

    public static void go(List<Integer> as, int m, int index){
        if (m == index) {


            StringBuffer sb = new StringBuffer();
            for (Integer a : as) {
                sb.append(a);
                sb.append(" ");
            }

            if (answers.contains(sb.toString())) {
                return;
            }

            System.out.println(sb);
            answers.add(sb.toString());
            return;
        }
        for (Integer number : numbers) {
            if (!as.isEmpty() && as.getLast() >= number)
                continue;

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
