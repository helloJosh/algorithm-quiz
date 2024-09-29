package samsung;

import java.io.*;
import java.util.*;

public class magicforestsearch {
    public static Set<String> set = new HashSet<>();
    public static void doBfs(String cur, String letters, boolean[] check, int index) {
        if (index == letters.length()) {
            set.add(cur);
            //System.out.println(cur);
        }
        for (int i = 0 ; i < letters.length(); i++) {
            if (!check[i]) {
                check[i] = true;
                doBfs(cur + letters.charAt(i), letters, check, index + 1);
                check[i] = false;
            }
        }
    }

    public static String[] solution(String letters) {
        String[] answer = {};
        boolean[] check = new boolean[letters.length()];
        String cur = "";
        doBfs(cur, letters, check, 0);

        for (Object s : set.toArray()){
            System.out.println(s);
        }
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("abca"));
    }
}
