package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baek1759 {
    public static final List<Character> moList = List.of('a','e','i','o','u');
    public static void go(int n, List<Character> usableList, List<Character> passwordList, int i) {
        if (n == passwordList.size()) {
            int ja = 0;
            int mo = 0;
            for (Character c : passwordList) {
                if (moList.contains(c)) {
                    mo += 1;
                } else {
                    ja += 1;
                }
            }
            if (mo >= 1 && ja >= 2) {
                System.out.println(passwordList.toString());
                return;
            } else {
                return;
            }

        }
        if (i >= usableList.size()) {
            return;
        }
        
        
        passwordList.add(usableList.get(i));
        go(n, usableList, passwordList, i+1 );
        passwordList.remove(usableList.get(i));
        go(n,usableList,passwordList,i+1);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Character> a = new ArrayList<>();
        

        for (int i=0; i < m; i++) {
            a.add(st.nextToken().charAt(0));
        }

        Collections.sort(a);

        go(n, a, new ArrayList<Character>(), 0);

    }
}
