import java.io.*;
import java.util.*;

public class Main {
    static int T, W;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      T = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      
      int[] time = new int[T + 1];
      for(int i=1; i<=T; i++) {
          time[i] = Integer.parseInt(br.readLine());
      }
      
      int[][][] dp = new int[T + 1][W + 1][3];
      for(int i=0; i<=T; i++) {
          for(int j=0; j<=W; j++) {
              dp[i][j][1] = -1;
              dp[i][j][2] = -1;
          }
      }
      dp[0][0][1] = 0;
      for(int t = 1; t <= T; t++) {
          //전 시간 대에 모든 움직임 횟수를 반복 -> 그리고 두 가지 경우 가만히 있는다, 다른 나무로 이동한다.
          for(int w = 0; w <= W; w++) {
              for(int tree = 1; tree <= 2; tree++) {
                  if(dp[t - 1][w][tree] == -1) {
                      continue;
                  }
                  
                  //가만히 있는다.
                  int cnt = tree == time[t] ? 1 : 0;
                  dp[t][w][tree] = Math.max(dp[t][w][tree], dp[t - 1][w][tree] + cnt);
                  
                  //다른 나무로 이동한다.
                  if(w == W) {
                      continue;
                  }
                  //이동 가능
                  int nextTree = tree == 1 ? 2 : 1;
                  int cnt2 = nextTree == time[t] ? 1 : 0;
                  dp[t][w + 1][nextTree] = Math.max(dp[t][w + 1][nextTree], dp[t - 1][w][tree] + cnt2);
              }
          }
      }
      
      int answer = 0;
      for(int i=0; i<=W; i++) {
          for(int j=1; j<=2; j++) {
              answer = Math.max(answer, dp[T][i][j]);
          }
      }
      System.out.println(answer);
  }
}