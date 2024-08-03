import java.util.*;

public class Main {
    static final int inf = -100000000;
    static List<Integer> t;
    static List<Integer> p;
    static int n;
    static int ans = 0;

    static void go(int day, int sum) {
        if (day == n + 1) {
            if (ans < sum) ans = sum;
            return;
        }
        if (day > n + 1) {
            return;
        }
        go(day + 1, sum);
        go(day + t.get(day), sum + p.get(day));
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = new ArrayList<>(n + 1);
        p = new ArrayList<>(n + 1);

        // ArrayList의 초기 값을 채우기 위해 0을 넣어줍니다.
        for (int i = 0; i <= n; i++) {
            t.add(0);
            p.add(0);
        }

        for (int i = 1; i <= n; i++) {
            t.set(i, sc.nextInt());
            p.set(i, sc.nextInt());
        }
        go(1, 0);
        System.out.println(ans);
    }
}