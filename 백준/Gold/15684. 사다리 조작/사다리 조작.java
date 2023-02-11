import java.io.*;
import java.util.*;

public class Main {
    static int N,M,H;
    static boolean ladder[][];
    static ArrayList<Integer> hLine_crd = new ArrayList<>(); //가로선 좌표
    static int ans = -1;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      ladder = new boolean[N][H+1];
      for(int i=0; i<M; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(n_st.nextToken());
          int b = Integer.parseInt(n_st.nextToken());
          ladder[b][a] = true;
      }
      for(int i=0; i<=3; i++) {
          if(DFS(i)) {
              ans = i;
              break;
          }
      }
      System.out.println(ans);
    }
    
    static boolean DFS(int nh) {
        if(hLine_crd.size()==nh) {
            if(check()) return true;
            return false;
        }
        for(int i=1; i<N; i++) {
            for(int j=1; j<=H; j++) {
                int crd = i*100 + j;
                if(hLine_crd.size()==0) {
                    if(!ladder[i][j]) {
                        hLine_crd.add(crd);
                        ladder[i][j] = true;
                        if(DFS(nh)) return true;
                        hLine_crd.remove(hLine_crd.size()-1);
                        ladder[i][j] = false;
                    }
                } else {
                    if(hLine_crd.get(hLine_crd.size()-1) < crd && !ladder[i][j]) {
                        hLine_crd.add(crd);
                        ladder[i][j] = true;
                        if(DFS(nh)) return true;
                        hLine_crd.remove(hLine_crd.size()-1);
                        ladder[i][j] = false;
                    }
                }
            }
        }
        return false;
    }
    
    static boolean check() {
        for(int i=1; i<=N; i++) {
            int high = 0;
            int line = i;
            while(high<H) {
                high += 1;
                if(line == 1) {
                    if(ladder[line][high]) line = 2;
                } else if(line == N) {
                    if(ladder[line-1][high]) line = line-1;
                } else {
                    if(ladder[line-1][high]) line = line-1;
                    else if(ladder[line][high]) line = line+1;
                }
            }
            if(i != line) return false;
        }
        return true;
    }
}