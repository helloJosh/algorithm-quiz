package classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1167 {
    public static class Node {
        int e;
        int cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }
    public static void doBfs(int n, List<Integer> checks, List<List<Node>> arrays){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        checks.set(n, 0);

        while (!queue.isEmpty()) {
            int dn = queue.remove();

            for (int i = 0; i < arrays.get(dn).size(); i++) {
                Node node = arrays.get(dn).get(i);
                if(checks.get(node.e) == -1) {
                    queue.add(node.e);
                    checks.set(node.e, checks.get(dn) + node.cost);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Node>> arrays = new ArrayList<>();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            arrays.add(new ArrayList<>());
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                arrays.get(from - 1).add(new Node(to - 1, cost));
            }
        }

        List<Integer> checks = new ArrayList<>(Collections.nCopies(t, -1));
        doBfs(0, checks, arrays);

        int maxIndex = 0;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < checks.size(); i++) {
            if (checks.get(i) > maxVal) {
                maxIndex = i;
                maxVal = checks.get(i);
            }
        }

        checks = new ArrayList<>(Collections.nCopies(t, -1));
        doBfs(maxIndex, checks, arrays);

        maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < checks.size(); i++) {
            if (checks.get(i) > maxVal) {
                maxVal = checks.get(i);
            }
        }

        System.out.println(maxVal);
    }
}
