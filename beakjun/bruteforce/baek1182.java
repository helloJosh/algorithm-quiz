package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baek1182 {
    public static int go(List<Integer> a, int s, int i, int sum){
        if (i == a.size()) {
            if (sum == i) {
                return 1;
            } else {
                return 0;
            }
        } 
        
        return go(a, s, i + 1, sum + a.get(i)) + go(a, s, i + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        List<Integer> a = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }

        int ans = go(a, s, 0, 0);
        if (s == 0) {
            ans -= 1;
        }
        System.out.println(ans);
    }
}
