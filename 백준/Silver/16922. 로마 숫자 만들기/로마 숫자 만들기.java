import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean visited[][];
    static int num[] = {1,5,10,50};
    static int ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1][N*50+1];
        find_num(0, 0);
        System.out.println(ans);
    }
    
    static void find_num(int cur_num, int cout) {
        if(cout == N) {
            ans += 1;
            return;
        }
        for(int i=0; i<4; i++) {
            int next_num = cur_num + num[i];
            if(!visited[cout][next_num]) {
                visited[cout][next_num] = true;
                find_num(next_num, cout + 1);
            }
        }
    }
}