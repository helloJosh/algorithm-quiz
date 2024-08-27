package sw1.graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1260 {
    public static class Pair{
        private int x;
        private int y;
        public Pair(int x, int y){
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
    public static class Graph{
        private List<List<Integer>> arrays;
        private List<Pair> edges;

        public Graph(int size){
            arrays = new ArrayList<>();
            edges = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<Integer> rows = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    rows.add(0);
                }
                arrays.add(rows);
            }
        }
        public void addEdge(int x, int y){
            arrays.get(x).set(y, 1);
            arrays.get(y).set(x, 1);
            edges.add(new Pair(x,y));
        }
        public List<Pair> getEdges(){
            return edges;
        }
        public List<List<Integer>> getArrays(){
            return arrays;
        }
    }
    public static class GraphService{
        private final Graph graph;
        public GraphService(Graph graph){
            this.graph = graph;
        }

        public void doBfs(int v){
            int size = graph.getArrays().size();

            List<Boolean> checks = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));
            Queue<Integer> queue = new LinkedList<>();
            StringBuffer sb = new StringBuffer();

            queue.add(v);
            checks.set(v, Boolean.TRUE);

            while (!queue.isEmpty()) {
                int val1 = queue.remove();
                sb.append(val1+1).append(" ");

                for (int i = 0; i < size ;i++) {
                    if (graph.getArrays().get(val1).get(i) == 1
                        && checks.get(i) == Boolean.FALSE) {
                        queue.add(i);
                        checks.set(i, Boolean.TRUE);
                    }
                }
            }
            System.out.println(sb);

        }

        public void doDfs(int v){
            int size = graph.getArrays().size();

            List<Boolean> checks = new ArrayList<>(Collections.nCopies(size, Boolean.FALSE));
            Stack<Integer> stack = new Stack<>();
            StringBuffer sb = new StringBuffer();

            stack.add(v);
            checks.set(v, Boolean.TRUE);
            sb.append(v+1).append(" ");

            while (!stack.isEmpty()) {
                int val1 = stack.peek();
                boolean hasAdjacent = false;


                for (int i = 0; i < size ;i++) {
                    if (graph.getArrays().get(val1).get(i) == 1
                            && checks.get(i) == Boolean.FALSE) {
                        stack.add(i);
                        checks.set(i, Boolean.TRUE);
                        sb.append(i+1).append(" ");
                        hasAdjacent = true;
                        break;
                    }
                }

                if(!hasAdjacent){
                    stack.pop();
                }
            }

            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            graph.addEdge(val1-1, val2-1);
        }

        GraphService graphService = new GraphService(graph);
        graphService.doDfs(v-1);
        graphService.doBfs(v-1);
    }
}
