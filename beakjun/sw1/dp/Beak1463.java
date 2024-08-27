package sw1.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beak1463 {
    public static List<Integer> array = new ArrayList<>(Collections.nCopies(2000000, -1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array.set(1, 0);

        for (int i = 2; i <= n; i++) {
            array.set(i, array.get(i - 1) + 1);

            if (i % 2 == 0
                    && array.get(i) > array.get(i / 2) + 1){
                array.set(i, array.get(i / 2) + 1);
            }
            if( i % 3 == 0
                    && array.get(i) > array.get(i / 3) + 1){
                array.set(i, array.get(i / 3) + 1);
            }
        }

        System.out.println(array.get(n));

    }
}
