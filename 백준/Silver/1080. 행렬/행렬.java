import java.io.*;
import java.util.*;

public class Main {
    static int map[][];
    static int compare_map[][];
    static int N,M,ans;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      ans = 0;
      map = new int[N][M];
      compare_map = new int[N][M];
      for(int i=0; i<2; i++) {
          for(int j=0; j<N; j++) {
              String s = br.readLine();
              for(int k=0; k<M; k++) {
                  if(i==0) {
                      map[j][k] = s.charAt(k) - '0';
                  }else {
                      compare_map[j][k] = s.charAt(k) - '0';
                  }
              }
          }
      }
      if(compare()) {
          System.out.println(ans);
      } else {
          boolean same = false;
          for(int i=0; i<N; i++) {
              for(int j=0; j<M; j++) {
                  if(map[i][j] != compare_map[i][j]) {
                      change(j,i);
                      if(compare()) {
                          same = true;
                          break;
                      }
                  }
              }
              if(same) {
                  break;
              }
          }
          if(same) {
              System.out.println(ans);
          } else {
              System.out.println(-1);
          }
      }
    }
    static boolean compare() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != compare_map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static void change(int x, int y) {
        if((x+2<=M-1) && (y+2<=N-1)) {
            for(int i=y; i<=y+2; i++) {
                for(int j=x; j<=x+2; j++) {
                    if(map[i][j]==0) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
            ans += 1;
        }
    }
}