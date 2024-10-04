package samsung;

import java.io.*;
import java.util.*;

public class Rudolphsrebellion {
    public static final int MAX_N = 51;
    public static final int MAX_P = 31;
    public static int n, m, p, c, d;
    public static Map<Integer, Pair> santas = new HashMap<>();
    public static boolean[] active = new boolean[MAX_P];
    public static int[] stun = new int[MAX_P];
    public static int[][] board = new int[MAX_N][MAX_N];
    public static Pair rudolph = new Pair(0, 0);
    static int[] points = new int[MAX_P];
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};

    public static class Pair {
        int x;
        int y;
        public Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Tuple implements Comparable<Tuple>{
        int dist;
        int y;
        int x;
        public Tuple(int dist, int y, int x) {
            this.dist = dist;
            this.y = y;
            this.x = x;
        }
        @Override
        public int compareTo(Tuple o) {
            if (this.dist != o.dist) {
                return Integer.compare(this.dist, o.dist);
            }
            if (this.y != o.y) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
    public static boolean inRange(int y, int x) {
        return 1 <= y && y <= n && 1 <= x && x <= n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rudolph = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        board[rudolph.y][rudolph.x] = - 1;

        for (int i = 0; i < p ; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            santas.put(id, new Pair(sy, sx));
            board[sy][sx] = id;
            active[id] = true;
        }

        for (int t = 0; t <= m ; t++) {
            int closestX = Integer.MAX_VALUE;
            int closestY = Integer.MAX_VALUE;
            int closestId = 0;

            for (int i = 1; i < p; i++) {
                if(active[i]) continue;

                Pair cur = santas.get(i);
                Tuple currentBest = new Tuple((closestY - rudolph.y) * (closestY - rudolph.y) + (closestX - rudolph.x) * (closestX - rudolph.x), -closestY, -closestX);
                Tuple currentValue = new Tuple((cur.y - rudolph.y) * (cur.y - rudolph.y) + (cur.x - rudolph.x) * (cur.x - rudolph.x), -cur.y, -cur.x);

                if(currentValue.compareTo(currentBest) < 0) {
                    closestY = cur.y;
                    closestX = cur.x;
                    closestId = i;
                }
            }

            if (closestId != 0) {
                Pair prevR = new Pair(rudolph.y, rudolph.x);

                int dy = 0;
                if (closestY > rudolph.y) dy = 1;
                else if (closestY < rudolph.y) dy = -1;

                int dx = 0;
                if (closestX > rudolph.x) dx = 1;
                else if (closestX < rudolph.x) dx = -1;

                rudolph.y += dy;
                rudolph.x += dx;
                board[prevR.y][prevR.x] = 0 ;

                if (rudolph.y == closestY && rudolph.x == closestX) {
                    int ny = closestY + dy * c;
                    int nx = closestX + dx * c;
                    int finaly = ny;
                    int finalx = nx;

                    stun[closestId] = t + 1;

                    while (inRange(finaly, finalx) && board[finaly][finalx] > 0) {
                        finaly += dy;
                        finalx += dx;
                    }

                    while ( !(finaly == ny && finalx == nx)) {
                        int prevY = finaly - dy;
                        int prevX = finalx - dx;

                        if (!inRange(prevY, prevX)) break;

                        int id = board[prevY][prevX];

                        if (!inRange(finaly, finalx)) {
                            active[id] = false;
                        } else {
                            board[finaly][finalx] = board[prevY][prevX];
                            santas.put(id, new Pair(finaly, finalx));
                        }

                        finaly = prevY;
                        finalx = prevX;
                    }

                    points[closestId] += c;
                    santas.put(closestId, new Pair(ny, nx));
                    if (inRange(ny, nx)) {
                        board[ny][nx] = closestId;
                    } else {
                        active[closestId] = false;
                    }
                }
            }

            board[rudolph.y][rudolph.x] = -1;

            for (int i = 1; i <= p; i++) {
                if (!active[i] || stun[i] >= t) continue;

                Pair cur = santas.get(i);

                int minDist = (cur.y - rudolph.y) * (cur.y - rudolph.y) + (cur.x - rudolph.x) * (cur.x - rudolph.x);
                int moveDir = -1;

                for (int dir = 0; dir < 4; dir++) {
                    int ny = cur.y + dy[dir];
                    int nx = cur.x + dx[dir];

                    if (!inRange(ny, nx) && board[ny][nx] > 0) continue;

                    int dist = (ny - rudolph.y) * (ny - rudolph.y) + (nx - rudolph.x) * (nx - rudolph.x);

                    if (dist < minDist) {
                        minDist = dist;
                        moveDir = dir;
                    }
                }

                if (moveDir != -1) {
                    int ny = cur.y + dy[moveDir];
                    int nx = cur.x + dx[moveDir];

                    if (nx == rudolph.x && ny == rudolph.y) {
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
                            while (inRange(lastY, lastX) && board[lastY][lastX] > 0) {
                                lastX += moveX;
                                lastY += moveY;
                            }

                            while (!(lastX == firstX && lastY == firstY)) {
                                int beforeY = lastY - moveY;
                                int beforeX = lastX - moveX;

                                if (!inRange(beforeY, beforeX)) break;

                                int id = board[beforeY][beforeX];

                                if (!inRange(lastY, lastX)) {
                                    active[id] = false;
                                } else {
                                    board[lastY][lastX] = board[beforeY][beforeX];
                                    santas.put(id, new Pair(lastY, lastX));
                                }

                                lastY = beforeY;
                                lastX = beforeX;
                            }

                            points[i] += d;
                            board[cur.y][cur.x] = 0;
                            santas.put(i, new Pair(firstY, firstX));
                            if (inRange(firstY, firstX)) {
                                board[firstY][firstX] = i;
                            } else {
                                active[i] = false;
                            }
                        }
                    } else {
                        board[cur.y][cur.x] = 0;
                        santas.put(i, new Pair(ny, nx));
                        board[ny][nx] = i;
                    }
                }
            }
            for (int i = 1; i <= p; i++) {
                if (active[i])
                    points[i]++;
            }
        }

        for (int i = 1; i <= p; i++)
            System.out.print(points[i] + " ");

    }
}
