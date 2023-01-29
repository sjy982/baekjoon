import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int ans = 0;
    static boolean not_combi[][];
    static Stack<Integer> result = new Stack<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      not_combi = new boolean[N+1][N+1];
      for(int i=0; i<M; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          int fn = Integer.parseInt(n_st.nextToken());
          int sn = Integer.parseInt(n_st.nextToken());
          not_combi[fn][sn] = true;
          not_combi[sn][fn] = true;
      }
      DFS(1);
      System.out.println(ans);
    }
    
    static void DFS(int ind) {
        if(result.size() == 3) {
            ans += 1;
            return;
        }
        for(int i=ind; i<=N; i++) {
            if(result.size() == 0) {
                result.push(i);
                DFS(i+1);
                result.pop();
            } else {
                if(check(result, i)) {
                    result.push(i);
                    DFS(i+1);
                    result.pop();
                }
            }
        }
    }
    
    static boolean check(Stack<Integer> s, int n) {
        boolean r = true;
        for(int i=0; i<s.size(); i++) {
            if(not_combi[s.get(i)][n]) {
                r = false;
                break;
            }
        }
        return r;
    }
}