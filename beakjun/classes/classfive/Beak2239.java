package classes.classfive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Beak2239 {
    public static List<List<Integer>> matrixs = new ArrayList<>();
    public static final int FIRST_ROW = 0;
    public static final int LAST_ROW = 9;
    public static final int LAST_BOX = 3;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            init();

            for (int i = FIRST_ROW; i < LAST_ROW; i++) {
                String[] line = br.readLine().split("");

                List<Integer> matrix = new ArrayList<>();
                matrixs.add(matrix);

                for (int j = FIRST_ROW; j < LAST_ROW; j++) {
                    matrixs.get(i).set(j, Integer.parseInt(line[j]));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        for (int i = FIRST_ROW; i < LAST_ROW; i++) {
            List<Integer> matrix = new ArrayList<>();
            for (int j = FIRST_ROW; j < LAST_ROW; j++) {
                matrix.add(0);
            }
            matrixs.add(matrix);
        }
    }

    public static void doDfs(int n) {
        if (n == 81) {
            return;
        }

        int x = n % LAST_ROW;
        int y = n / LAST_ROW;

        if (matrixs.get(y).get(x) != 0) {
            doDfs(n + 1);
        } else {
            for (int i = FIRST_ROW; i < LAST_ROW; i++) {
                if (!isValid(x, y, i)) {
                    continue;
                }
                matrixs.get(y).set(x, i);
                doDfs(n + 1);
                matrixs.get(y).set(x, 0);
            }
        }
    }

    public static boolean isValid(int x, int y, int n) {
        for (int i = FIRST_ROW; i < LAST_ROW; i++) {
            if (matrixs.get(y).get(x) == n || matrixs.get(i).get(x) == n) {
                return false;
            }
        }
        int boxY = y / LAST_BOX * LAST_BOX;
        int boxX = x / LAST_BOX * LAST_BOX;

        for (int i = boxY; i < boxY + LAST_BOX; i++) {
            for (int j = boxX; j < boxX + LAST_BOX; j++) {
                if(matrixs.get(i).get(j) == n) {
                    return false;
                }
            }
        }

        return true;
    }
}
