import java.util.*;
class Point {
    int x, y, c;
    Point(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c; //턴 횟수
    }
}
class Solution {
    static final int[] dx = {0, -1, 0, 1, 0};
    static final int[] dy = {0, 0, -1, 0, 1};
    static int W,H;
    static ArrayList<Integer> result = new ArrayList<>();
    static final int[] need_turn = {0, 3, 2, 1};
    static int answer = Integer.MAX_VALUE;
    public int solution(int[][] clockHands) {
        H = clockHands.length;
        W = clockHands.length;
        DFS(clockHands);
        return answer;
    }
    
    static void DFS(int[][] clockHands) {
        if(result.size() == W) {
            //맨 위 1줄 상태가 결정되면 나머지 줄도 결정됨.
            int total_turn_cut = 0;
            int[][] copy_clockHands = new int[H][W];
            for(int i=0; i<H; i++) copy_clockHands[i] = clockHands[i].clone();
            for(int i=0; i<H; i++) {
                int turn_cut = 0;
                for(int j=0; j<W; j++) {
                    if(i==0) turn_cut = result.get(j);
                    else turn_cut = need_turn[copy_clockHands[i-1][j]];
                    
                    if(turn_cut != 0) {
                        Point p = new Point(j, i, turn_cut);
                        turn(p, copy_clockHands);
                        total_turn_cut += turn_cut;
                    }
                }
            }
            if(answer_check(copy_clockHands)) answer = Math.min(answer, total_turn_cut);
            return;
        }
        for(int i=0; i<4; i++) {
            //0~3번 돌릴 횟수 결정
            result.add(i);
            DFS(clockHands);
            result.remove(result.size() - 1);
        }
    }
    
    static void turn(Point point ,int[][] clockHands) {
        for(int i=0; i<5; i++) {
            int nx = point.x + dx[i];
            int ny = point.y + dy[i];
            if((0<= nx && nx<= W-1) && (0<= ny && ny<=H-1)) {
                int n_dir = clockHands[ny][nx] + point.c;
                if(n_dir >=4) n_dir -= 4;
                clockHands[ny][nx] = n_dir;
            }
        }
    }
    
    static boolean answer_check(int[][] clockHands) {
        for(int i=0; i<clockHands.length; i++) {
            for(int j=0; j<clockHands[i].length; j++) {
                if(clockHands[i][j] != 0) return false;
            }
        }
        return true;
    }
}