import java.util.*;
class Node {
    Point w1, w2;
    int t;
    Node(Point wing1, Point wing2, int t) {
        this.w1 = wing1;
        this.w2 = wing2;
        this.t = t;
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
    static boolean[][][][] visited; //[w1.y][w1.x][w2.y][w2.x]
    static int[][] board;
    static int N;
    static int answer;
    public int solution(int[][] Board) {
        N = Board.length;
        board = Board;
        visited = new boolean[N][N][N][N];
        BFS(new Node(new Point(0,0), new Point(1, 0), 0));
        return answer;
    }
    
    static void BFS(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.w1.y][start.w1.x][start.w2.y][start.w2.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if((n.w1.y == N-1 && n.w1.x == N-1) || (n.w2.y == N-1 && n.w2.x == N-1)) {
                answer = n.t;
                return;
            }
            //이동
            for(int i=0; i<4; i++) {
                int w1_nx = n.w1.x + dx[i];
                int w1_ny = n.w1.y + dy[i];
                int w2_nx = n.w2.x + dx[i];
                int w2_ny = n.w2.y + dy[i];
                if(range_check(new Point(w1_nx, w1_ny)) && range_check(new Point(w2_nx, w2_ny))) {
                    if(!visited[w1_ny][w1_nx][w2_ny][w2_nx]) {
                        visited[w1_ny][w1_nx][w2_ny][w2_nx] = true;
                        que.add(new Node(new Point(w1_nx, w1_ny), new Point(w2_nx, w2_ny), n.t + 1));
                    }
                }
            }
            //회전
            for(int i=0; i<2; i++) {
                //i == 0 w1, i == 1 w2
                for(int j=0; j<2; j++) {
                    // j == 0 시계, j == 1반시계
                    Node next_n = rotation(n, i, j);
                    if(!visited[next_n.w1.y][next_n.w1.x][next_n.w2.y][next_n.w2.x]) {
                        visited[next_n.w1.y][next_n.w1.x][next_n.w2.y][next_n.w2.x] = true;
                        que.add(next_n);
                    }
                }
            }
        }
    }
    
    static Node rotation(Node n, int wing, int dir) {
        if(wing == 0) {
            //w1 회전
            if(n.w1.y == n.w2.y) {
                //가로
                if(dir == 0) {
                    //시계 방향
                    if(range_check(new Point(n.w1.x, n.w1.y - 1))) {
                        //대각선 확인 돌아갈 수 있다면
                        if(range_check(new Point(n.w1.x + 1, n.w1.y - 1))) {
                            //돌아갔을 때 w1 위치가 가능한 위치라면
                            return new Node(new Point(n.w1.x + 1, n.w1.y - 1), new Point(n.w2.x, n.w2.y), n.t +1);
                        }
                    }
                } else if(dir == 1) {
                    //반시계 방향
                    if(range_check(new Point(n.w1.x, n.w1.y + 1))) {
                        if(range_check(new Point(n.w1.x + 1, n.w1.y + 1))) {
                            return new Node(new Point(n.w2.x, n.w2.y), new Point(n.w1.x + 1, n.w1.y + 1), n.t + 1);
                        }
                    }
                }
            } else {
                //세로
                if(dir == 0) {
                    //시계 방향
                    if(range_check(new Point(n.w1.x + 1, n.w1.y))) {
                        if(range_check(new Point(n.w1.x + 1, n.w1.y + 1))) {
                            return new Node(new Point(n.w2.x, n.w2.y), new Point(n.w1.x + 1, n.w1.y + 1), n.t + 1);
                        }
                    }
                } else if(dir == 1) {
                    //반시계 방향
                    if(range_check(new Point(n.w1.x - 1, n.w1.y))) {
                        if(range_check(new Point(n.w1.x - 1, n.w1.y + 1))) {
                            return new Node(new Point(n.w1.x - 1, n.w1.y + 1), new Point(n.w2.x, n.w2.y), n.t + 1);
                        }
                    }
                }
            }
        } else if(wing == 1){
            //w2 회전
            if(n.w1.y == n.w2.y) {
                //가로
                if(dir == 0) {
                    //시계 방향
                    if(range_check(new Point(n.w2.x, n.w2.y + 1))) {
                        if(range_check(new Point(n.w2.x - 1, n.w2.y + 1))) {
                            return new Node(new Point(n.w1.x, n.w1.y), new Point(n.w2.x - 1, n.w2.y + 1), n.t + 1);
                        }
                    }
                } else if(dir == 1) {
                    //반시계 방향
                    if(range_check(new Point(n.w2.x, n.w2.y - 1))) {
                        if(range_check(new Point(n.w2.x - 1, n.w2.y - 1))) {
                            return new Node(new Point(n.w2.x - 1, n.w2.y - 1), new Point(n.w1.x, n.w1.y), n.t + 1);
                        }
                    }
                }
            } else {
                //세로
                if(dir == 0) {
                    //시계 방향
                    if(range_check(new Point(n.w2.x - 1, n.w2.y))) {
                        if(range_check(new Point(n.w2.x - 1, n.w2.y - 1))) {
                           return new Node(new Point(n.w2.x - 1, n.w2.y - 1), new Point(n.w1.x, n.w1.y), n.t + 1); 
                        }
                    }
                }else if(dir == 1) {
                    //반시계 방향
                    if(range_check(new Point(n.w2.x + 1, n.w2.y))) {
                        if(range_check(new Point(n.w2.x + 1, n.w2.y - 1))) {
                            return new Node(new Point(n.w1.x, n.w1.y), new Point(n.w2.x + 1, n.w2.y - 1), n.t + 1);
                        }
                    }
                }
            }
        }
        return new Node(new Point(n.w1.x, n.w1.y), new Point(n.w2.x, n.w2.y), n.t);
    }
    
    static boolean range_check(Point p) {
        if((0<=p.x && p.x<=N-1) && (0<=p.y && p.y<=N-1)) {
            if(board[p.y][p.x] == 0) return true;
        }
        return false;
    }
}