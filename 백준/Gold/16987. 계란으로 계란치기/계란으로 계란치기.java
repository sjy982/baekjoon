import java.io.*;
import java.util.*;

class EggInfo {
    int s,w;
    EggInfo(int s, int w) {
        this.s = s;
        this.w = w;
    }
}

public class Main {
    static int N;
    static EggInfo egg[];
    static int ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        egg = new EggInfo[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            egg[i] = new EggInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        DFS(0);
        System.out.println(ans);
    }
    
    static void DFS(int d) {
        if(d==N) {
            ans = Math.max(ans, bk_egg_cout());
            return;
        }
        boolean hit = false;
        for(int i=0; i<N; i++) {
            if(d != i) {
                if(egg[d].s > 0) {
                    if(egg[i].s > 0) {
                        egg[d].s -= egg[i].w;
                        egg[i].s -= egg[d].w;
                        DFS(d+1);
                        egg[d].s += egg[i].w;
                        egg[i].s += egg[d].w;
                        hit = true;
                    }
                } 
            }
        }
        if(!hit) DFS(d+1);
    }
    
    static int bk_egg_cout() {
        int cout = 0;
        for(int i=0; i<N; i++) {
            if(egg[i].s<=0) cout += 1;
        }
        return cout;
    }
}