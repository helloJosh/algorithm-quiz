package sw1.graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak13549 {
    public static List<Integer> sobin = new ArrayList<>(Collections.nCopies(200001, 0));

    public static void doBfs(int n){
        Deque<Integer> deque = new LinkedList<>();

        deque.add(n);
        sobin.set(n, 0);

        while (!deque.isEmpty()) {
            int x = deque.removeFirst();
            int nx = x + 1;
            if (0 <= nx && nx <=100000
                && sobin.get(nx) == 0) {
                deque.addLast(nx);
                sobin.set(nx, sobin.get(x) + 1);
            }

            nx = x - 1;
            if (0 <= nx && nx <=100000
                    && sobin.get(nx) == 0) {
                deque.addLast(nx);
                sobin.set(nx, sobin.get(x) + 1);
            }

            nx = 2 * x;
            if (0 <= nx && nx <=100000
                    && sobin.get(nx) == 0) {
                deque.addFirst(nx);
                sobin.set(nx, sobin.get(x));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        doBfs(n);

        System.out.println(sobin.get(k));

    }
}
