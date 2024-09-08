package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class acientsearch {
    public static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}, dy= {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int k, m;
    public static int[][] a = new int[6][6];
    public static Queue<Integer> wall = new LinkedList<>();
    public static int[][] count = new int[6][6];
    public static boolean[][] check = new boolean[6][6];

    public static int[][] turn(int[][] tempA, int y, int x) {
        int[] temp = new int[10];
        for (int i = 0; i < 8; i++) {
            temp[i] = tempA[y + dy[i]][x + dx[i]];
        }
        for (int i = 0; i < 8; i++) {
            temp[9 - i] = temp[7 - i];
        }
        temp[1] = temp[9];
        temp[0] = temp[8];

        for (int i = 0; i < 8; i++) {
            tempA[y + dy[i]][x + dx[i]] = temp[i];
        }

        print(tempA);

        return tempA;
    }

    public static Queue<int[]> doBfs(int[][] tempA, int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> history = new LinkedList<>();
        int cnt = 1;
        queue.add(new int[]{y, x});
        history.add(new int[]{y, x});
        check[y][x] = true;
        count[y][x] = cnt;
        while (!queue.isEmpty()) {
            int[] p = queue.remove();
            for (int i = 0; i < 8; i += 2) {
                int ny = p[0] + dx[i];
                int nx = p[1] + dy[i];
                if (!check[ny][nx] && tempA[ny][nx] == tempA[y][x]) {
                    queue.add(new int[]{ny, nx});
                    history.add(new int[]{ny, nx});
                    count[ny][nx] = ++cnt;
                    check[ny][nx] = true;
                }
            }
        }

        if (cnt >= 3) {
            return history;
        } else {
            return new LinkedList<>();
        }
    }

    public static void get(List<int[]> list) {

    }
    public static void print(int[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 6; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            wall.add(Integer.parseInt(st.nextToken()));
        }

        int[][] tempA = turn(a, 3 ,3);

        print(count);

    }
}
