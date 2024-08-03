package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1697 {
    public static List<Integer> subinCheck = new ArrayList<>(Collections.nCopies(200000, 0));
    public static void doBfs(int x, int goal){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        subinCheck.set(x, 0);

        while (!queue.isEmpty()) {
            x = queue.remove();
            int nx = x+1;
            if(0 <= nx && nx < 100000
            && subinCheck.get(nx) == 0) {
                queue.add(nx);
                subinCheck.set(nx, subinCheck.get(x) + 1);
                if(nx == goal) {
                    return;
                }
            }
            nx = x-1;
            if(0 <= nx && nx < 100000
                    && subinCheck.get(nx) == 0) {
                queue.add(nx);
                subinCheck.set(nx, subinCheck.get(x) + 1);
                if(nx == goal) {
                    return;
                }
            }
            nx = 2*x;
            if(0 <= nx && nx < 100000
                    && subinCheck.get(nx) == 0) {
                queue.add(nx);
                subinCheck.set(nx, subinCheck.get(x) + 1);
                if(nx == goal) {
                    return;
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(n);
        System.out.println(k);
        doBfs(n, k);
        System.out.println(subinCheck.get(k));


    }
}
