package classes.classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1197 {
    public static List<List<Node>> arrays = new ArrayList<>();
    public static List<Boolean> booleans = new ArrayList<>(Collections.nCopies(10000, false));
    public static class Node implements Comparable<Node>{
        int e;
        int cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static int doPrim(int start) {
        int total = 0;
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int e = node.e;
            int cost = node.cost;

            if (booleans.get(e)) {
                continue;
            }
            booleans.set(e, true);
            total += cost;

            for (Node next : arrays.get(e)) {
                if (!booleans.get(next.e)) {
                    queue.add(next);
                }
            }
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < v; i++) {
            arrays.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arrays.get(from - 1).add(new Node(to - 1, cost));
            arrays.get(to - 1).add(new Node(from - 1, cost));
        }

        System.out.println(doPrim(0));
    }

}
