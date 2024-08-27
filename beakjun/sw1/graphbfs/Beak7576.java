package sw1.graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak7576 {
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};

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
    public static class Matrix{
        private List<List<Integer>> arrays;
        private List<List<Integer>> checks;
        private int x;
        private int y;

        public Matrix(int n, int m) {
            arrays = new ArrayList<>();
            checks = new ArrayList<>();
            y = m; x = n;

            for (int i = 0; i < m; i++) {
                List<Integer> arraysRow = new ArrayList<>();
                List<Integer> checksRow = new ArrayList<>();

                for (int j = 0; j < n ; j++) {
                    arraysRow.add(0);
                    checksRow.add(0);
                }
                arrays.add(arraysRow);
                checks.add(checksRow);
            }
        }
        public void addEdge(int x, int y, int val) {
            arrays.get(y).set(x, val);
        }
    }
    public static class MatrixService {
        private final Matrix matrix;
        public MatrixService(Matrix matrix) {
            this.matrix = matrix;
        }

        public void doBfs(int x, int y) {
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(x, y));
            matrix.checks.get(y).set(x, 1);

            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                x = pair.getX();
                y = pair.getY();

                for (int i = 0; i < 4; i++ ) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (0 <= nx && nx < matrix.x && 0 <= ny && ny < matrix.y
                        && matrix.arrays.get(ny).get(nx) == 0
                        && matrix.checks.get(ny).get(nx) == 0) {
                        queue.add(new Pair(nx, ny));
                        matrix.checks.get(ny).set(nx, matrix.checks.get(ny).get(nx) + 1);
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

        for (int i = 0; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n ; j++) {
                matrix.addEdge(j, i, Integer.parseInt(st.nextToken()));
            }
        }

        List<Pair> startPoints = new ArrayList<>();
        List<Matrix> matrices = new ArrayList<>();

        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                if (matrix.arrays.get(i).get(j) == 1) {
                    startPoints.add(new Pair(j, i));
                    Matrix copyMatrix = new Matrix(n, m);
                    copyMatrix.checks = new ArrayList<>();
                    copyMatrix.checks.addAll(matrix.checks);
                    matrices.add(copyMatrix);
                }
            }
        }

        for (int i = 0; i <startPoints.size() ; i++ ) {
            Pair pair = startPoints.get(i);
            MatrixService matrixService = new MatrixService(matrices.get(i));
            matrixService.doBfs(pair.getX(), pair.getY());
        }


        System.out.println(matrix.arrays);

        for (int i = 0; i <startPoints.size() ; i++ ) {
            System.out.println(matrices.get(i).checks);
        }
    }
}
