import java.util.*;
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
    static boolean[][] visited;
    static boolean[] fill;
    static int W,H;
    static ArrayList<ArrayList<Point>> blank_list = new ArrayList<>(); 
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        H = game_board.length;
        W = game_board[0].length;
        visited = new boolean[H][W];
        //game_board에서 빈공간 찾기
        for(int i=0; i<game_board.length; i++) {
            for(int j=0; j<game_board[i].length; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    blank_list.add(standardization(BFS(new Point(j, i), game_board, 0)));
                }
            }
        }
        visited = new boolean[H][W]; //초기화
        fill = new boolean[blank_list.size()];
        //테이블에서 퍼즐 조각 찾기
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<table[i].length; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    answer += find_space(BFS(new Point(j, i), table, 1));
                }
            }
        }
        return answer;
    }
    static ArrayList<Point> BFS(Point start, int[][] board, int v) {
        ArrayList<Point> piece = new ArrayList<>();
        Queue<Point> que = new LinkedList<>();
        piece.add(start);
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Point n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= W-1) && (0 <= ny && ny <= H-1)) {
                    if(!visited[ny][nx] && board[ny][nx] == v) {
                        piece.add(new Point(nx, ny));
                        que.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return piece;
    }
    
    static ArrayList<Point> standardization(ArrayList<Point> piece) {
        ArrayList<Point> s_piece = new ArrayList<>();
        ArrayList<Point> copy_piece = deep_copy(piece);
        Collections.sort(copy_piece, (a, b) -> {
            int diff_y = a.y - b.y;
            if(diff_y < 0) return -1;
            else if(diff_y > 0) return 1;
            else if(diff_y == 0) {
                int diff_x = a.x - b.x;
                if(diff_x < 0) return -1;
                else if(diff_x > 0) return 1;
            }
            return 0;
        });
        for(int i=0; i<copy_piece.size(); i++) {
            s_piece.add(new Point(copy_piece.get(i).x - copy_piece.get(0).x, copy_piece.get(i).y - copy_piece.get(0).y));
        }
        return s_piece;
    }
    
    static int find_space(ArrayList<Point> piece) {
        for(int i=0; i<4; i++) {
            //회전하면서 맞는 blank가 있는지 확인
            ArrayList<Point> s_piece = standardization(piece);
            for(int j=0; j<blank_list.size(); j++) {
                if(!fill[j] && blank_list.get(j).size() == s_piece.size()) {
                    boolean possible = true;
                    for(int k=0; k<blank_list.get(j).size(); k++) {
                        Point blank = blank_list.get(j).get(k);
                        if((blank.x != s_piece.get(k).x) || (blank.y != s_piece.get(k).y)) {
                            possible = false;
                            break;
                        }
                    }
                    if(possible) {
                        fill[j] = true;
                        return s_piece.size();
                    } 
                }
            }
            piece = rotation_piece(piece);
        }
        return 0;
    }
    
    static ArrayList<Point> rotation_piece(ArrayList<Point> piece) {
        //90도 시계 방향으로 회전
        //회전하면 -> x값은 H - 회전하기전 y값, y값은 회전하기전 x값
        ArrayList<Point> r90_piece = new ArrayList<>();
        for(int i=0; i<piece.size(); i++) r90_piece.add(new Point((H - 1) - piece.get(i).y, piece.get(i).x));
        return r90_piece;
    }
    
    static ArrayList<Point> deep_copy(ArrayList<Point> list) {
        ArrayList<Point> copy_list = new ArrayList<>();
        for(int i=0; i<list.size(); i++) copy_list.add(new Point(list.get(i).x, list.get(i).y));
        return copy_list;
    }
}