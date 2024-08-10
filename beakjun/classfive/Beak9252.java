package classfive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Beak9252 {
    public static List<List<Integer>> arrays = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] val1 = br.readLine().split("");
            String[] val2 = br.readLine().split("");

            init(val1.length, val2.length);

            for (int i = 1; i <= val1.length; i++) {
                for (int j = 1; j <= val2.length; j++) {
                    if (val1[i - 1].equals(val2[j - 1])) {
                        arrays.get(i).set(j, arrays.get(i - 1).get(j - 1) + 1);
                    } else {
                        arrays.get(i).set(j, Math.max(arrays.get(i - 1).get(j), arrays.get(i).get(j - 1)));
                    }
                }
            }
            System.out.println(arrays);
            System.out.println(arrays.get(val1.length).get(val2.length));
            viewHistory(val1, val1.length, val2.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(int n, int m) {
        for (int i = 0; i <= n; i++) {
            List<Integer> array = new ArrayList<>();
            for (int j = 0; j <= m; j++) {
                array.add(0);
            }
            arrays.add(array);
        }
    }

    public static void viewHistory(String[] line, int i, int j){
        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        while (i > 0 && j > 0) {
            if (arrays.get(i).get(j).equals(arrays.get(i - 1).get(j))) {
                i--;
            } else if (arrays.get(i).get(j).equals(arrays.get(i).get(j - 1))) {
                j--;
            } else {
                stack.push(line[i - 1]);
                i--;
                j--;
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        System.out.println(builder);
    }
}
