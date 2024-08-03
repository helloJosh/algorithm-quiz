package graphbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Beak14226 {
    public static List<List<Integer>> emojis = new ArrayList<>();
    public static void doBfs(int max){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(0);
        emojis.get(1).set(0, 0);

        while (!queue.isEmpty()) {
            int emojiCount = queue.remove();
            int clipCount = queue.remove();

            if (emojis.get(emojiCount).get(emojiCount) == -1) {
                emojis.get(emojiCount).set(emojiCount, emojis.get(emojiCount).get(clipCount) + 1);
                queue.add(emojiCount);
                queue.add(emojiCount);
            }

            if (emojiCount + clipCount <= max
            && emojis.get(emojiCount + clipCount).get(clipCount) == -1) {
                emojis.get(emojiCount + clipCount).set(clipCount, emojis.get(emojiCount).get(clipCount) + 1);
                queue.add(emojiCount + clipCount);
                queue.add(clipCount);
            }

            if (emojiCount - 1 >= 0
                    && emojis.get(emojiCount - 1).get(clipCount) == -1) {
                emojis.get(emojiCount - 1).set(clipCount, emojis.get(emojiCount).get(clipCount) + 1);
                queue.add(emojiCount - 1);
                queue.add(clipCount);
            }
        }
        int ans = -1;

        for (int i = 0; i <= max; i++) {
            if (emojis.get(max).get(i) != -1) {
                if (ans == -1 || ans > emojis.get(max).get(i)) {
                    ans = emojis.get(max).get(i);
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < n+1 ; i++) {
            List<Integer> clip = new ArrayList<>();
            for (int j = 0; j < n+1 ; j++) {
                clip.add(-1);
            }
            emojis.add(clip);
        }

        doBfs(n);
    }

}
