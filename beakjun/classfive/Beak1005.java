package classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1005 {
    public static class Matrix {
        public List<List<Integer>> matrixes;

        public Matrix(int n) {
            matrixes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> matrix = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    matrix.add(0);
                }
                matrixes.add(matrix);
            }
        }
        public void addEdge(int from, int to){
            matrixes.get(to).set(from, 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        map.values().stream().toList();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            List<Integer> arrays = new ArrayList<>();
            Matrix matrix = new Matrix(n);

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arrays.add(Integer.parseInt(st.nextToken()));
            }
            
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                matrix.addEdge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            }

            int w = Integer.parseInt(br.readLine());

            System.out.println(arrays);
            System.out.println(matrix.matrixes);

        }
    }
}
