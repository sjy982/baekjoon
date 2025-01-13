import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String O = br.readLine();
      String N = br.readLine();
      int[][] dp = new int[O.length() + 1][N.length() + 1];
      for(int i=1; i<=N.length(); i++) {
          dp[0][i] = 1;
      }
      
      for(int i=1; i<=O.length(); i++) {
          char std = O.charAt(i - 1);
          int ind = -1;
          for(int j=i; j<=N.length(); j++) {
              if(std == N.charAt(j - 1)) {
                  //같다면
                  if(dp[i - 1][j - 1] != -1) {
                      if(ind == -1) {
                          dp[i][j] = dp[i - 1][j - 1];
                          ind = j;
                      } else {
                          if(dp[i - 1][j - 1] <= dp[i][ind]) {
                              dp[i][j] = dp[i - 1][j - 1];
                              ind = j;
                          } else {
                              dp[i][j] = 1 + dp[i][ind];
                          }
                      }
                  } else {
                      dp[i][j] = -1;
                  }
              } else {
                  //다르면
                  if(ind == -1) {
                      dp[i][j] = -1;
                  } else {
                      dp[i][j] = 1 + dp[i][ind];
                  }
              }
          }
      }
      
      System.out.println(dp[O.length()][N.length()]);
  }
}