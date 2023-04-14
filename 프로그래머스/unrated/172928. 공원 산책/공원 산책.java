import java.util.*;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static HashMap<Character, Integer> dir_map = new HashMap<>();
    static Point location;
    static int H, W;
    public int[] solution(String[] park, String[] routes) {
        dir_map.put('W', 0);
        dir_map.put('N', 1);
        dir_map.put('E', 2);
        dir_map.put('S', 3);
        H = park.length;
        W = park[0].length();
        int[] answer = new int[2];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(park[i].charAt(j) == 'S') location = new Point(j, i);
            }
        }
        for(int i=0; i<routes.length; i++) {
            char dir = routes[i].charAt(0);
            int distance = routes[i].charAt(2) - '0';
            int nx = location.x;
            int ny = location.y;
            boolean isPosi = true;
            for(int j=0; j<distance; j++) {
                nx += dx[dir_map.get(dir)];
                ny += dy[dir_map.get(dir)];
                if((nx>=0 && nx<=W-1) && (ny>=0 && ny<=H-1)) {
                    if(park[ny].charAt(nx) == 'X'){
                        isPosi = false;
                        break;
                    } 
                } else {
                    isPosi = false;
                    break;
                };
            }
            if(isPosi) location = new Point(nx, ny);
        }
        answer[0] = location.y;
        answer[1] = location.x;
        return answer;
    }
}