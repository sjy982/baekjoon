import java.io.*;
import java.util.*;

class Info {
    int h;
    String t;
    Info(int h, String t) {
        this.h = h;
        this.t = t;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[][] bricks = new int[N + 1][3]; // [i][0] -> 넓이, [1] -> 높이, [2] -> 무게
      for(int i=1; i<=N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<3; j++) {
              bricks[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      Info[] dp = new Info[N + 1]; 
      
      Info ans = new Info(-1, "");
      for(int i=1; i<=N; i++) {
          Info rt = answer(bricks, dp, i);
          if(ans.h < rt.h) {
              ans = rt;
          }
      }
      
      StringBuilder sb = new StringBuilder();
      String[] ansTop = ans.t.split(" ");
      sb.append(ansTop.length).append("\n");
      for(int i=ansTop.length - 1; i>=0; i--) {
          sb.append(ansTop[i]).append("\n");
      }
      
      System.out.println(sb.toString().trim());
      
      
  }
  
  static Info answer(int[][] bricks, Info[] dp, int curIndex) {
      if(dp[curIndex] != null) {
          return dp[curIndex];
      }
      int totalh = bricks[curIndex][1];
      String top = "";
      
      for(int i=1; i<=N; i++) {
          if(bricks[i][0] < bricks[curIndex][0] && bricks[i][2] < bricks[curIndex][2]) {
              //넓이와 무게가 더 작다면 탑을 쌓을 수 있음
              Info info = answer(bricks, dp, i);
              if(totalh < bricks[curIndex][1] + info.h) {
                  totalh = bricks[curIndex][1] + info.h;
                  top = info.t;
              }
          }
      }
      StringBuilder sb = new StringBuilder();
      sb.append(curIndex).append(" ").append(top);
      dp[curIndex] = new Info(totalh, sb.toString());
      
      return dp[curIndex];
  }
}