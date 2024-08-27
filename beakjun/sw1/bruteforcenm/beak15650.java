package sw1.bruteforcenm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class beak15650 {
    public static List<Integer> a = new ArrayList<>();
    public static List<Boolean> b = new ArrayList<>();

    public static void go(int index, int start, int n, int m) {
        if (index == m) {
            for (int i = 0; i < m; i++){
                System.out.print(a.get(i) + " ");
            }
            System.out.println();

            return;
        }
        for (int i = start-1; i < n ; i++) {
            if (b.get(i)) {
                continue;
            }

            b.set(i, true);
            a.set(index, i);
            go(index + 1, i + 1, n, m);
            b.set(i, false);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < n ; i++) {
            a.add(i+1);
        }

        for (int i = 0; i < m ; i++ ) {
            b.add(false);
        }
        go(0, 1, m, n);
    }    
}
