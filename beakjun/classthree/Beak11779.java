package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak11779 {
    public static class Node implements Comparable<Node>{
        int e;
        int cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static class Matrix {
        List<List<Node>> arrays;
        public Matrix(int size) {
            arrays = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<Node> rows = new ArrayList<>();
                arrays.add(rows);
            }
        }
        public void addNode(int from, int to, int cost){
            arrays.get(from).add(new Node(to, cost));
        }
    }

    public static class MatrixService {
        private Matrix matrix;
        private List<Integer> distances;
        private List<Integer> routes;
        private List<Boolean> checks;
        public MatrixService(Matrix matrix) {
            this.matrix = matrix;
            distances = new ArrayList<>(Collections.nCopies(matrix.arrays.size(), Integer.MAX_VALUE));
            routes = new ArrayList<>(Collections.nCopies(matrix.arrays.size(), -1));
            checks = new ArrayList<>(Collections.nCopies(matrix.arrays.size(), false));
        }
        public void findShortestDirection(int from){
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(from, 0));
            distances.set(from, 0);
            routes.set(from, from);

            while (!queue.isEmpty()) {
                Node cur = queue.remove();

                if (!checks.get(cur.e)) {
                    checks.set(cur.e, true);
                } else {
                    continue;
                }

                for (Node next : matrix.arrays.get(cur.e)) {
                    if (distances.get(next.e) > distances.get(cur.e) + next.cost) {
                        distances.set(next.e, distances.get(cur.e) + next.cost);
                        queue.add(new Node(next.e, distances.get(next.e)));
                        routes.set(next.e, cur.e);
                    }
                }
            }
        }

        public List<Integer> getShortestPath(int to, int from) {
            List<Integer> path = new ArrayList<>();
            for (int at = to; at != from; at = routes.get(at)) {
                path.add(at);
            }
            path.add(from);
            Collections.reverse(path);
            return path;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Matrix matrix = new Matrix(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            matrix.addNode(
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()));

        }
        MatrixService matrixService = new MatrixService(matrix);

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken()) - 1;
        int to = Integer.parseInt(st.nextToken()) - 1;

        matrixService.findShortestDirection(from);

        System.out.println(matrixService.distances.get(to));

        List<Integer> path = matrixService.getShortestPath(to, from);
        System.out.println(path.size());

        for (int num : path) {
            System.out.print(num + 1 + " ");
        }
    }
}
