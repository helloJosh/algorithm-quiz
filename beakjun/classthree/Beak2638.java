package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak2638 {
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Graph {
        private List<List<Integer>> arrays;
        private List<List<Integer>> checks;
        private List<List<Boolean>> visits;
        private int m;
        private int n;
        public Graph(int m, int n) {
            this.m = m;
            this.n = n;
            arrays = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> rows = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    rows.add(0);
                }
                arrays.add(rows);
            }
        }
        public void addEdge(int x, int y){
            arrays.get(y).set(x, 1);
        }
    }
    public static class GraphService {
        private Graph graph;
        public GraphService(Graph graph) {
            this.graph = graph;
        }
        public void doBfs() {
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, 0));
            graph.visits.get(0).set(0, true);

            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                for (int i = 0; i < 4; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];
                    if (0 <= nx && nx < graph.m && 0 <= ny && ny < graph.n
                        && Boolean.TRUE.equals(!graph.visits.get(ny).get(nx))
                        && graph.arrays.get(ny).get(nx) == 0) {
                        queue.add(new Pair(nx, ny));
                        graph.visits.get(ny).set(nx, true);
                    }

                    if (0 <= nx && nx < graph.m && 0 <= ny && ny < graph.n
                        && graph.arrays.get(ny).get(nx) == 1) {
                        graph.checks.get(ny).set(nx, graph.checks.get(ny).get(nx) + 1);
                    }
                }
            }
        }
        public void resetCheck() {
            graph.checks = new ArrayList<>();
            graph.visits = new ArrayList<>();
            for (int i = 0; i < graph.n; i++) {
                List<Integer> crows = new ArrayList<>();
                List<Boolean> vrows = new ArrayList<>();
                for (int j = 0; j < graph.m; j++) {
                    crows.add(0);
                    vrows.add(false);
                }
                graph.checks.add(crows);
                graph.visits.add(vrows);
            }
        }
        public void removeCheese() {
            for (int i = 0; i < graph.n; i++) {
                for (int j = 0; j < graph.m; j++) {
                    if(graph.checks.get(i).get(j) >= 2){
                        graph.arrays.get(i).set(j, 0);
                    }
                }
            }
        }
        public boolean hasNoCheese() {
            for (int i = 0; i < graph.n; i++) {
                for (int j = 0; j < graph.m; j++) {
                    if(graph.arrays.get(i).get(j) == 1){
                        return false;
                    }
                }
            }
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(m, n);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                if (st.nextToken().equals("1")) {
                   graph.addEdge(j, i);
                }
            }
        }
        int ans = 0;
        GraphService graphService = new GraphService(graph);
        while (true) {
            graphService.resetCheck();
            graphService.doBfs();
            graphService.removeCheese();

            ans ++;
            if (graphService.hasNoCheese()) {
                break;
            }
        }

        System.out.println(ans);
    }
}
