package graphbfs;

import java.io.*;
import java.util.*;

public class Baek2206 {
    public static class Pair {
        int x;
        int y;
        int z;
        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static List<List<Integer>> arrays = new ArrayList<>();
    public static List<List<List<Integer>>> counts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            List<Integer> array = new ArrayList<>();
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                array.add(Integer.parseInt(String.valueOf(line.charAt(j))));
            }
            arrays.add(array);
        }
        for (int k = 0; k < 2; k++) {
            List<List<Integer>> count = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> c = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    c.add(-1);
                }
                count.add(c);
            }
            counts.add(count);
        }

        doBfs(n, m);

        int val1 = counts.get(1).get(n - 1).get(m - 1);
        int val2 = counts.get(0).get(n - 1).get(m - 1);

        if ( val1 == -1 && val2 == -1) {
            System.out.println(-1);
        } else {
            if (val1 == -1) {
                System.out.println(val2);
            } else if(val2 == -1) {
                System.out.println(val1);
            } else {
                System.out.println(Math.min(val1, val2));
            }
        }
    }
    public static void doBfs(int n, int m){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0, 0));
        counts.get(0).get(0).set(0, 1);

        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                int nz = pair.z;
                if ( 0 <= nx && nx < m
                        && 0 <= ny && ny < n
                        && arrays.get(ny).get(nx) == 0
                        && counts.get(nz).get(ny).get(nx) == -1) {
                    queue.add(new Pair(nx, ny, nz));
                    counts.get(nz).get(ny).set(nx, counts.get(pair.z).get(pair.y).get(pair.x) + 1);
                }
            }
            if (pair.z == 0) {
                for (int i = 0; i < 4; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];
                    int nz = pair.z + 1;
                    if (0 <= nx && nx < m
                            && 0 <= ny && ny < n
                            && arrays.get(ny).get(nx) == 1
                            && counts.get(nz).get(ny).get(nx) == -1) {
                        queue.add(new Pair(nx, ny, nz));
                        counts.get(nz).get(ny).set(nx, counts.get(pair.z).get(pair.y).get(pair.x) + 1);
                    }
                }
            }
        }
    }
}
