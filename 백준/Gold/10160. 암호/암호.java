import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000009;
    static int N,K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      long[] dp = new long[N + 1];
      dp[0] = 1;
      for(int i=1; i<=4; i++) {
          dp[i] = (dp[i - 1] * K) % MOD;
      }
      
      for(int i=5; i<=N; i++) {
          dp[i] = (dp[i - 1] * K) % MOD;
          dp[i] = (dp[i] - dp[i - 5] * 2 + MOD + MOD) % MOD;
          if(i >= 7) {
              dp[i] = (dp[i] + dp[i - 7]) % MOD;
          }
      }
      System.out.println(dp[N]);
  }
}