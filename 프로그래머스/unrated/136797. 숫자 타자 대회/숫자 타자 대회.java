import java.util.*;
class Node {
    char l,r;
    int c;
    Node(char l, char r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }
}

class Point {
    int x, y, w;
    Point(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }
}
class Solution {
    static int[][][] visited;
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] weight = {2, 3, 2, 3, 2, 3, 2, 3};
    static HashMap<Character, Point> charToPoint = new HashMap<>();
    static char[][] pad = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'*', '0', '#'}};
    public int solution(String numbers) {
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<pad.length; i++) {
            for(int j=0; j<pad[i].length; j++) charToPoint.put(pad[i][j], new Point(j, i, 0));
        }
        visited = new int[10][10][numbers.length()+1];
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[i].length; j++) Arrays.fill(visited[i][j], Integer.MAX_VALUE);
        }
        BFS(new Node('4', '6', 0), numbers);
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[i].length; j++) {
                answer = Math.min(answer, visited[i][j][numbers.length()]);
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
            Point target = charToPoint.get(numbers.charAt(n.c));
            for(int i=0; i<2; i++) {
                Point n_start = new Point(-1, -1, 0);
                char next_l = n.l;
                char next_r = n.r;
                if(i==0) {
                    n_start = charToPoint.get(n.l);
                    next_l = numbers.charAt(n.c);
                } else {
                    n_start = charToPoint.get(n.r);
                    next_r = numbers.charAt(n.c);
                }
                if(next_l != next_r) {
                    //같은 버튼위에 두 손가락이 올라갈 수 없음
                    int weight = np_BFS(n_start, target) + visited[n.l - '0'][n.r - '0'][n.c];
                    if(visited[next_l - '0'][next_r - '0'][n.c + 1] > weight) {
                        que.add(new Node(next_l, next_r, n.c + 1));
                        visited[next_l - '0'][next_r - '0'][n.c + 1] = weight;
                    }
                }
            }
        }
    }
    
    static int np_BFS(Point start, Point target) {
        Queue<Point> que = new LinkedList<>();
        int[][] np_visited = new int[4][3];
        for(int i=0; i<4; i++) Arrays.fill(np_visited[i], Integer.MAX_VALUE);
        que.add(start);
        if((start.x == target.x) && (start.y == target.y)) return 1;
        np_visited[start.y][start.x] = 1;
        while(que.size() != 0) {
            Point n = que.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= 2) && (0<= ny && ny <= 3)) {
                    if(np_visited[ny][nx] > (n.w + weight[i])) {
                        que.add(new Point(nx, ny, n.w + weight[i]));
                        np_visited[ny][nx] = n.w + weight[i];
                    }
                }
            }
        }
        return np_visited[target.y][target.x];
    }
}