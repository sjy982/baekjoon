import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      int[][] dp = new int[N + 1][N + 1];
      init(dp);
      dp[1][0] = 0;
      
      for(int i=2; i<=N; i++) {
          //이번에 불려야 될 음 => i
          for(int j=0; j<=i-2; j++) {
              //dp[i - 1][0 ~ ...]
              //(i - 1)에서 i를 부르는 경우
              dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + Math.abs(arr[i - 1] - arr[i]));
              
              //j에서 i를 부르는 경우
              dp[i][i - 1] = Math.min(dp[i][i - 1], dp[i - 1][j] + (j == 0 ? 0 : Math.abs(arr[j] - arr[i])));
          }
      }
      
      int answer = Integer.MAX_VALUE;
      for(int i=0; i<=N - 1; i++) {
          answer = Math.min(answer, dp[N][i]);
      }
      System.out.println(answer);
  }
  
  static void init(int[][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              dp[i][j] = Integer.MAX_VALUE;
          }
      }
  }
}