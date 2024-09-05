package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class treecolor {
    static final int MAX_ID = 100005;
    static final int MAX_DEPTH = 105;
    static final int COLOR_MAX = 5;

    public static class Node {
        int id = 0;
        int color = 0;
        int lastUpdate = 0;
        int maxDepth = 0;
        int parentId = 0;
        List<Integer> childIds = new ArrayList<>();
    }
    static class ColorCount {
        int[] cnt = new int[COLOR_MAX + 1];
        ColorCount add(ColorCount obj) {
            ColorCount res = new ColorCount();
            for (int i = 1; i <= COLOR_MAX; i++) {
                res.cnt[i] = this.cnt[i] + obj.cnt[i];
            }
            return res;
        }

        int score() {
            int result = 0;
            for (int i = 1; i <= COLOR_MAX; i++) {
                if (this.cnt[i] > 0) result++;
            }
            return result * result;
        }
    }

    static Node[] nodes = new Node[MAX_ID];
    static boolean[] isRoot = new boolean[MAX_ID];

    static {
        for (int i = 0; i < MAX_ID; i++) {
            nodes[i] = new Node();
        }
    }

    static boolean canMakeChild(Node cur, int needDepth) {
        if (cur.id == 0)
            return true;
        if (cur.maxDepth <= needDepth)
            return false;
        return canMakeChild(nodes[cur.parentId], needDepth + 1);
    }

    static int[] getColor(Node cur) {
        if (cur.id == 0) {
            return new int[] {0 , 0};
        }
        int[] info = getColor(nodes[cur.parentId]);
        if (info[1] > cur.lastUpdate) {
            return info;
        } else {
            return new int[] {cur.color, cur.lastUpdate};
        }
    }
    static Object[] getBeauty(Node curr, int color, int lastUpdate) {
        if (lastUpdate < curr.lastUpdate) {
            lastUpdate = curr.lastUpdate;
            color = curr.color;
        }
        int result = 0;
        ColorCount colorCount = new ColorCount();
        colorCount.cnt[color] = 1;
        for (int childId : curr.childIds) {
            Node child = nodes[childId];
            Object[] subResult = getBeauty(child, color, lastUpdate);
            colorCount = colorCount.add((ColorCount) subResult[1]);
            result += (Integer) subResult[0];
        }
        result += colorCount.score();
        return new Object[] { result, colorCount };
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            if (order == 100) {
                int mId = Integer.parseInt(st.nextToken());
                int pId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                int maxDepth = Integer.parseInt(st.nextToken());
                if (pId == -1) {
                    isRoot[pId] = true;
                }
                if (isRoot[mId] || canMakeChild(nodes[pId], 1)) {
                    nodes[mId].id = mId;
                    nodes[mId].color = color;
                    nodes[mId].maxDepth = maxDepth;
                    nodes[mId].parentId = isRoot[mId] ? 0 : pId;
                    nodes[mId].lastUpdate = i;
                    if (!isRoot[mId]) {
                        nodes[pId].childIds.add(mId);
                    }
                }


            } else if (order == 200) {
                int mId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                nodes[mId].color = color;
                nodes[mId].lastUpdate = i;
            } else if (order == 300) {
                int mId = Integer.parseInt(st.nextToken());
                System.out.println(getColor(nodes[mId])[0]);
            } else if (order == 400) {
                int beauty = 0;
                for (int idx = 1; idx < MAX_ID; idx++) {
                    // root 노드들에 대해 점수를 계산합니다
                    if (isRoot[idx]) {
                        beauty += (Integer) getBeauty(nodes[idx], nodes[idx].color, nodes[idx].lastUpdate)[0];
                    }
                }
                System.out.println(beauty);
            }
        }
    }
}
