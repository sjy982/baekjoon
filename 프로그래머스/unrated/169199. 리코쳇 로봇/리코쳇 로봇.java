import java.util.*;
class Point {
    int x,y, c;
    Point(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
class Solution {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static Point start, end;
    static boolean[][] visited;
    static int W,H;
    public int solution(String[] board) {
        H = board.length;
        W = board[0].length();
        visited = new boolean[H][W];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                if(board[i].charAt(j) == 'R') start = new Point(j, i, 0);
                else if(board[i].charAt(j) == 'G') end = new Point(j, i, 0);
            }
        }
        int answer = BFS(board);
        return answer;
    }
    static int BFS(String[] board) {
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size()!=0) {
            Point n = que.poll();
            if((n.y == end.y) && (n.x == end.x)) return n.c;
            for(int i=0; i<4; i++) {
                Point np = move(i, new Point(n.x, n.y, n.c + 1), board);
                if(!visited[np.y][np.x]) {
                    visited[np.y][np.x] = true;
                    que.add(np);
                }
            }
        }
        return -1;
    }
    static Point move(int dir, Point p, String[] board) {
        while(true) {
            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];
            if(((0<=nx && nx<=W-1) && (0<=ny && ny<=H-1)) && board[ny].charAt(nx) != 'D') {
                p.x = nx;
                p.y = ny;
            } else break;
        }
        return p;
    }
}