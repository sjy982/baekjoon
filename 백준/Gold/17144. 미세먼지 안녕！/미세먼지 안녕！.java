import java.util.*;
import java.io.*;

class Point {
    int x, y;
    Point() {
        this.x = -1;
        this.y = -1;
    }
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Dust {
    Point p;
    int v;
    Dust(Point p, int v) {
        this.p = p;
        this.v = v;
    }
}

class AirCleaner {
    Point f, b;
    AirCleaner(Point f, Point b) {
        this.f = f;
        this.b = b;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0}; //서 북 동 남
    static final int[] dy = {0, -1, 0, 1};
    static final int[] c_cw = {2, 1, 0, 3};
    static final int[] cw = {2, 3, 0, 1};
    static int W, H, T;
    static Integer[][] room;
    static AirCleaner air_cleaner;
    static int answer = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        T = sc.nextInt();
        room = new Integer[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                room[i][j] = sc.nextInt();
                if(air_cleaner == null && room[i][j] == -1) {
                    air_cleaner = new AirCleaner(new Point(j, i), new Point(j, i + 1));
                }
            }
        }
        for(int i=0; i<T; i++) {
            diffusion_dust();
            work_airCleaner();
        }
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(room[i][j] > 0) answer += room[i][j];
            }
        }
        System.out.println(answer);
    }
    
    static void diffusion_dust() {
        ArrayList<Dust> dust_list = new ArrayList<>();
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(room[i][j] > 0) {
                    dust_list.add(new Dust(new Point(j, i), room[i][j]));
                    room[i][j] = 0;
                }
            }
        }
        for(int i=0; i<dust_list.size(); i++) {
            int cnt = 0;
            for(int j=0; j<4; j++) {
                int nx = dust_list.get(i).p.x + dx[j];
                int ny = dust_list.get(i).p.y + dy[j];
                if(check_range(nx, ny) && room[ny][nx] != -1) {
                    room[ny][nx] += dust_list.get(i).v / 5;
                    cnt += 1;
                }
            }
            room[dust_list.get(i).p.y][dust_list.get(i).p.x] +=  (dust_list.get(i).v - (dust_list.get(i).v/5 * cnt));
        }
    }
    
    static void work_airCleaner() {
        work(-1);
        work(1);
    }
    
    static void work(int dir) {
        Point air_p = new Point();
        Point end = new Point();
        int[] ad_ind = new int[4];
        if(dir == -1) {
            air_p = new Point(air_cleaner.f.x + 1, air_cleaner.f.y);
            end = new Point(air_cleaner.f.x, air_cleaner.f.y);
            ad_ind = c_cw;
        } else if(dir == 1) {
            air_p = new Point(air_cleaner.b.x + 1, air_cleaner.b.y);
            end = new Point(air_cleaner.b.x, air_cleaner.b.y);
            ad_ind = cw;
        }
        int before_value = 0;
        int air_dir = 0;
        while(true) {
            if((air_p.x == end.x) && (air_p.y == end.y)) break;
            int tmp = room[air_p.y][air_p.x];
            room[air_p.y][air_p.x] = before_value;
            before_value = tmp;
            if(check_range(air_p.x + dx[ad_ind[air_dir]], air_p.y + dy[ad_ind[air_dir]])) {
                air_p = new Point(air_p.x + dx[ad_ind[air_dir]], air_p.y + dy[ad_ind[air_dir]]);
            } else {
                air_dir += 1;
                air_p = new Point(air_p.x + dx[ad_ind[air_dir]], air_p.y + dy[ad_ind[air_dir]]);
            }
        }
    }
    
    static boolean check_range(int x, int y) {
        if((0 <= x && x <= W-1) && (0 <= y && y <= H-1)) return true;
        return false;
    }
}