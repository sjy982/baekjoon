import java.io.*;
import java.util.*;

public class Main {
    static int P, Q;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      P = Integer.parseInt(st.nextToken());
      Q = Integer.parseInt(st.nextToken());
      int[] QArr = new int[Q + 1];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=1; i<=Q; i++) {
          QArr[i] = Integer.parseInt(st2.nextToken());
      }
      Arrays.sort(QArr);
      
      int[][] dp = new int[P + 1][P + 1];
      init(dp);
      System.out.println(findAnswer(1, P, 1, Q, QArr, dp));
  }
  
  static int findAnswer(int s, int e, int sInd, int eInd, int[] QArr, int[][] dp) {
      if(s > e) {
          return 0;
      }
      
      if(dp[s][e] != -1) {
          return dp[s][e];
      }
       
      int answer = Integer.MAX_VALUE;
      for(int i=sInd; i<=eInd; i++) {
          int std = QArr[i];
          int v = (std - s) + (e - std);
          v += findAnswer(s, std - 1, sInd, i - 1, QArr, dp);
          v += findAnswer(std + 1, e, i + 1, eInd, QArr, dp);
          answer = Math.min(answer, v);
      }
      dp[s][e] = answer;
      
      if(answer == Integer.MAX_VALUE) {
          dp[s][e] = 0;
      }
      
      return dp[s][e];
  }
  
  static void init(int[][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              dp[i][j] = -1;
          }
      }
  }
}