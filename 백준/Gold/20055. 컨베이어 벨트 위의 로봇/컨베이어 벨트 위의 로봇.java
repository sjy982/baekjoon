import java.util.*;
import java.io.*;
class Conveyor {
    int v;
    boolean robot;
    Conveyor(int v, boolean robot) {
        this.v = v;
        this.robot = robot;
    }
}
public class Main {
    static int N, K;
    static Conveyor[] conveyor;
    static int answer = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        conveyor = new Conveyor[2 * N + 1];
        for(int i=1; i<conveyor.length; i++) conveyor[i] = new Conveyor(sc.nextInt(), false);
        while(true) {
            if(check_end()) break;
            rotation();
            move_robot();
            lay_robot();
            answer += 1;
        }
        System.out.println(answer);
    }
    
    static void lay_robot() {
        if(conveyor[1].v >= 1) {
            conveyor[1].robot = true;
            conveyor[1].v -= 1;
        }
    }
    
    static void move_robot() {
        for(int i=N; i>1; i--) {
            if(!conveyor[i].robot && conveyor[i-1].robot && (conveyor[i].v >= 1)) {
                conveyor[i].robot = true;
                conveyor[i - 1].robot = false;
                conveyor[i].v -= 1;
            }
        }
        if(conveyor[N].robot) conveyor[N].robot = false;
    }
    
    static void rotation() {
        conveyor[N].robot = false;
        conveyor[0] = new Conveyor(conveyor[2 * N].v, conveyor[2 * N].robot);
        for(int i=2*N; i>=1; i--) {
            conveyor[i] = new Conveyor(conveyor[i - 1].v, conveyor[i - 1].robot);
        }
        if(conveyor[N].robot) conveyor[N].robot = false;
    }
    
    static boolean check_end() {
        int cnt = 0;
        for(int i=1; i<conveyor.length; i++) if(conveyor[i].v == 0) cnt += 1;
        if(cnt >= K) return true;
        return false;
    }
}