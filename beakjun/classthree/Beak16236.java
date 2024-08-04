package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beak16236 {
    public static final int[] dy = {-1, 0 , 0, 1};
    public static final int[] dx = {0, -1, 1, 0};

    public static class Pair {
        private int x;
        private int y;
        private int distance;
        public Pair(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    public static class Matrix {
        private List<List<Integer>> arrays;
        private int size;
        public Matrix(int size) {
            this.size = size;
            arrays = new ArrayList<>();
            for (int i = 0 ; i < size; i++) {
                List<Integer> arrayRow = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    arrayRow.add(0);
                }
                arrays.add(arrayRow);
            }
        }
        public void addEdge(int x, int y, int val){
            arrays.get(y).set(x, val);
        }
    }
    public static class MatrixGame {
        private final Matrix matrix;
        private int sharkSize;
        private int moveCount;
        private int fishCount;
        private List<List<Boolean>> visit;

        public MatrixGame(Matrix matrix) {
            this.matrix = matrix;
        }
        public int doGame(Pair pair){
            sharkSize = 2;
            moveCount = 0;
            fishCount = 0;

            while (true) {
                PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) ->
                        o1.distance != o2.distance ? Integer.compare(o1.distance, o2.distance) : (o1.y != o2.y ? Integer.compare(o1.y, o2.y) : Integer.compare(o1.x, o2.x))
                );
                visit = refreshVisit(matrix.size);

                queue.add(new Pair(pair.x, pair.y, 0));
                visit.get(pair.y).set(pair.x, true);
                boolean flag = false;

                while (!queue.isEmpty()) {
                    pair = queue.remove();

                    if (matrix.arrays.get(pair.y).get(pair.x) != 0
                            && matrix.arrays.get(pair.y).get(pair.x) < sharkSize){
                        matrix.arrays.get(pair.y).set(pair.x, 0);
                        fishCount += 1;
                        moveCount += pair.distance;
                        flag = true;
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx = pair.x + dx[i];
                        int ny = pair.y + dy[i];

                        if(0 <= nx && nx < matrix.size && 0 <= ny && ny < matrix.size
                            && !visit.get(ny).get(nx)
                            && matrix.arrays.get(ny).get(nx) <= sharkSize) {
                            queue.add(new Pair(nx, ny, pair.distance + 1));
                            visit.get(ny).set(nx, true);
                        }
                    }
                }
                if (!flag) {
                    return moveCount;
                }
                if (sharkSize == fishCount) {
                    sharkSize++;
                    fishCount = 0;
                }

            }
        }
        public List<List<Boolean>> refreshVisit(int size){
            List<List<Boolean>> visits = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<Boolean> visitsRow = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    visitsRow.add(Boolean.FALSE);
                }
                visits.add(visitsRow);
            }
            return visits;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        Matrix matrix = new Matrix(t);
        Pair startVal = null;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < t; j++) {
                int val = Integer.parseInt(st.nextToken());
                matrix.addEdge(j, i , val);
                if (val == 9) {
                    startVal = new Pair(j, i, 0);
                }
            }
        }
        MatrixGame matrixGame = new MatrixGame(matrix);

        System.out.println(matrix.arrays);
        System.out.println(matrixGame.doGame(startVal));
        System.out.println(matrixGame.sharkSize);
        System.out.println(matrix.arrays);
    }
}
