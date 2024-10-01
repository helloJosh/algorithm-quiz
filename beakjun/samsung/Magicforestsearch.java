package samsung;

import java.util.*;

public class Magicforestsearch {
    public static final int MAX_L = 70;

    public static int r, c, k;
    public static int[][] a  = new int[MAX_L + 3][MAX_L];
    public static boolean[][] isExit = new boolean[MAX_L + 3][MAX_L];
    public static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    public static int answer = 0;

    public static boolean inRange(int y, int x) {
        return 3 <= y && y < r + 3 && 0 <= x && x < c;
    }

    public static void resetMap() {
        for (int i = 0 ; i < r + 3; i++) {
            for (int j = 0; j < c; j++) {
                a[i][j] = 0;
                isExit[i][j] = false;
            }
        }
    }

    public static boolean canGo(int y, int x){
        boolean flag = 0 <= x - 1 && x + 1 < c && y + 1 < r +3;
        flag = flag && (a[y - 1][x - 1] == 0);
        flag = flag && (a[y - 1][x] == 0);
        flag = flag && (a[y - 1][x + 1] == 0);
        flag = flag && (a[y][x - 1] == 0);
        flag = flag && (a[y][x] == 0);
        flag = flag && (a[y][x + 1] == 0);
        flag = flag && (a[y + 1][x] == 0);
        return flag;
    }

    public static int doBfs(int y, int x) {
        int result = y;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[MAX_L + 3][MAX_L];
        q.add(new int[]{y, x});
        visit[y][x] = true;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            for (int k = 0 ; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];

                if (inRange(ny, nx) && !visit[ny][nx] && (a[ny][nx] == a[cur[0]][cur[1]] || (a[ny][nx] != 0 && isExit[cur[0]][cur[1]]))) {
                    q.add(new int[]{ny, nx});
                    visit[ny][nx] = true;
                    result = Math.max(result, ny);
                }
            }
        }
        return result;
    }

    public static void down(int y, int x, int d, int id) {
        if (canGo(y + 1, x)) {
            down(y + 1, x, d, id);
        } else if (canGo(y + 1, x - 1)) {
            down(y + 1, x - 1, (d + 3) % 4, id);
        } else if (canGo(y + 1, x + 1)) {
            down(y + 1, x + 1, (d + 1) % 4, id);
        } else {
            if ( !inRange(y - 1, x - 1) || !inRange(y + 1, x + 1)) {
                resetMap();
            } else {
                a[y][x] = id;
                for (int k = 0 ; k < 4; k++) {
                    a[y + dy[k]][x + dx[k]] = id;
                }

                isExit[y + dy[d]][x + dy[d]] = true;
                answer += doBfs(y, x) - 3 + 1;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt();
        c = scanner.nextInt();
        k = scanner.nextInt();

        for (int id = 1; id <= k; id++) { // 골렘 번호 id
            int x = scanner.nextInt() - 1;
            int d = scanner.nextInt();
            down(0, x, d, id);
        }
        System.out.println(answer);

    }
}
