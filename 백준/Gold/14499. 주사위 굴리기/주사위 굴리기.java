import java.util.*;
import java.io.*;
class Dice {
    int x, y;
    int[] state;
    Dice(int x, int y, int[] state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
//   2
// 4 1 3
//   5
//   6
}

public class Main {
    static final int[] dx = {0, 1, -1, 0, 0}; // 동 -> 1, 서 -> 2, 북 -> 3, 남 -> 4
    static final int[] dy = {0, 0, 0, -1, 1};
    static int H, W, N;
    static int[][] map;
    static Dice dice;
    static StringBuilder answer = new StringBuilder();
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        int sy = sc.nextInt();
        int sx = sc.nextInt();
        dice = new Dice(sx, sy, new int[7]);
        N = sc.nextInt();
        map = new int[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        for(int i=0; i<N; i++) {
            int dir = sc.nextInt();
            int nx = dice.x + dx[dir];
            int ny = dice.y + dy[dir];
            if(check_range(nx, ny)) {
                roll_dice(nx, ny, dir);
                if(map[ny][nx] == 0) {
                    map[ny][nx] = dice.state[1];
                } else {
                    dice.state[1] = map[ny][nx];
                    map[ny][nx] = 0;
                }
                answer.append(dice.state[6] + "\n");
            }
        }
        System.out.println(answer.toString().trim());
    }
    
    static void roll_dice(int x, int y, int dir) {
        int[] rool_state = new int[7];
        int[] development = make_development(dir);
        for(int i=1; i<=6; i++) rool_state[i] = dice.state[development[i]];
        dice = new Dice(x, y, rool_state);
    }
    
    static int[] make_development(int dir) {
        if(dir == 1) {
            int[] development = {0, 3, 2, 6, 1, 5, 4};
            return development;
        } else if(dir == 2) {
            int[] development = {0, 4, 2, 1, 6, 5, 3};
            return development;
        } else if(dir == 3) {
            int[] development = {0, 2, 6, 3, 4, 1, 5};
            return development;
        }
        int[] development = {0, 5, 1, 3, 4, 6, 2};
        return development;
        
    }
    
    static boolean check_range(int x, int y) {
        if((0 <= x && x <= W-1) && (0 <= y && y <= H-1)) return true;
        return false;
    }
}