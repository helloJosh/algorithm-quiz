package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1707 {
    public static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }
    public static class Graph {
        private int size;
        private List<List<Integer>> arrays;
        private List<Pair> edges;

        public Graph(int x){
            size = x;
            arrays = new ArrayList<>();
            edges = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < x; j++) {
                    row.add(0);
                }
                arrays.add(row);
            }
        }
        public List<List<Integer>> getArrays() {
            return arrays;
        }
        public List<Pair> getEdges() {
            return edges;
        }
        public void addEdge(int x, int y) {
            arrays.get(x).set(y, 1);
            arrays.get(y).set(x, 1);
            edges.add(new Pair(x, y));
        }
    }
    public static class GraphService {
        private final List<Integer> flags;
        private final Graph graph;
        public GraphService(Graph graph) {
            this.graph = graph;
            flags = new ArrayList<>(Collections.nCopies(graph.size, 0));
        }

        public boolean doBfs(){
            Queue<Integer> queue = new LinkedList<>();

            for(int v = 0; v < graph.size ; v++) {
                if (flags.get(v) == 0) {
                    queue.add(v);
                    flags.set(v, 1);

                    while (!queue.isEmpty()) {
                        int val = queue.remove();
                        for (int i = 0; i < graph.size; i++) {
                            if (graph.getArrays().get(val).get(i) == 1) {
                                if (flags.get(i) == 0) {
                                    queue.add(i);
                                    flags.set(i, 3 - flags.get(val));
                                } else {
                                    if (flags.get(val) == flags.get(i))
                                        return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Graph> graphs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graphs.add(new Graph(v));

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int val1 = Integer.parseInt(st.nextToken());
                int val2 = Integer.parseInt(st.nextToken());
                graphs.get(i).addEdge(val1-1, val2-1);
            }
        }

        for (Graph graph : graphs) {
            GraphService graphService = new GraphService(graph);
            if (graphService.doBfs()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
