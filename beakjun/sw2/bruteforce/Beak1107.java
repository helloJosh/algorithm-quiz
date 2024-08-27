package sw2.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Beak1107 {
    public static List<Integer> errors;
    public static List<Integer> oks;
    public static String n;
    public static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();
        int target = Integer.parseInt(n);

        int m = Integer.parseInt(br.readLine());
        if (m == 0) {
            errors = new ArrayList<>();
        } else {
            errors = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                errors.add(Integer.parseInt(st.nextToken()));
            }
        }

        oks = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            if (!errors.contains(i)) {
                oks.add(i);
            }
        }

        ans = Math.abs(target - 100);

        for (int i = 1; i <= n.length() + 1; i++) {
            doBfs("", 0, i);
        }

        System.out.println(ans);
    }

    public static void doBfs(String s, int index, int maxLength){
        if (index == maxLength) {
            int retVal = Math.abs(Integer.parseInt(s) - Integer.parseInt(n));

            if (retVal + s.length() < ans) {
                ans = retVal + s.length();
            }
            return;
        }
        for (Integer i : oks) {
            String tmp = s + i;
            doBfs(tmp, index + 1, maxLength);
        }
    }
}
