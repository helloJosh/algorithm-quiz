package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class treecolor {
    public static class Node {
        int id;
        int color;
        int maxDepth;
        Node parent;
        List<Node> children;

        public Node(int id, int color, int maxDepth) {
            this.id = id;
            this.color = color;
            this.maxDepth = maxDepth;
            this.children = new ArrayList<>();
        }
    }
    public static class Tree {
        private Map<Integer, Node> nodes = new HashMap<>();

        public void addNode(int mId, int pId, int color, int maxDepth) {
            if (pId == -1) {
                Node node = new Node(mId, color, maxDepth);
                nodes.put(mId, node);
            } else {
                Node parent = nodes.get(pId);
                if (Objects.isNull(parent)) {
                    return;
                }
                if (getDepth(parent) < parent.maxDepth) {
                    Node node = new Node(mId, color, maxDepth);

                    node.parent = parent;
                    node.children.add(node);
                    nodes.put(mId, node);
                }
            }
        }

        public int getDepth(Node node) {
            if (node == null) return 0;
            int depth = 1;
            for (Node child : node.children) {
                depth = Math.max(depth, 1 + getDepth(child));
            }
            return depth;
        }

        public void changeColor(int mId, int color) {
            Node node = nodes.get(mId);
            if (node != null) {
                recursiveColor(node, color);
            }
        }
        public void recursiveColor(Node node, int color){
            node.color = color;
            for (Node temp : node.children) {
                recursiveColor(temp, color);
            }
        }
        public int getColor(int mId) {
            Node node = nodes.get(mId);
            return node.color;
        }
        public int calculateScore() {
            int score = 0;
            for (Node node : nodes.values()) {
                Set<Integer> colors = new HashSet<>();
                recursiveScore(node, colors);
                score += colors.size() * colors.size();
            }
            return  score;
        }
        public void recursiveScore(Node node, Set<Integer> colors) {
            colors.add(node.color);
            for (Node child : node.children) {
                recursiveScore(child, colors);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            if (order == 100) {
                int mId = Integer.parseInt(st.nextToken());
                int pId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                int maxDepth = Integer.parseInt(st.nextToken());

                tree.addNode(mId, pId, color, maxDepth);

            } else if (order == 200) {
                int mId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                tree.changeColor(mId, color);
            } else if (order == 300) {
                int mId = Integer.parseInt(st.nextToken());
                System.out.println(tree.getColor(mId));
            } else if (order == 400) {
                System.out.println(tree.calculateScore());
            }
        }

    }
}
