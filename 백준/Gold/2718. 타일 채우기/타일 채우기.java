import java.io.*;
import java.io.*;

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      int[][] dp = new int[31][3];
      fillDp(dp);
      StringBuilder sb = new StringBuilder();
      for(int t=0; t<T; t++) {
          int N = Integer.parseInt(br.readLine());
          sb.append(dp[N][0]).append("\n");
      }
      System.out.println(sb.toString());
  }
  
  static void fillDp(int[][] dp) {
      dp[1][0] = 1;
      dp[1][1] = 2;
      dp[1][2] = 1;
      dp[2][0] = 5;
      dp[2][1] = 7;
      dp[2][2] = 6;
      for(int i=3; i<dp.length; i++) {
          dp[i][0] = dp[i - 1][0] + (dp[i - 2][1] * 2) + dp[i - 2][2] + dp[i - 2][0];
          dp[i][1] = dp[i][0] + dp[i - 1][1];
          dp[i][2] = dp[i][0] + dp[i - 2][2];
      }
  }
}