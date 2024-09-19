package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class rudolphsrebellion {
    static final int MAX_N = 51;
    static final int MAX_P = 31;

    static class Pair {
        int val1, val2;
        Pair (int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }
    static class Tuple implements Comparable<Tuple> {
        int val1, val2, val3;
        Tuple (int val1, int val2, int val3) {
            this.val1 = val1;
            this.val2 = val2;
            this.val3 = val3;
        }
        @Override
        public int compareTo(Tuple o) {
            if (this.val1 != o.val1) {
                return Integer.compare(this.val1, o.val1);
            }
            if (this.val2 != o.val2) {
                return Integer.compare(this.val2, o.val2);
            }
            return Integer.compare(this.val3, o.val3);
        }
    }

    static int n, m ,p, c, d;
    static int[] points = new int[MAX_P];
    static Map<Integer, Pair> pos = new HashMap<>();
    static Pair rudolph = new Pair(0, 0);

    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[] isActive = new boolean[MAX_P];
    static int[] stun = new int[MAX_P];

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static boolean isInrange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();

        rudolph.val1 = sc.nextInt();
        rudolph.val2 = sc.nextInt();
        board[rudolph.val1][rudolph.val2] = -1;

        for (int i = 1; i <= p; i++) {
            int id = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            pos.put(id , new Pair(x, y));

            board[x][y] = id;
            isActive[id] = true;
        }

        for(int t = 1; t <= m; t++) {
            int closestX = 100000;
            int closestY = 100000;
            int closestId = 0;

            for (int i = 1; i <= p; i++) {
                if (!isActive[i]) {
                    continue;
                }

                Tuple currentBest = new Tuple((closestX - rudolph.val1) * (closestX - rudolph.val1) + (closestY - rudolph.val2) * (closestY - rudolph.val2), -closestX, -closestY);
                Tuple currentValue = new Tuple((pos.get(i).val1 - rudolph.val1) * (pos.get(i).val1 - rudolph.val1) + (pos.get(i).val2 - rudolph.val2) * (pos.get(i).val2 - rudolph.val2), -pos.get(i).val1, -pos.get(i).val2);

                if (currentValue.compareTo(currentBest) < 0) {
                    closestX = pos.get(i).val1;
                    closestY = pos.get(i).val2;
                    closestId = i;
                }
            }

            if (closestId != 0) {
                Pair prevRudolph = new Pair(rudolph.val1, rudolph.val2);
                int moveX = 0;
                if (closestX > rudolph.val1) moveX = 1;
                else if(closestX < rudolph.val1) moveX = -1;

                int moveY = 0;
                if (closestY > rudolph.val2) moveY = 1;
                else if(closestY < rudolph.val2) moveY = -1;

                rudolph.val1 += moveX;
                rudolph.val2 += moveY;
                board[prevRudolph.val1][prevRudolph.val2] = 0;

                if (rudolph.val1 == closestX && rudolph.val2 == closestY) {
                    int firstX = closestX + moveX * c;
                    int firstY = closestY + moveY * c;
                    int lastX = firstX;
                    int lastY = firstY;

                    stun[closestId] = t + 1;

                    while (isInrange(lastX, lastY) && board[lastX][lastY] > 0) {
                        lastX += moveX;
                        lastY += moveY;
                    }

                    while (!(lastX == firstX && lastY == firstY)) {
                        int beforeX = lastX - moveX;
                        int beforeY = lastY - moveY;

                        if (!isInrange(beforeX, beforeY)) break;

                        int id = board[beforeX][beforeY];

                        if (!isInrange(lastX, lastY)) {
                            isActive[id] = false;
                        } else {
                            board[lastX][lastY] = board[beforeX][beforeY];
                            pos.put(id, new Pair(lastX, lastY));
                        }
                        lastX = beforeX;
                        lastY = beforeY;
                    }

                    points[closestId] += c;
                    pos.put(closestId, new Pair(firstX, firstY));
                    if (isInrange(firstX, firstY)) {
                        board[firstX][firstY] = closestId;
                    } else {
                        isActive[closestId] = false;
                    }
                }
            }

            board[rudolph.val1][rudolph.val2] = -1;

            for (int i = 1; i <= p; i++) {
                if (!isActive[i] || stun[i] >= t) continue;
                int minDist = (pos.get(i).val1 - rudolph.val1) * (pos.get(i).val1 - rudolph.val1) + (pos.get(i).val2 - rudolph.val2) * (pos.get(i).val2 - rudolph.val2);
                int moveDir = -1;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = pos.get(i).val1 + dx[dir];
                    int ny = pos.get(i).val2 + dy[dir];

                    if (!isInrange(nx, ny) || board[nx][ny] > 0) continue;

                    int dist = (nx - rudolph.val1) * (nx - rudolph.val1) + (ny - rudolph.val2) * (ny - rudolph.val2);
                    if (dist < minDist) {
                        minDist = dist;
                        moveDir = dir;
                    }
                }

                if (moveDir != -1) {
                    int nx = pos.get(i).val1 + dx[moveDir];
                    int ny = pos.get(i).val2 + dy[moveDir];

                    if (nx == rudolph.val1 && ny == rudolph.val2) {
                        stun[i] = t + 1;

                        int moveX = -dx[moveDir];
                        int moveY = -dx[moveDir];

                        int firstX = nx + moveX * d;
                        int firstY = ny + moveY * d;
                        int lastX = firstX;
                        int lastY = firstY;

                        if (d == 1) {
                            points[i] += d;
                        } else {
                            while (isInrange(lastX, lastY) && board[lastX][lastY] > 0) {
                                lastX += moveX;
                                lastY += moveY;
                            }

                            while (!(lastX == firstX && lastY == firstY)) {
                                int beforeX = lastX - moveX;
                                int beforeY = lastY - moveY;

                                if (!isInrange(beforeX, beforeY)) break;

                                int id = board[beforeX][beforeY];

                                if (!isInrange(lastX, lastY)) {
                                    isActive[id] = false;
                                } else {
                                    board[lastX][lastY] = board[beforeX][beforeY];
                                    pos.put(id, new Pair(lastX, lastY));
                                }

                                lastX = beforeX;
                                lastY = beforeY;
                            }

                            points[i] += d;
                            board[pos.get(i).val1][pos.get(i).val2] = 0;
                            pos.put(i, new Pair(firstX, firstY));

                            if (isInrange(firstX, firstY)) {
                                board[firstX][firstY] = i;
                            } else {
                                isActive[i] = false;
                            }
                        }
                    } else {
                        board[pos.get(i).val1][pos.get(i).val2] = 0;
                        pos.put(i, new Pair(nx, ny));
                        board[nx][ny] = i;
                    }
                }
            }

            for (int i = 1; i <= p; i++) {
                if (isActive[i]) points[i]++;
            }
        }

        for (int i = 1; i <= p; i++)
            System.out.print(points[i] + " ");
    }
}
