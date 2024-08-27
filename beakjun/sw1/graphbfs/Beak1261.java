package sw1.graphbfs;

import java.io.*;
import java.util.*;
public class Beak1261 {
    public static class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static List<List<Integer>> matrixes = new ArrayList<>();
    public static List<List<Integer>> counts = new ArrayList<>();
    public static List<List<Boolean>> visits = new ArrayList<>();
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            List<Integer> matrix = new ArrayList<>();
            List<Integer> count = new ArrayList<>();
            List<Boolean> visit = new ArrayList<>();
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix.add(Integer.parseInt(String.valueOf(line.charAt(j))));
                count.add(0);
                visit.add(false);
            }
            matrixes.add(matrix); counts.add(count); visits.add(visit);
        }
        doBfs(n, m);
        System.out.println(counts.get(m - 1).get(n - 1));
    }

    public static void doBfs(int n, int m){
        Deque<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,0));
        counts.get(0).set(0, 0);
        visits.get(0).set(0, true);

        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if ( 0 <= nx && nx < n
                        && 0 <= ny && ny < m
                        && !visits.get(ny).get(nx)) {
                    if (matrixes.get(ny).get(nx) == 1) {
                        queue.addLast(new Pair(nx, ny));
                        counts.get(ny).set(nx, counts.get(pair.y).get(pair.x) + 1);
                        visits.get(ny).set(nx, true);
                    }
                    else if (matrixes.get(ny).get(nx) == 0) {
                        queue.addFirst(new Pair(nx, ny));
                        counts.get(ny).set(nx, counts.get(pair.y).get(pair.x));
                        visits.get(ny).set(nx, true);
                    }
                }
            }
        }

    }
}
