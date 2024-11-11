import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1999;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      int[][] dp = new int[N + 1][10000];
      dp[0][N] = 1; //자기자신
      dp[1][N] = 1;
      dp[N][1] = 1;
      for(int i=2; i<=N; i++) {
          for(int j=1; j<=i; j++) {
              dp[i][N] += dp[i - j][N];
          }
          dp[i][N] %= MOD;
          dp[N][i] = dp[i][N];
      }
      int bnr = dp[N][N] - 1;//N * N을 회전하지 않은 블록으로 채운 경우의 수 자기 자신 제외
      dp[N][N] = ((dp[N][N] * 2) - 1) % MOD;
      for(int i=N+1; i<=M; i++) {
          for(int j=1; j<=N; j++) {
              if(j == N) {
                  dp[N][i] += dp[N][i - j] * (1 + bnr);
              } else {
                  dp[N][i] += dp[N][i - j];
              }
          }
          dp[N][i] %= MOD;
      }
      System.out.println(dp[N][M]);
  }
}