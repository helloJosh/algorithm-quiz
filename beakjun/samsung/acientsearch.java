package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class acientsearch {
    static final int nLarge = 5;
    static final int nSmall = 3;

    static class Board {
        int[][] a = new int[nLarge][nLarge];

        public Board() {
            for (int i = 0; i < nLarge; i++) {
                for (int j = 0; j <nLarge; j++) {
                    a[i][j] = 0;
                }
            }
        }

        public boolean inRange(int y, int x) {
            return 0 <= y && y < nLarge && 0 <= x && x < nLarge;
        }

        public Board rotate(int sy, int sx, int cnt) {
            Board result = new Board();
            for (int i = 0; i < nLarge; i++) {
                for (int j = 0; j < nLarge; j++) {
                    result.a[i][j] = this.a[i][j];
                }
            }

            for (int i = 0; i < cnt; i++) {
                int tmp = result.a[sy + 0][sx + 2];
                result.a[sy + 0][sx + 2] = result.a[sy + 0][sx + 0];
                result.a[sy + 0][sx + 0] = result.a[sy + 2][sx + 0];
                result.a[sy + 2][sx + 0] = result.a[sy + 2][sx + 2];
                result.a[sy + 2][sx + 2] = tmp;
                tmp = result.a[sy + 1][sx + 2];
                result.a[sy + 1][sx + 2] = result.a[sy + 0][sx + 1];
                result.a[sy + 0][sx + 1] = result.a[sy + 1][sx + 0];
                result.a[sy + 1][sx + 0] = result.a[sy + 2][sx + 1];
                result.a[sy + 2][sx + 1] = tmp;
            }
            return result;
        }

        public int calScore() {
            int score = 0;
            boolean[][] visit = new boolean[nLarge][nLarge];
            int[] dy = { 0, 1, 0, -1}, dx = {1, 0, -1, 0};

            for (int i = 0; i < nLarge; i++) {
                for (int j = 0; j < nLarge; j++) {
                    if (!visit[i][j]) {
                        Queue<int[]> q = new LinkedList<>();
                        Queue<int[]> trace = new LinkedList<>();

                        q.add(new int[]{i, j});
                        trace.add(new int[]{i, j});
                        visit[i][j] = true;
                        while (!q.isEmpty()) {
                            int[] cur = q.remove();
                            for (int k = 0 ; k < 4; k++) {
                                int ny = cur[0] + dy[k], nx = cur[1] +dx[k];
                                if (inRange(ny, nx) && a[ny][nx] == a[cur[0]][cur[1]] && !visit[ny][nx]) {
                                    q.add(new int[]{ny, nx});
                                    trace.add(new int[]{ny, nx});
                                    visit[ny][nx] = true;
                                }
                            }
                        }

                        if (trace.size() >= 3) {
                            score += trace.size();
                            while (!trace.isEmpty()) {
                                int[] t = trace.remove();
                                a[t[0]][t[1]] = 0;
                            }
                        }
                    }
                }
            }

            return score;
        }

        public void fill(Queue<Integer> queue) {
            for (int j = 0; j < nLarge; j++) {
                for (int i = nLarge - 1; i >= 0; i--) {
                    if (a[i][j] == 0 && !queue.isEmpty()) {
                        a[i][j] = queue.remove();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        Board board = new Board();

        for (int i = 0; i < nLarge; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nLarge; j++) {
                board.a[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i= 0; i < m; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }

        while (k-- > 0) {
            int maxScore = 0;
            Board maxScoreBoard = null;

            for (int cnt = 1; cnt <= 3; cnt++) {
                for (int sx = 0; sx <= nLarge - nSmall; sx++) {
                    for (int sy = 0; sy <= nLarge - nSmall; sy++) {
                        Board rotated = board.rotate(sy, sx, cnt);
                        int score = rotated.calScore();
                        if (maxScore < score) {
                            maxScore = score;
                            maxScoreBoard = rotated;
                        }
                    }
                }
            }

            if (maxScoreBoard == null) {
                break;
            }

            board = maxScoreBoard;
            while (true) {
                board.fill(q);
                int newScore = board.calScore();
                if (newScore == 0) {
                    break;
                }
                maxScore += newScore;
            }

            System.out.println(maxScore + " ");
        }
    }
}
