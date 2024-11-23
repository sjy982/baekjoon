import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000;
    static int T, A, S, B;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      T = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      S = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      int[] arr = new int[T + 1];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=0; i<A; i++) {
          int n = Integer.parseInt(st2.nextToken());
          arr[n] += 1;
      }
      int[][] dp = new int[T + 1][B + 1];

      for(int i=1; i<=T; i++) {
          for(int j=1; j<=B; j++) {
              dp[i][j] = dp[i - 1][j]; //현재 j가 추가되지 않은 경우
              for(int k=1; k<=arr[i]; k++) {
                  if(j < k) {
                      break;
                  } else if(j == k) {
                      dp[i][j] += 1;
                  } else {
                      dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                  }
              }
          }
      }
      
      int answer = 0;
      for(int i=S; i<=B; i++) {
          answer = (answer + dp[T][i]) % MOD;
      }
      System.out.println(answer);
  }
}