package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baek14888 {

    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;
    public static void go(int n, List<Integer> a ,int sum, int add, int sub, int mult, int div ){
        if (add < 0 || sub < 0 || mult < 0 || div < 0) {
            return;
        }
        if (add == 0 && sub == 0 && mult == 0 && div == 0) {
            if (max < sum) {
                max = sum;
            }
            if (min > sum) {
                min = sum;
            }
            return;
        }

        go(n + 1, a, sum + a.get(n), add-  1, sub, mult, div);
        go(n + 1, a, sum - a.get(n), add, sub - 1, mult, div);
        go(n + 1, a, sum * a .get(n), add, sub, mult - 1, div);
        go(n + 1, a, sum / a.get(n), add, sub, mult, div - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < n ; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }

        int add = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int mult = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        go(0, a, 0 , add , sub , mult, div);

    }
}
