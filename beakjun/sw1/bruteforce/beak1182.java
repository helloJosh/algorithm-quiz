package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class beak1182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> a = new ArrayList<>();
        
        for (int i = 0; i < n ;i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;

        for (int i = 0; i < (1 << n); i++ ) {
            int  sum = 0;
            for (int k = 0; k < n;i++) {
                if ( (i & (1 << k)) != 0) {
                    sum += a.get(k);
                }
            }
            if(sum == s){
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
