package sw2.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Beak2529 {
    public static List<Integer> maxNums = new ArrayList<>();
    public static List<Integer> minNums = new ArrayList<>();
    public static List<String> array;
    public static List<Boolean> maxChecks;
    public static List<Boolean> minChecks;
    public static long maxAns = Long.MIN_VALUE;
    public static long minAns = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        array = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            array.add(st.nextToken());
            minNums.add(i);
            maxNums.add(9 - i);
        }
        minNums.add(n);
        maxNums.add(9 - n);
        maxChecks = new ArrayList<>(Collections.nCopies(n + 1, false));
        minChecks = new ArrayList<>(Collections.nCopies(n + 1, false));

        List<Integer> curs1 = new ArrayList<>();
        List<Integer> curs2 = new ArrayList<>();

        doDfs1(curs1);
        doDfs2(curs2);

        System.out.println(maxAns);
        System.out.println(minAns);

    }
    public static void doDfs1(List<Integer> curs){
        if (curs.size() == minNums.size()) {
            for (int i = 0 ; i < array.size(); i++) {
                if (array.get(i).equals(">")) {
                    if(curs.get(i) < curs.get(i + 1)) {
                        return;
                    }
                } else {
                    if(curs.get(i) > curs.get(i + 1)) {
                        return;
                    }
                }
            }
            int val = 1;
            long temp = 0;
            for (int i = curs.size() - 1; i >= 0; i--) {
                temp += (long)curs.get(i) * (long)val;
                val *= 10;
            }
            if (temp < minAns) {
                minAns = temp;
            }
            return;
        }
        for (int i = 0; i < minNums.size(); i++) {
            if (!minChecks.get(i)) {
                minChecks.set(i, true);
                curs.add(minNums.get(i));
                doDfs1(curs);
                curs.remove(minNums.get(i));
                minChecks.set(i, false);
            }
        }
    }
    public static void doDfs2(List<Integer> curs){
        if (curs.size() == maxNums.size()) {
            for (int i = 0 ; i < array.size(); i++) {
                if (array.get(i).equals(">")) {
                    if(curs.get(i) < curs.get(i + 1)) {
                        return;
                    }
                } else {
                    if(curs.get(i) > curs.get(i + 1)) {
                        return;
                    }
                }
            }
            int val = 1;
            long temp = 0;
            for (int i = curs.size() - 1; i >= 0; i--) {
                temp += (long)curs.get(i) * (long)val;
                val *= 10;
            }
            if (temp > maxAns) {
                maxAns = temp;
            }
            return;
        }
        for (int i = 0; i < maxNums.size(); i++) {
            if (!maxChecks.get(i)) {
                maxChecks.set(i, true);
                curs.add(maxNums.get(i));
                doDfs2(curs);
                curs.remove(maxNums.get(i));
                maxChecks.set(i, false);
            }
        }
    }
}
