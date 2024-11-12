import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] A = new int[N + 1];
      int[] B = new int[N + 1];
      init(new StringTokenizer(br.readLine()), A);
      init(new StringTokenizer(br.readLine()), B);
      int[][] dp = new int[N + 1][N + 1];
      for(int i=1; i<=N; i++) {
          int v  = (A[1] - B[i]) * (A[1] - B[i]);
          dp[1][i] = dp[1][i - 1] + v;
      }
      
      for(int i=2; i<=N; i++) {
          dp[i][1] = dp[i - 1][1] + (A[i] - B[1]) * (A[i] - B[1]);
          for(int j=2; j<=N; j++) {
              int v = (A[i] - B[j]) * (A[i] - B[j]);
              dp[i][j] = dp[i][j - 1] + v;
              dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + v);
              dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + v);
              
          }
      }
      System.out.println(dp[N][N]);
  }
  
  static void init(StringTokenizer st, int[] arr) {
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
  }
}