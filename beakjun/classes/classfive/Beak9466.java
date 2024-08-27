package classes.classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Beak9466 {
    public static List<Integer> arrays;
    public static List<Boolean> checks, dones;
    public static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while ( t-- > 0 ) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            arrays = new ArrayList<>(Collections.nCopies(n, 0));
            checks = new ArrayList<>(Collections.nCopies(n, false));
            dones = new ArrayList<>(Collections.nCopies(n, false));
            count = 0;

            for (int i = 0 ; i < n; i++) {
                arrays.set(i, Integer.parseInt(st.nextToken()) - 1);
            }

            for (int i = 0; i < n; i++) {
                if(!dones.get(i)){
                    doDfs(i);
                }
            }
            System.out.println(n - count);
        }
    }
    public static void doDfs(int n) {
        if (checks.get(n)) {
            dones.set(n, true);
            count++;
        } else {
            checks.set(n, true);
        }

        if (!dones.get(arrays.get(n))) {
            doDfs(arrays.get(n));
        }

        checks.set(n, false);
        dones.set(n, true);
    }
}
