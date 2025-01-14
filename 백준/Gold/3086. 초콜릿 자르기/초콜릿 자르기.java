import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int maxLen = Math.max(N, M);
      int[][] dp = new int[maxLen + 1][maxLen + 1];
      for(int i=1; i<=maxLen; i++) {
          dp[1][i] = i;
          dp[i][1] = i;
      }
      
      for(int i=2; i<=maxLen; i++) {
          for(int j=2; j<=maxLen; j++) {
              if(i == j) {
                  dp[i][j] = 1;
              } else {
                  if(dp[i][j] == 0) {
                      //열
                      dp[i][j] = dp[i][j - 1] + dp[i][1];
                      for(int k=2; k<=j/2; k++) {
                          dp[i][j] = Math.min(dp[i][j], dp[i][j - k] + dp[i][k]);
                      }
                      
                      //행
                      dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dp[1][j]);
                      for(int k=2; k<=i/2; k++) {
                          dp[i][j] = Math.min(dp[i][j], dp[i - k][j] + dp[k][j]);
                      }
                      dp[j][i] = dp[i][j];
                  }
              }
          }
      }
      System.out.println(dp[N][M]);
  }
}