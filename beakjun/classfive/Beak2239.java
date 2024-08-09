package classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Beak2239 {
    public static List<List<Integer>> matrixs = new ArrayList<>();
    public static final int FIRST_ROW = 0;
    public static final int LAST_ROW = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = FIRST_ROW; i < LAST_ROW; i++) {
            List<Integer> matrix = new ArrayList<>();
            for (int j = FIRST_ROW; j < LAST_ROW; j++) {
                matrix.add(0);
            }
            matrixs.add(matrix);
        }

        for (int i = FIRST_ROW; i < LAST_ROW; i++) {
            String[] line = br.readLine().split("");

            List<Integer> matrix = new ArrayList<>();
            matrixs.add(matrix);

            for (int j = FIRST_ROW; j < LAST_ROW; j++) {
                matrixs.get(i).set(j, Integer.parseInt(line[j]));
            }
        }

    }
}
