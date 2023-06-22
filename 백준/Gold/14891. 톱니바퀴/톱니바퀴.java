import java.util.*;
import java.io.*;

class Gear {
    int[] state;
    Gear() {
        this.state = new int[8]; //0 -> 12시, 1 -> 1 30, 2 -> 3
    }
    Gear(int[] state) {
        this.state = state;
    }
}
public class Main {
    static int N;
    static Gear[] gear = new Gear[4];
    static boolean[] visited;
    static int answer = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<4; i++) {
            gear[i] = new Gear();
            String str_input = sc.nextLine();
            for(int j=0; j<str_input.length(); j++) {
                gear[i].state[j] = str_input.charAt(j) - '0';
            }
        }
        N = sc.nextInt();
        for(int i=0; i<N; i++) {
            visited = new boolean[4];
            rotation_gear(sc.nextInt() - 1, sc.nextInt());
        }
        for(int i=0; i<4; i++) {
            if(gear[i].state[0] == 1) answer += Math.pow(2, i);
        }
        System.out.println(answer);
    }
    
    static void rotation_gear(int gn, int dir) {
        visited[gn] = true;
        int next_dir = dir == 1 ? -1 : 1;
        if(gn == 0) {
            if(!visited[gn + 1] && gear[gn].state[2] != gear[gn + 1].state[6]) {
                rotation_gear(gn + 1, next_dir);
            }
        } else if(gn == 1 || gn == 2) {
            if(!visited[gn - 1] && gear[gn].state[6] != gear[gn - 1].state[2]) {
                rotation_gear(gn - 1, next_dir);
            }
            if(!visited[gn + 1] && gear[gn].state[2] != gear[gn + 1].state[6]) {
                rotation_gear(gn + 1, next_dir);
            }
        } else if(gn == 3) {
            if(!visited[gn - 1] && gear[gn].state[6] != gear[gn - 1].state[2]) {
                rotation_gear(gn - 1, next_dir);
            }
        }
        update_gear(gn, dir);
    }
    
    static void update_gear(int gn, int dir) {
        int[] new_state = new int[8];
        if(dir == 1) {
            //시계
            for(int i=0; i<8; i++) {
                if(i == 0) new_state[i] = gear[gn].state[7];
                else new_state[i] = gear[gn].state[i-1];
            }
        } else {
            //반시계
            for(int i=0; i<8; i++) new_state[i] = gear[gn].state[(i + 1)%8];
        }
        gear[gn] = new Gear(new_state);
    }
}