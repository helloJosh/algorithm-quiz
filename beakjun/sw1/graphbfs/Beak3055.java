package sw1.graphbfs;

import java.io.*;
import java.util.*;

public class Beak3055 {
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static List<List<Character>> arrays = new ArrayList<>();
    public static List<List<Integer>> waters = new ArrayList<>();
    public static List<List<Integer>> counts = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        List<Pair> waterStart = new ArrayList<>();
        Pair start = null;
        Pair end = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // y
        int r = Integer.parseInt(st.nextToken());
        // x
        int c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            List<Character> array = new ArrayList<>();
            List<Integer> water = new ArrayList<>();
            List<Integer> count = new ArrayList<>();
            for (int j = 0; j < c; j++) {
                char w = line.charAt(j);
                array.add(w);
                water.add(0);
                count.add(0);
                if (w == '*') {
                    waterStart.add(new Pair(j, i));
                } else if (w == 'S') {
                    start = new Pair(j, i);
                } else if (w == 'X') {
                    water.set(j, -1);
                    count.set(j, -1);
                } else if (w == 'D') {
                    end = new Pair(j, i);
                    water.set(j, -1);
                }
            }
            arrays.add(array);
            waters.add(water);
            counts.add(count);
        }
        doBfsForWater(waterStart, r, c);
        doBfs(start, end, r, c);
        System.out.println(waters);
        System.out.println(counts);

        int ans = counts.get(end.y).get(end.x);
        if (ans == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(ans - 1);
        }
    }

    public static void doBfs(Pair pair, Pair end, int r, int c) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);
        counts.get(pair.y).set(pair.x, 1);

        while (!queue.isEmpty()) {
            Pair p = queue.remove();
            for (int i = 0 ;i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && nx < c
                        && 0 <= ny && ny < r) {
                    if (nx == end.x && ny == end.y) {
                        counts.get(ny).set(nx, counts.get(p.y).get(p.x) + 1);
                        return;
                    }

                    if ( arrays.get(ny).get(nx) == '.'
                            && counts.get(ny).get(nx) == 0
                            && (waters.get(ny).get(nx) == 0 || waters.get(ny).get(nx) > counts.get(p.y).get(p.x) + 1)) {
                        queue.add(new Pair(nx, ny));
                        counts.get(ny).set(nx, counts.get(p.y).get(p.x) + 1);
                    }
                }
            }
        }
    }

    public static void doBfsForWater(List<Pair> pairs, int r, int c) {
        Queue<Pair> queue = new LinkedList<>();
        for (Pair p : pairs) {
            queue.add(new Pair(p.x, p.y));
            waters.get(p.y).set(p.x, 1);
        }

        while (!queue.isEmpty()) {
            Pair p = queue.remove();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (0 <= nx && nx < c
                        && 0 <= ny && ny < r
                        && arrays.get(ny).get(nx) == '.'
                        && waters.get(ny).get(nx) == 0) {
                    queue.add(new Pair(nx, ny));
                    waters.get(ny).set(nx, waters.get(p.y).get(p.x) + 1);
                }
            }
        }
    }
}
