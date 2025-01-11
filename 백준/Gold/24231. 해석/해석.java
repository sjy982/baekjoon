import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    static int S;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      S = input.length();
      boolean[] enStr = new boolean[S + 1];
      for(int i=0; i<input.length(); i++) {
          if(input.charAt(i) == '0') {
              enStr[i + 1] = false;
          } else {
              enStr[i + 1] = true;
          }
      }
      
      int[][] dp = new int[S + 1][S + 1];
      initDp(dp);
      System.out.println(findAnswer(1, S, enStr, dp));
  }
  
  static int findAnswer(int start, int end, boolean[] enStr, int[][] dp) {
      if(start == end) {
          //하나만 남은 경우는 불가능
          return 0;
      }
      
      if(start > end) {
          //start가 앞서는 경우는 가능한 경우
          return 1;
      }
      
      if(dp[start][end] >= 0) {
          return dp[start][end];
      }
      
      dp[start][end] = 0;
      for(int i=start+1; i<=end; i++) {
          if(!enStr[start] == enStr[i]) {
              //쌍이 만들어 진다면.
              int fv =  findAnswer(start + 1, i - 1, enStr, dp);
              int bc =  findAnswer(i + 1, end, enStr, dp);
              long finalV = (long) fv * (long) bc;
              finalV = (finalV + dp[start][end]) % MOD;
              dp[start][end] = (int) finalV;
          }
      }
      
      return dp[start][end];
  }
  
  static void initDp(int[][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              dp[i][j] = -1; //탐색하지 않음
          }
      }
  }
}