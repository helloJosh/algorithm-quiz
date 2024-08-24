package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beak11726 {
    public static List<Integer> arrays = new ArrayList<>(Collections.nCopies(1001, 0));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arrays.set(1, 1);
        arrays.set(2, 3);
        for (int i = 3; i <= n; i++) {
            arrays.set(i, (arrays.get(i - 1) + 2 * arrays.get(i - 2)) %10007);
        }

        System.out.println(arrays.get(n));
    }
}
