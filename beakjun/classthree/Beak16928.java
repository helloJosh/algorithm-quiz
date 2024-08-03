package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak16928 {
    private static Map<Integer, Integer> shortCut = new HashMap<>();
    private static List<Integer> checks = new ArrayList<>(Collections.nCopies(101, -1));
    private static int cnt = 0;

    private static void doBfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        checks.set(1, 0);

        while (!queue.isEmpty()) {
            cnt ++;
            int x = queue.remove();
            for (int i = 1; i < 7; i++) {
                int nx = x + i;
                if (0 <= nx && nx < 101
                    && checks.get(nx) == -1) {

                    if (shortCut.containsKey(nx)) {
                        //checks.set(nx, checks.get(x) + 1);
                        nx = shortCut.get(nx);
                    }
                    checks.set(nx, checks.get(x) + 1);
                    queue.add(nx);

                    if (nx == 100) {
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            shortCut.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        doBfs();
        System.out.println(checks);
        System.out.println(checks.get(100));
        System.out.println(cnt);
    }
}
