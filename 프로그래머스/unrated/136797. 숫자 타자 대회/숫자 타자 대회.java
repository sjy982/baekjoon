import java.util.*;
class Node {
    char l, r;
    int c;
    Node(char l, char r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }
}

class Point {
    int x,y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] weight = {2, 3, 2, 3, 2, 3, 2, 3};
    static final int[][] pad_point = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}, {3, 0}, {}, {3, 2}};
    static int[][][] visited;
    public int solution(String numbers) {
        int answer = Integer.MAX_VALUE;
        visited = new int[10][10][numbers.length() + 1];
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[i].length; j++) Arrays.fill(visited[i][j], Integer.MAX_VALUE);
        }
        BFS(new Node('4', '6', 0), numbers);
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[i].length; j++) {
                answer = Math.min(visited[i][j][numbers.length()], answer);
            }
        }
        return answer;
    }
    static void BFS(Node start, String numbers) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.l - '0'][start.r - '0'][start.c] = 0;
        while(que.size() != 0) {
            Node n = que.poll();
            if(n.c == numbers.length()) continue;
            Point target = conversion(numbers.charAt(n.c));
            Point s = new Point(-1, -1);
            for(int i=0; i<2; i++) {
                char left = n.l;
                char right = n.r;
                if(i==0) {
                    //왼쪽 손가락으로 누르는 경우
                    s = conversion(n.l);
                    left = numbers.charAt(n.c);
                } else if(i==1) {
                    //오른쪽 손가락으로 누르는 경우
                    s = conversion(n.r);
                    right = numbers.charAt(n.c);
                }
                if((left != right)){
                    int next_w = visited[n.l - '0'][n.r - '0'][n.c] + pad_BFS(s, target);
                    if(visited[left - '0'][right - '0'][n.c + 1] > next_w) {
                        que.add(new Node(left, right, n.c + 1));
                        visited[left - '0'][right - '0'][n.c + 1] = next_w;
                    }
                }
            }
        }
    }
    
    static int pad_BFS(Point start, Point target) {
        //pad 최소 가중치
        if((start.x == target.x) && (start.y == target.y)) return 1;
        Queue<Point> que = new LinkedList<>();
        int[][] visited = new int[4][3];
        for(int i=0; i<visited.length; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        que.add(start);
        visited[start.y][start.x] = 0;
        while(que.size() != 0) {
            Point n = que.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= 2) && (0 <= ny && ny <= 3)) {
                    int next_weight = visited[n.y][n.x] + weight[i];
                    if(visited[ny][nx] > next_weight) {
                        que.add(new Point(nx, ny));
                        visited[ny][nx] = next_weight;
                    }
                }
            }
        }
        return visited[target.y][target.x];
    }
    
    static Point conversion(char c) {
        int index = -1;
        if(c == '*') index = 10;
        else if(c == '#') index = 12;
        index = c - '0';
        return new Point(pad_point[index][1], pad_point[index][0]);
    }
}