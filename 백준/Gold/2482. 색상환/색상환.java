import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000003;
    static int N, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      K = Integer.parseInt(br.readLine());
      
      long[][] ans = new long[N + 1][K + 1]; //A에서 B를 뽀는 경우의 수
      int[][] dp = new int[N + 1][K + 1];
      
      for(int i=1; i<=N; i++) {
          //하나를 뽑는 경우의 수 구하기
          ans[i][1] = i;
          dp[i][1] = i;
      }
      
      //dp부터 채우기
      for(int i=2; i<=K; i++) {
          for(int j=i+1; j<=N; j++) {
              dp[j][i] = (dp[j - 2][i - 1] + dp[j - 1][i]) % MOD;
          }
      }
      
      //ans 채우기
      for(int i=2; i<=K; i++) {
          for(int j=i+2; j<=N; j++) {
              long temp = ans[j - 1][i] - dp[j - 4][i - 1];
              if(temp < 0) {
                  //temp가 음수라면
                  temp += MOD;
              }
              ans[j][i] = (dp[j - 3][i - 1] * 2L + temp) % MOD;
          }
      }
      
      System.out.println(ans[N][K]);
  }
}