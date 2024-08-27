package sw1.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baek6603 {
    public static void go(int n, List<Integer> a, List<Integer> b, int k) {
        if(n == b.size()) {
            for(Integer i : b){
                System.out.print(i+" ");
            }
            System.out.println();
            return;
        }
        if(a.size() == k){
            return;
        }

        b.add(a.get(k));
        go(n, a, b, k+1);
        b.remove(a.get(k));
        go(n, a, b, k+1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            if (st.countTokens() == 1) {
                return;
            }

            int t = Integer.parseInt(st.nextToken());

            List<Integer> a = new ArrayList<>();

            for (int i = 0; i < t; i++) {
                a.add(Integer.parseInt(st.nextToken()));
            }

            go(6, a, new ArrayList<Integer>(), 0);
        }
    }
}
