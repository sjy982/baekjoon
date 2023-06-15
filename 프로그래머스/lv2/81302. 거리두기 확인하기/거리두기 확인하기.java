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
            if((start.y != n.y || start.x != n.x) && room[n.y].charAt(n.x) == 'P' && find_MDistance(start, n) <= 2) return false;
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= 4) && (0 <= ny && ny <= 4)) {
                    if(room[ny].charAt(nx) != 'X' && !visited[ny][nx]) {
                        que.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return true;
    }
    
    static void setting_room(Point p, boolean[][] visited, String[] room) {
        for(int i=0; i<4; i++) {
            Point cp = new Point(p.x + dx[i], p.y + dy[i]);
            boolean behind_wall = false;
            while((0 <= cp.x && cp.x <= 4) && (0 <= cp.y && cp.y <= 4)) {
                if(room[cp.y].charAt(cp.x) == 'X') behind_wall = true;
                if(behind_wall) visited[cp.y][cp.x] = true;
                cp = new Point(cp.x + dx[i],  cp.y + dy[i]);
            }
        }
    }
    
    static int find_MDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}