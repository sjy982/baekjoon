import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    static final int[][] CASE = {
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1},
        {1, 1, 0},
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    }; //곡을 만드는 경우의 수는 7가지
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int S = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      
      int[][][][] dp = new int[S + 1][d + 1][k + 1][h + 1];
      
      System.out.println(answer(S, d, k, h, dp));
  }
  
  static int answer(int S, int d, int k, int h, int[][][][] dp) {
      if(d == -1 || k == -1 || h == -1 || dp[S][d][k][h] == -1) {
          //안되는 경우에는 -1, -1은 방문은 했지만 경우의 수가 존재하지 않음
          return 0;
      }
      
      if(dp[S][d][k][h] > 0) {
          //이미 방문을 했고, 경우의 수가 존재함
          return dp[S][d][k][h];
      }
      
      if(S == 0) {
          if(d == 0 && k == 0 && h == 0) {
              return 1;
          }
          return 0;
      }
      
      long v = 0;
      for(int i=0; i<7; i++) {
          v += answer(S - 1, d - CASE[i][0], k - CASE[i][1], h - CASE[i][2], dp);
      }
      
      if(v == 0) {
          dp[S][d][k][h] = -1;
          return 0;
      }
      dp[S][d][k][h] = (int) (v % MOD);
      return dp[S][d][k][h];
  }
}