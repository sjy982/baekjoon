import java.util.*;
import java.io.*;
class Point {
    int x,y;
    Point(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
class Node {
    int x, y, c;
    StringBuilder sb;
    Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
        sb = new StringBuilder();
    }
}
class Solution {
    static final int[] dx = {0, -1, 1, 0};
    static final int[] dy = {1, 0, 0, -1};
    static final char[] dir = {'d', 'l', 'r', 'u'};
    static boolean[][][] visited;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        visited = new boolean[n+1][m+1][k+1];
        answer = BFS(n, m, new Point(y, x), new Point(c, r), k);
        return answer;
    }
    static String BFS(int H, int W, Point start, Point end, int k) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start.x, start.y, 0));
        visited[start.y][start.x][0] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if((n.c == k)) {
                if((n.x == end.x) && (n.y == end.y)) return n.sb.toString();
                else continue;
            }
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((1 <= nx && nx <= W) && (1<= ny && ny <= H)) {
                    if(!visited[ny][nx][n.c+1]) {
                        Node new_node = new Node(nx, ny, n.c + 1);
                        new_node.sb.append(n.sb.toString() + dir[i]);
                        que.add(new_node);
                        visited[ny][nx][n.c+1] = true;
                    }
                }
            }
        }
        return "impossible";
    }
}