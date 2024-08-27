package classes.classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Beak7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> memories = new ArrayList<>();
        List<Integer> costs = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memories.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            costs.add(Integer.parseInt(st.nextToken()));
        }

        List<List<Integer>> dps = new ArrayList<>(Collections.nCopies(n, new ArrayList<>(Collections.nCopies(10001 ,0))));
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int cost = costs.get(i);
            int memory = memories.get(i);

            for (int j = 0; j < 10001; j++) {
                if (i == 0) {
                    if (j >= cost) {
                        dps.get(i).set(j, memory);
                    }
                } else {
                    if (j >= cost) {
                        dps.get(i).set(j,
                                Math.max(dps.get(i - 1).get(j), dps.get(i - 1).get(j - cost) + memory)
                        );
                    } else {
                        dps.get(i).set(j, dps.get(i - 1).get(j));
                    }
                }

                if (dps.get(i).get(j) >= m) {
                    answer = Math.min(answer, j);
                }
            }
        }
        System.out.println(answer);
    }
}
