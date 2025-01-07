import java.io.*;
import java.util.*;

class DpDetail {
    int v, cn;
    DpDetail next;
    DpDetail(int v, int cn, DpDetail next) {
        this.v = v;
        this.cn = cn;
        this.next = next;
    }
}

public class Main {
    static int N, M, maxA;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      maxA = N * 20;
      int[] arr = new int[N + 1];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st2.nextToken());
      }
      DpDetail[][][] dp = new DpDetail[N + 1][M + 1][maxA + 1]; //아침 상태를 기준으로함
      
      DpDetail answer = findAnswer(1, M, 0, arr, dp);
      StringBuilder sb = new StringBuilder();
      sb.append(answer.v).append("\n");
      
      DpDetail cur = answer;
      while(cur != null) {
          if(cur.cn != -1) {
              sb.append(cur.cn).append(" ");
          }
          
          cur = cur.next;
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static DpDetail findAnswer(int n, int m, int a, int[] arr, DpDetail[][][] dp) {
      if(n == (N + 1)) {
          return new DpDetail(0, -1, null);
      }
      
      if(dp[n][m][a] != null) {
          return dp[n][m][a];
      }
      
      DpDetail clienNext = null;
      if(m > 0) {
          //청소 가능
          clienNext = findAnswer(n + 1, m - 1, 0, arr, dp); //청소 후 다음날 아침 상태
      }
      
      DpDetail noClienNext = findAnswer(n + 1, m, a + arr[n], arr, dp); //청소 x 다음날 아침 상태
      
      int nv = a * arr[n];
      if((clienNext != null) && (clienNext.v <= noClienNext.v)) {
          dp[n][m][a] = new DpDetail(nv + clienNext.v, n, clienNext);
      } else {
          dp[n][m][a] = new DpDetail(nv + noClienNext.v, -1, noClienNext);
      }
      
      return dp[n][m][a];
      
  }
}