import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      long[][][] dp = new long[N + 1][221][221]; // [A][B][C] -> A는 몇 번째 자리인지, B는 수, C는 합
      for(int i=1; i<=220; i++) {
          dp[1][i][i] = 1;
      }
      
      for(int i=2; i<=N; i++) {
          for(int j=1; j<=220; j++) {
              for(int k=2; k<=220; k++) {
                  dp[i][j][k] = find(i - 1, j, k - j, dp);
              }
          }
      }
      
      StringBuilder sb = new StringBuilder();
      int nextNum = 1;
      for(int i=N; i>=1; i--) {
          long sum = 0;
          for(int j=nextNum; j<=220; j++) {
              sum += dp[i][j][M];
              if(sum >= K) {
                  sb.append(j).append(" ");
                  K -= (sum - dp[i][j][M]); 
                  M -= j;
                  nextNum = j;
                  break;
              }
          }
      }
      System.out.println(sb.toString().trim());
  }
  
  static long find(int s, int num, int value, long[][][] dp) {
      if(value <= 0 ) {
          return 0;
      }
      long rv = 0;
      for(int i = num; i<=220; i++) {
          if(dp[s][i][value] > 0) {
              rv += dp[s][i][value];
          }
      }
      return rv;
  }
}