import java.util.*;
import java.io.*;
class Point {
    int x, y, d;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class Main {
    static final int[] dx = {0, 1, 0, -1}; //북, 동, 남, 서
    static final int[] dy = {-1, 0, 1, 0};
    static int H, W;
    static Point robot;
    static int[][] room;
    static int answer = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        int sy = sc.nextInt();
        int sx = sc.nextInt();
        robot = new Point(sx, sy, sc.nextInt());
        room = new int[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                room[i][j] = sc.nextInt();
            }
        }
        while(true) {
            if(room[robot.y][robot.x] == 0) {
                answer += 1;
                room[robot.y][robot.x] = -1; //청소함 표시
            }
            if(check_around()) {
                for(int i=0; i<4; i++) {
                    rotation_robot(); //회전
                    int nx = robot.x + dx[robot.d];
                    int ny = robot.y + dy[robot.d];
                    if(check_range(new Point(nx, ny)) && room[ny][nx] == 0) {
                        robot = new Point(nx, ny, robot.d);
                        break;
                    }
                }
            } else {
                if(check_R()) {
                    robot = new Point(robot.x + dx[(robot.d + 2) % 4], robot.y + dy[(robot.d + 2) % 4], robot.d);
                } else break;
            }
        }
        System.out.println(answer);
    }
    
    static boolean check_around() {
        for(int i=0; i<4; i++) {
            int nx = robot.x + dx[i];
            int ny = robot.y + dy[i];
            if(check_range(new Point(nx, ny)) && room[ny][nx] == 0) return true;
        }
        return false;
    }
    
    static boolean check_range(Point p) {
        if((0 <= p.x && p.x <= W-1) && (0 <= p.y && p.y <= H-1)) return true;
        return false;
    }
    
    static boolean check_R() {
        int nx = robot.x + dx[(robot.d + 2) % 4];
        int ny = robot.y + dy[(robot.d + 2) % 4];
        if(check_range(new Point(nx, ny)) && room[ny][nx] != 1) return true;
        return false;
    }
    
    static void rotation_robot() {
        //반시계 방향으로 회전
        int rd = robot.d - 1;
        if((robot.d - 1) == -1) rd = 3;
        robot = new Point(robot.x, robot.y, rd);
    }
}