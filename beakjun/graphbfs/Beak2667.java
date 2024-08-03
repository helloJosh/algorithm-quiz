package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak2667 {
    public static final int[] dx = {1 ,0, -1, 0};
    public static final int[] dy = {0 ,1, 0, -1};

    public static class Pair{
        private int x;
        private int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    public static class Graph{
        private List<List<Integer>> arrays;
        private List<List<Integer>> checks;
        private int size;

        public Graph(int n){
            size = n;
            arrays = new ArrayList<>();
            checks = new ArrayList<>();
            for (int i = 0 ; i < n; i++) {
                List<Integer> row = new ArrayList<>();
                List<Integer> checkRow = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add(0);
                    checkRow.add(0);
                }
                arrays.add(i, row);
                checks.add(i, checkRow);
            }
        }
        public void addEdge(int x, int y){
            arrays.get(x).set(y, 1);
        }
    }
    public static class GraphService{
        private final Graph graph;
        public GraphService(Graph graph){
            this.graph = graph;
        }

        public void doBfs(int x, int y, int ans){
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(x, y));
            graph.checks.get(x).set(y, ans);
            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                int valx = pair.getX();
                int valy = pair.getY();
                for (int i = 0; i < 4; i++) {
                    int nx = valx + dx[i];
                    int ny = valy + dy[i];
                    if (0 <= nx && nx < graph.size && 0 <= ny && ny < graph.size
                            && graph.arrays.get(nx).get(ny) == 1
                            && graph.checks.get(nx).get(ny) == 0) {
                        queue.add(new Pair(nx, ny));
                        graph.checks.get(nx).set(ny, ans);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        Graph graph = new Graph(n);

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '1') {
                    graph.addEdge(i, j);
                }
            }
        }
        GraphService graphService = new GraphService(graph);

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++ ) {
                if(graph.checks.get(i).get(j) == 0 && graph.arrays.get(i).get(j) == 1) {
                    graphService.doBfs(i, j, ++ans);
                }
            }
        }
//        System.out.println(graph.arrays);
//        System.out.println(graph.checks);
        System.out.println(ans);
        for (int i = 1; i < ans+1; i++) {
            int count = 0;
            for ( int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (graph.checks.get(j).get(k) == i) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
