package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class magicforestsearch {
    public static final int MAX_L = 70;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int r,c,k;
    public static int[][] a = new int[MAX_L + 3][MAX_L];
    public static boolean[][] isExit = new boolean[MAX_L + 3][MAX_L];
    public static int answer = 0;

    public static boolean inRange(int y, int x) {
        return 3 <= y && y < r + 3 && 0 <= x && x < c;
    }
    public static void printA() {
        for (int i = 0; i < r + 3;i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
    public static void init() {
        for (int i = 0; i < r + 3;i++) {
            for (int j = 0; j < c; j++) {
                a[i][j] = 0;
                isExit[i][j] = false;
            }
        }
    }

    public static boolean check(int y, int x) {
        boolean flag = 0 <= x - 1 && x + 1 < c && y + 1 < r + 3;
        flag = flag && (a[y - 1][x - 1] == 0);
        flag = flag && (a[y - 1][x] == 0);
        flag = flag && (a[y - 1][x + 1] == 0);
        flag = flag && (a[y][x - 1] == 0);
        flag = flag && (a[y][x] == 0);
        flag = flag && (a[y][x + 1] == 0);
        flag = flag && (a[y + 1][x] == 0);
        return flag;
    }

    public static void down(int y, int x, int d, int id) {
        if (check(y + 1 , x)) {
            down(y + 1 , x, d, id);
        } else if (check(y + 1, x - 1)) {
            down(y + 1, x - 1, (d + 3) % 4, id);
        } else if (check(y + 1, x + 1)) {
            down(y + 1, x + 1, (d + 1) % 4 , id);
        } else {
            if (!inRange(y - 1, x - 1) || !inRange(y + 1, x + 1)) {
                init();
            } else {
                a[y][x] = id;
                for (int z = 0; z < 4; z++) {
                    a[y + dy[z]][x + dx[z]] = id;
                }
                isExit[y + dy[d]][x + dx[d]] = true;
                answer += bfs(y, x) - 3 + 1;
            }
        }
    }

    public static int bfs(int y, int x) {
        int result = y;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[MAX_L + 3][MAX_L];
        q.add(new int[]{y, x});
        visit[y][x] = true;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if (inRange(ny, nx)
                        && !visit[ny][nx]
                        && (a[ny][nx] == a[cur[0]][cur[1]] || (a[ny][nx] != 0 && isExit[cur[0]][cur[1]]))) {
                    q.add(new int[]{ny, nx});
                    visit[ny][nx] = true;
                    result = Math.max(result, ny);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            down(0, row, d, i);

        }
        System.out.println(answer);
    }
}
