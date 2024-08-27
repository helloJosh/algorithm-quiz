package sw1.graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak11724 {
    public static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
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
    public static class Graph {
        private List<List<Integer>> arrays;
        private List<Pair> edges;
        public Graph(int size) {
            arrays = new ArrayList<>();
            edges = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    row.add(0);
                }
                arrays.add(row);
            }
        }
        public void addEdge(int x, int y){
            arrays.get(x).set(y, 1);
            arrays.get(y).set(x, 1);
            edges.add(new Pair(x,y));
        }
        public List<List<Integer>> getArrays(){
            return  arrays;
        }
        public List<Pair> getEdges(){
            return edges;
        }
    }
    public static class GraphService {
        private final List<Boolean> checks;
        private final Graph graph;
        public GraphService(Graph graph) {
            this.graph = graph;
            checks = new ArrayList<>(Collections.nCopies(graph.arrays.size(), Boolean.FALSE));
        }
        public void doBfs(int v) {
            Queue<Integer> queue = new LinkedList<>();

            queue.add(v);
            checks.set(v, Boolean.TRUE);

            while (!queue.isEmpty()) {
                int val = queue.remove();
                for (int i = 0; i < checks.size(); i++) {
                    if (graph.getArrays().get(val).get(i) == 1
                            && !checks.get(i)) {
                        queue.add(i);
                        checks.set(i, Boolean.TRUE);
                    }
                }
            }
        }
        public int countComponent() {
            int answer = 0;
            for (int i = 0; i < graph.getArrays().size(); i++) {
                if (!checks.get(i)) {
                    doBfs(i);
                    answer++;
                }
            }
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            graph.addEdge(val1-1, val2-1);
        }

        GraphService graphService = new GraphService(graph);
        System.out.println(graphService.countComponent());
    }
}
