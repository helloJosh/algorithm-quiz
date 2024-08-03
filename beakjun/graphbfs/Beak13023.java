package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Beak13023 {
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
        public int getY(){
            return y;
        }
    }
    public static class Graph {
        private List<List<Integer>> arrays;
        private List<Pair> edges;
        private int size;

        public Graph(int size) {
            arrays = new ArrayList<>();
            edges = new ArrayList<>();
            this.size = size;

            for (int i = 0; i < size; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    row.add(0);
                }
                arrays.add(row);
            }
        }

        public void addEdge(int i, int j) {
            arrays.get(i).set(j, 1);
            arrays.get(j).set(i, 1);
            edges.add(new Pair(i,j));
        }
        public boolean check(int i, int j){
            return arrays.get(i).get(j) == 1;
        }
        public List<List<Integer>> getArrays() {
            return arrays;
        }
    }

    public static class GraphService {
        private Graph graph;

        public GraphService(Graph graph) {
            this.graph = graph;
        }

        private boolean check() {
            List<List<Integer>> arrays = graph.getArrays();

            for (Pair firstPair : graph.edges) {
                for (Pair secondPair : graph.edges) {
                    int a = firstPair.getX();
                    int b = firstPair.getY();
                    int c = secondPair.getX();
                    int d = secondPair.getY();

                    if (a == b || a == c || a == d || b == c || b == d || c == d)
                        continue;

                    if (!graph.check(b,c))
                        continue;

                    List<Integer> filteredArrays = new ArrayList<>();

                    for (int i = 0; i < arrays.get(d).size(); i ++){
                        if(arrays.get(d).get(i) == 1)
                            filteredArrays.add(i);
                    }

                    for (Integer e : filteredArrays) {
                        if (a == e || b == e || c == e || d == e)
                            continue;

                        return true;
                    }

                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        for (int i = 0; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addEdge(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        GraphService graphService = new GraphService(graph);

        if (graphService.check()) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}
