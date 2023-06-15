import java.util.*;
class Point {
    int x,y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<places.length; i++) {
            boolean isPosi = true;
            for(int j=0; j<places[i].length; j++) {
                for(int k=0; k<places[i][j].length(); k++) {
                    if(places[i][j].charAt(k) == 'P') {
                        if(!BFS(new Point(k, j), places[i])) {
                            isPosi = false;
                            break;
                        }
                    }
                }
                if(!isPosi) break;
            }
            if(isPosi) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    
    static boolean BFS(Point start, String[] room) {
        boolean[][] visited = new boolean[5][5];
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        setting_room(start, visited, room); //상,하,좌,우로 벽이 있다면 그 벽뒤는 검사하지 않는다.
        while(que.size() != 0) {
            Point n = que.poll();
            if((start.y != n.y || start.x != n.x) && room[n.y].charAt(n.x) == 'P') return false;
            for(int i=0; i<4; i++) {
                Point np = new Point(n.x + dx[i], n.y + dy[i]);
                if(check_range(np)) {
                    if(room[np.y].charAt(np.x) != 'X' && !visited[np.y][np.x] && find_MDistance(start, np) <= 2) {
                        que.add(np);
                        visited[np.y][np.x] = true;
                    }
                }
            }
        }
        return true;
    }
    
    static void setting_room(Point p, boolean[][] visited, String[] room) {
        for(int i=0; i<4; i++) {
            Point np = new Point(p.x + dx[i], p.y + dy[i]);
            if(check_range(np) && room[np.y].charAt(np.x) == 'X') {
                if(check_range(new Point(np.x + dx[i], np.y + dy[i]))) visited[np.y + dy[i]][np.x + dx[i]] = true;
            }
        }
    }
    
    static boolean check_range(Point p) {
        if((0 <= p.x && p.x <= 4) && (0 <= p.y && p.y <= 4)) return true;
        return false;
    }
    
    static int find_MDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}