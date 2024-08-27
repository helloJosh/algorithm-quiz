package classes.classfive;

import java.io.*;
import java.util.*;

public class Beak2623 {
    public static List<List<Integer>> arrays;
    public static List<Integer> indegree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arrays = new ArrayList<>();

        for (int i = 0; i < n;i++) {
            List<Integer> array = new ArrayList<>();
            arrays.add(array);
        }

        indegree = new ArrayList<>(Collections.nCopies(n, 0));


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            int s = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 0; j < t - 1; j++) {
                int e = Integer.parseInt(st.nextToken()) - 1;
                indegree.set(e, indegree.get(e) + 1);
                arrays.get(s).add(e);
                s = e;
            }
        }

        ArrayList<Integer> result = topologySort(n);

        if(result.size() == n)  {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i) + 1);
            }
        } else {
            System.out.println("0");
        }
    }

    public static ArrayList<Integer> topologySort(int n) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if(indegree.get(i) == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            result.add(cur);

            for (int i = 0; i < arrays.get(cur).size(); i++) {
                int next = arrays.get(cur).get(i);
                indegree.set(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return result;
    }
}
