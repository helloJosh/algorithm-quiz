package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beak9095 {
    public static List<Long> arrays = new ArrayList<>(Collections.nCopies(1000001, 0L));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());



        arrays.set(1, 1L);
        arrays.set(2, 2L);
        arrays.set(3, 4L);
        for (int i = 4; i <= 10000000; i++) {
            arrays.set(i, (arrays.get(i - 1) + arrays.get(i - 2) + arrays.get(i - 3)) % 1000000009L);
        }
        for (int j = 0 ; j < t; j++) {
            int n = Integer.parseInt(br.readLine());

            System.out.println(arrays.get(n));
        }
    }
}
