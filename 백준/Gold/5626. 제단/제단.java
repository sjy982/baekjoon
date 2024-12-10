import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[N + 1];
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
          if(arr[i] > 5000) {
              System.out.println(0);
              return;
          }
      }
      if(arr[0] >= 1 || arr[N] >= 1) {
          System.out.println(0);
          return;
      }
      int[][] dp = new int[N + 1][5001]; //최대 5000이기 때문에
      dp[1][0] = 1;
      for(int i=2; i<=N; i++) {
          if(arr[i] == -1) {
              dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
              if(i == N) {
                  break;
              }
              int sum = (dp[i][0] + dp[i - 1][2]) % MOD;
              for(int j=1; j<=5000; j++) {
                  dp[i][j] = sum;
                  sum -= dp[i - 1][j - 1];
                  if(sum < 0) {
                      sum += MOD;
                  }
                  if(j + 2 <= 5000) {
                      sum = (sum + dp[i - 1][j + 2]) % MOD;
                  }
                  
              }
          } else {
              //-1이 아닌 경우는
              dp[i][arr[i]] = dp[i - 1][arr[i]];
              if(arr[i] - 1 >= 0) {
                  dp[i][arr[i]] = (dp[i][arr[i]] + dp[i - 1][arr[i] - 1]) % MOD;
              }
              if(arr[i] + 1 <=5000) {
                  dp[i][arr[i]] = (dp[i][arr[i]] + dp[i - 1][arr[i] + 1]) % MOD;
              }
          }
      }
      System.out.println(dp[N][0]);
  }
}