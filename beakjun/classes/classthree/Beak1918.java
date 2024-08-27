package classes.classthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Beak1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        String line = br.readLine();

        boolean flag = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                if(line.charAt(i) == '(' || line.charAt(i) == ')'){
                    break;
                }

                stack.add(c);
                if (line.charAt(i+1) == '(') {
                    flag = true;
                }
                if (line.charAt(i+1) == ')') {
                    flag = false;
                }

                if (!flag && (c == '*' || c =='/')) {
                   while (!stack.isEmpty()) {
                       sb.append(stack.pop());
                   }
                }
            } else {
                sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String ans = sb.toString();
        ans = ans.replace("(","");
        ans = ans.replace(")","");
        System.out.println(ans);
    }
}
