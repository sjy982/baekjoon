import java.io.*;
import java.util.*;

public class Main {
    static final char[] arr = {'A', 'B', 'C'};
    static final int[] da = {1, 0, 0};
    static final int[] db = {0, 1, 0};
    static final int[] dc = {0, 0, 1};
    static final StringBuilder sb = new StringBuilder();
    static int N, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      boolean[][][][] visited = new boolean[31][31][31][436];
      if(dfs(0, 0, 0, 0, visited)) {
          System.out.println(sb.toString());
      } else {
          System.out.println(-1);
      }
  }
  
  static boolean dfs(int a, int b, int c, int k, boolean[][][][] visited) {
      if(!checkRange(a, b, c, k)) {
          return false;
      }
      
      if(visited[a][b][c][k]) {
          return false;
      }
      
      visited[a][b][c][k] = true;
      if(((a + b + c) == N) && (k == K)) {
          return true;
      }
      
      for(int i=0; i<3; i++) {
          int nK = calNextK(a, b, c, k, i);
          sb.append(arr[i]);
          if(dfs(a + da[i], b + db[i], c + dc[i], nK, visited)) {
              return true;
          }
          sb.deleteCharAt(sb.length() - 1);
      }
      return false;
  }
  
  static boolean checkRange(int a, int b, int c, int k) {
      if(a <= 30 && b <= 30 && c <= 30 && k <= 435) {
          return true;
      }
      return false;
  }
  
  static int calNextK(int a, int b, int c, int k, int std) {
      int result = k;
      if(std == 1) {
          //맨 끝에 B가 추가됨
          result += a;
      } else if(std == 2) {
          //맨 끝에 C가 추가됨
          result = result + (a + b);
      }
      return result;
  }
}