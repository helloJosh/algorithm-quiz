package sw2.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beak1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int len = 1;
        long ans = 0;

        for (int i = 1; i <= n; i *= 10) {
            int start = i;
            int end = i * 10 - 1;

            if (end > n) {
                end = n;
            }
            ans += (long) (end - start + 1) * len;

            len ++;
        }

        System.out.println(ans);
    }
}
