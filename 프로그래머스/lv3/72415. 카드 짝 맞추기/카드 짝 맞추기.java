import java.util.*;
class Node {
    int x, y, c, set, select;
    Node(int x, int y, int c, int set, int select) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.set = set;
        this.select = select;
    }
}
class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static boolean[][][] visited;
    static int max_set = 0;
    static int answer = 0;
    public int solution(int[][] board, int r, int c) {
        for(int i=0; i<=15; i++) max_set = max_set | (1 << i);
        visited = new boolean[4][4][max_set + 1];
        Node start = new Node(c, r, 0, 0, -1); //-1 -> 선택하지 않음.
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == 0) start.set = start.set | (1 << conversion_point(j, i));
            }
        }
        BFS(start, board);
        return answer;
    }
    
    static void BFS(Node start, int[][] board) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x][start.set] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if(n.set == max_set) {
                answer = n.c;
                break;
            }
            //enter 연산
            int next_set = n.set | (1 << conversion_point(n.x, n.y));
            if(board[n.y][n.x] != 0 && n.set != next_set) {
                if(n.select == -1) {
                    //아직 선택한 카드가 없을 때.
                    if(!visited[n.y][n.x][next_set]) {
                        que.add(new Node(n.x, n.y, n.c + 1, next_set, board[n.y][n.x]));
                        visited[n.y][n.x][next_set] = true;
                    }
                } else {
                    //선택한 카드가 있을 때
                    if(n.select == board[n.y][n.x]) {
                        //카드의 값이 같을 때
                        que.add(new Node(n.x, n.y, n.c + 1, next_set, -1));
                        visited[n.y][n.x][next_set] = true;
                    }
                }
            }
            //이동 연산
            //기본 이동 연산
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= 3) && (0 <= ny && ny <= 3)) {
                    if(!visited[ny][nx][n.set]) {
                        que.add(new Node(nx, ny, n.c + 1, n.set, n.select));
                        visited[ny][nx][n.set] = true;
                    }
                }
            }
            //ctrl 이동 연산
            for(int i=0; i<4; i++) {
                Point next_p = ctrl_move(new Point(n.x, n.y), i, n.set, board);
                if(!visited[next_p.y][next_p.x][n.set]) {
                    que.add(new Node(next_p.x, next_p.y, n.c + 1, n.set, n.select));
                    visited[next_p.y][next_p.x][n.set] = true;
                }
            }
        }
    }
    
    static Point ctrl_move(Point p, int dir, int set,int[][] board) {
        while(true) {
            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];
            if((0<= nx && nx <= 3) && (0 <= ny && ny <= 3)) {
                if(board[ny][nx] != 0) {
                    int next_set = set | (1 << conversion_point(nx, ny));
                    if(set != next_set) {
                        //카드가 존재할 때
                        return new Point(nx, ny);
                    }    
                }
            } else return new Point(p.x, p.y);
            p = new Point(nx, ny);
        }
    }
    
    static int conversion_point(int x, int y) {
        return y * 4 + x;
    }
}