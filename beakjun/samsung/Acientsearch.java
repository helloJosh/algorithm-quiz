package samsung;

import java.io.*;
import java.util.*;

public class Acientsearch {
    public static int MAX_L = 5;
    public static int MIN_L = 3;
    public static Queue<Integer> queue = new LinkedList<>();

    public static class Board {
        public int[][] a = new int[MAX_L][MAX_L];

        public Board() {
            for (int i = 0; i < MAX_L; i++) {
                for (int j = 0; j < MAX_L; j++) {
                    a[i][j] = 0;
                }
            }
        }
        public boolean inRange(int y, int x) {
            return 0 <= y && y < MAX_L && 0 <= x && x < MAX_L;
        }

        public Board turn(int y, int x, int count) {
            Board result = new Board();

            for (int i = 0; i < MAX_L; i++) {
                for (int j = 0; j < MAX_L; j++) {
                    result.a[i][j] = this.a[i][j];
                }
            }

            for (int i = 0; i < count; i++) {
                int temp = result.a[y + 1][x + 1];
                result.a[y + 1][x + 1] = result.a[y + 1][x - 1];
                result.a[y + 1][x - 1] = result.a[y - 1][x - 1];
                result.a[y - 1][x - 1] = result.a[y - 1][x + 1];
                result.a[y - 1][x + 1] = temp;

                int temp2 = result.a[y + 1][x];
                result.a[y + 1][x] = result.a[y][x - 1];
                result.a[y][x - 1] = result.a[y - 1][x];
                result.a[y - 1][x] = result.a[y][x + 1];
                result.a[y][x + 1] = temp2;
            }
            return result;
        }

        public int calculateScore () {
            int score = 0;
            boolean[][] visited = new boolean[MAX_L][MAX_L];
            int[] dy = {0, 1, 0, -1};
            int[] dx = {1, 0, -1, 0};

            for (int i = 0; i < MAX_L; i++) {
                for (int j = 0; j < MAX_L; j++) {
                    if (!visited[i][j]) {
                        Queue<int []> q = new LinkedList<>();
                        Queue<int []> trace = new LinkedList<>();
                        q.offer(new int[]{i, j});
                        trace.offer(new int[]{i, j});
                        visited[i][j] = true;

                        while (!q.isEmpty()) {
                            int[] cur = q.poll();
                            for (int k = 0; k < 4; k++) {
                                int ny = cur[0] + dy[k];
                                int nx = cur[1] + dx[k];

                                if (inRange(ny, nx) && a[ny][nx] == a[cur[0]][cur[1]] && !visited[ny][nx] ) {
                                    q.offer(new int[]{ny, nx});
                                    trace.offer(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                }
                            }
                        }

                        if (trace.size() >= 3) {
                            score += trace.size();
                            while (!trace.isEmpty()) {
                                int[] t = trace.poll();
                                a[t[0]][t[1]] = 0;
                            }
                        }
                    }
                }
            }

            return score;
        }

        public void fill(Queue<Integer> queue){
            for (int j = 0; j < MAX_L; j++) {
                for (int i = MAX_L - 1; i >= 0; i--) {
                    if (a[i][j] == 0 && !queue.isEmpty()) {
                        a[i][j] = queue.poll();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        Board board = new Board();

        for (int i = 0; i < MAX_L; i++) {
            for (int j = 0; j < MAX_L; j++) {
                board.a[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            queue.offer(sc.nextInt());
        }

        while (k-- > 0) {
            int maxScore = 0;
            Board maxBoard = null;

            for (int cnt = 1; cnt <= 3; cnt++) {
                for (int x = 1; x < 4; x++) {
                    for (int y = 1; y < 4; y++) {
                        Board rotated = board.turn(y, x, cnt);
                        int score = rotated.calculateScore();

                        if (maxScore < score) {
                            maxScore = score;
                            maxBoard = rotated;
                        }
                    }
                }
            }
            System.out.println(maxScore);

            if (maxBoard == null) {
                break;
            }

            board = maxBoard;

            while (true) {
                board.fill(queue);
                int newScore = board.calculateScore();
                if (newScore == 0) break;
                maxScore += newScore;
            }

            System.out.println(maxScore + " ");
        }

    }
}
