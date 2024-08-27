package classes.classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak10026 {
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

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
    public static class Matrix {
        private List<List<String>> arrays;
        private List<List<Integer>> checks;
        private List<List<Integer>> uncompletedChecks;
        private int size;

        public Matrix(int size) {
            arrays = new ArrayList<>();
            checks = new ArrayList<>();
            uncompletedChecks = new ArrayList<>();
            this.size = size;

            for (int i = 0; i < size; i++) {
                List<String> arrayRow = new ArrayList<>();
                List<Integer> checkRow = new ArrayList<>();
                List<Integer> uncompletedChecksRow = new ArrayList<>();

                for (int j = 0; j < size ; j++) {
                    arrayRow.add("");
                    checkRow.add(0);
                    uncompletedChecksRow.add(0);
                }

                arrays.add(arrayRow);
                checks.add(checkRow);
                uncompletedChecks.add(uncompletedChecksRow);
            }
        }
        public void addEdge(int x, int y, String val) {
            arrays.get(y).set(x, val);
        }
    }
    public static class MatrixService {
        private final Matrix matrix;
        public MatrixService(Matrix matrix) {
            this.matrix = matrix;
        }

        public void doBfs(int x, int y, int cnt){
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(x ,y));
            matrix.checks.get(y).set(x, cnt);

            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                x = pair.getX();
                y = pair.getY();
                String val = matrix.arrays.get(y).get(x);

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 <= nx && nx < matrix.size && 0 <= ny && ny < matrix.size
                        && matrix.arrays.get(ny).get(nx).equals(val)
                        && matrix.checks.get(ny).get(nx) == 0) {
                        queue.add(new Pair(nx, ny));
                        matrix.checks.get(ny).set(nx, cnt);
                    }
                }
            }
        }

        public void doUncompletedBfs(int x, int y, int cnt){
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(x ,y));
            matrix.uncompletedChecks.get(y).set(x, cnt);

            while (!queue.isEmpty()) {
                Pair pair = queue.remove();
                x = pair.getX();
                y = pair.getY();
                String val = matrix.arrays.get(y).get(x);

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 <= nx && nx < matrix.size && 0 <= ny && ny < matrix.size
                            && matrix.uncompletedChecks.get(ny).get(nx) == 0) {
                        if (matrix.arrays.get(ny).get(nx).equals(val)) {
                            queue.add(new Pair(nx, ny));
                            matrix.uncompletedChecks.get(ny).set(nx, cnt);
                        } else if (val.equals("R") && matrix.arrays.get(ny).get(nx).equals("G")) {
                            queue.add(new Pair(nx, ny));
                            matrix.uncompletedChecks.get(ny).set(nx, cnt);
                        } else if (val.equals("G") && matrix.arrays.get(ny).get(nx).equals("R")) {
                            queue.add(new Pair(nx, ny));
                            matrix.uncompletedChecks.get(ny).set(nx, cnt);
                        }
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Matrix matrix = new Matrix(n);

        String line;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix.addEdge(j, i, String.valueOf(line.charAt(j)));
            }
        }
        MatrixService matrixService = new MatrixService(matrix);
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.checks.get(i).get(j) == 0) {
                   matrixService.doBfs(j , i, ++cnt1);
                }
            }
        }

        int cnt2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.uncompletedChecks.get(i).get(j) == 0) {
                    matrixService.doUncompletedBfs(j , i, ++cnt2);
                }
            }
        }

        System.out.println(cnt1 + " " + cnt2);
    }
}
