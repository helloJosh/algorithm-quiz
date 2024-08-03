package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak2178 {
    public static final int[] dx = {1, 0 , -1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getY() {
            return y;
        }
        public int getX() {
            return x;
        }
    }
    public static class Matrix {
        private List<List<Integer>> arrays;
        private List<List<Integer>> checks;
        private int n;
        private int m;

        public Matrix(int n, int m) {
            this.n = n;
            this.m = m;
            arrays = new ArrayList<>();
            checks = new ArrayList<>();
            for (int i = 0; i < n ; i++) {
                List<Integer> arraysRow = new ArrayList<>();
                List<Integer> checksRow = new ArrayList<>();
                for (int j = 0; j < m ; j++) {
                    arraysRow.add(0);
                    checksRow.add(0);
                }
                arrays.add(arraysRow);
                checks.add(checksRow);
            }
        }
        public void addEdge(int x, int y){
            arrays.get(x).set(y, 1);
        }
        public List<List<Integer>> getArrays() {
            return arrays;
        }
        public List<List<Integer>> getChecks() {
            return checks;
        }
    }
    public static class MatrixService {
        private final Matrix matrix;
        public MatrixService(Matrix matrix){
            this.matrix = matrix;
        }
        public void doBfs(){
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, 0));
            int count = 1;
            matrix.getChecks().get(0).set(0, count);

            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                int x = pair.getX();
                int y = pair.getY();

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (0 <= nx && nx < matrix.m && 0 <= ny && ny < matrix.n
                    && matrix.getChecks().get(ny).get(nx) == 0
                    && matrix.getArrays().get(ny).get(nx) == 1) {
                        queue.add(new Pair(nx, ny));
                        matrix.getChecks().get(ny).set(nx, matrix.getChecks().get(y).get(x) + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Matrix matrix = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m ; j++) {
                if (line.charAt(j) == '1') {
                    matrix.addEdge(i, j);
                }
            }
        }

        MatrixService matrixService = new MatrixService(matrix);
        matrixService.doBfs();
        System.out.println(matrix.arrays);
        System.out.println(matrix.checks);
        System.out.println(matrix.checks.get(n - 1).get(m - 1));

    }
}
