import java.util.*;
class Move {
    int x, y;
    Move(int x, int y) {
        this.x = x; //좌우로 얼마나 이동했는지
        this.y = y; //상하로 얼마나 이동했는지
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
    static boolean[][] visited;
    static int[][] key;
    static int M;
    static boolean answer = false;
    static ArrayList<ArrayList<Point>> lock_peace_list = new ArrayList<>();
    public boolean solution(int[][] Key, int[][] lock) {
        M = Key.length;
        key = Key;
        ArrayList<Point> lock_peace = find_peace(lock, 0);
        if(lock_peace.size() == 0) return true;
        for(int i=0; i<4; i++) {
            lock_peace_list.add(standardization_peace(lock_peace));
            lock_peace = rotation_peace(lock_peace);
        }
        visited = new boolean[2 * M + 1][2 * M + 1]; //(M, M)이 new Move(0, 0)
        BFS();
        return answer;
    }
    
    static void BFS() {
        Queue<Move> que = new LinkedList<>();
        que.add(new Move(0, 0));
        visited[M][M] = true;
        while(que.size() != 0) {
            Move n = que.poll();
            if(check_open(n)) {
                answer = true;
                return;
            }
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= (nx + M) && (nx + M) <= 2*M) && (0 <= (ny + M) && (ny + M) <= 2*M)) {
                    if(!visited[nx + M][ny + M]) {
                        visited[nx + M][ny + M] = true;
                        que.add(new Move(nx, ny));
                    }
                }
            }
        }
    } 
    
    static boolean check_open(Move m) {
        int[][] m_key = move_key(m);
        ArrayList<Point> key_peace = standardization_peace(find_peace(m_key, 1));
        if(key_peace.size() == lock_peace_list.get(0).size()) {
            for(int i=0; i<lock_peace_list.size(); i++) {
                if(same_peace(key_peace, lock_peace_list.get(i))) return true;
            }
        }
        return false;
    }
    
    static int[][] move_key(Move m) {
        int[][] m_key = new int[M][M];
        for(int i=0; i<key.length; i++) {
            int ny = i + m.y;
            for(int j=0; j<key[i].length; j++) {
                int nx = j + m.x;
                if(key[i][j] == 1 && ((0 <= nx && nx <= M-1) && (0 <= ny && ny <= M-1))) m_key[ny][nx] = 1;
            }
        }
        return m_key;
    }
    
    static boolean same_peace(ArrayList<Point> key_peace, ArrayList<Point> lock_peace) {
        for(int i=0; i<lock_peace.size(); i++) {
            if((key_peace.get(i).x != lock_peace.get(i).x) || (key_peace.get(i).y != lock_peace.get(i).y)) return false;
        }
        return true;
    }
    
    static ArrayList<Point> find_peace(int[][] arr, int v) {
        ArrayList<Point> peace = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                if(arr[i][j] == v) peace.add(new Point(j, i));
            }
        }
        return peace;
    }
    
    static ArrayList<Point> standardization_peace(ArrayList<Point> peace) {
        sort_peace(peace);
        ArrayList<Point> s_peace = new ArrayList<>();
        for(int i=0; i<peace.size(); i++) s_peace.add(new Point(peace.get(i).x - peace.get(0).x, peace.get(i).y - peace.get(0).y));
        return s_peace;
    }
    
    static void sort_peace(ArrayList<Point> peace) {
        Collections.sort(peace, (a, b) -> {
            if(a.y - b.y < 0) return -1;
            else if(a.y - b.y > 0) return 1;
            else {
                if(a.x - b.x < 0) return -1;
                else if(a.x - b.x > 0) return 1;
            }
            return 0;
        });
    }
    
    static ArrayList<Point> rotation_peace(ArrayList<Point> peace) {
        //90도 시계 방향 회전
        ArrayList<Point> r_peace = new ArrayList<>();
        for(int i=0; i<peace.size(); i++) r_peace.add(new Point((M-1) - peace.get(i).y, peace.get(i).x));
        return r_peace;
    }
}