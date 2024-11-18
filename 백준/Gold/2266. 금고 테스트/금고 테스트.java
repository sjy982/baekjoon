import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000;
    static int N,K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      int[][] dp = new int[N + 1][K + 1];
      init(dp);
      System.out.println(answer(N, K, dp));
  }
  
  static int answer(int end, int leftSafe, int[][] dp) {
      if(end == 0) {
          //불가능한 경우
          return 0;
      }
      if(leftSafe == 1) {
          //1개 남은 경우는 남은 모든 층을 순회해야됨
          return end;
      }
      if(end == 1) {
          //층이 하나 남은 경우 낙회 횟수 1
          return 1;
      }
      
      if(dp[end][leftSafe] != MAX) {
          //초기화 값이 아니라면 이미 방문했음을 의미
          return dp[end][leftSafe];
      }
      
      for(int i=1; i<=end; i++) {
          int case1 = answer(i - 1, leftSafe - 1, dp); //깨진 경우
          int case2 = answer(end - i, leftSafe, dp); //깨지지 않은 경우
          int v = Math.max(case1, case2);
          dp[end][leftSafe] = Math.min(dp[end][leftSafe], v + 1);
          
      }
      return dp[end][leftSafe];
  }
  
  static void init(int[][] dp) {
      for(int i=1; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              dp[i][j] = MAX;
          }
      }
  }
}