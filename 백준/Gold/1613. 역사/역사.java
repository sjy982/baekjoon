import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 50001;
    static int n, k;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      
      int[][] dp = new int[n + 1][n + 1];
      
      for(int i=1; i<=n; i++) {
          for(int j=1; j<=n; j++) {
              //MAX로 초기화
              dp[i][j] = MAX;
          }
      }
      
      for(int i=0; i<k; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st2.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          dp[a][b] = 1;
      }
      
      floyd(dp);
      
      StringBuilder sb = new StringBuilder();
      int s = Integer.parseInt(br.readLine());
      
      for(int i=0; i<s; i++) {
          StringTokenizer st3 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st3.nextToken());
          int b = Integer.parseInt(st3.nextToken());
          
          if(dp[a][b] != MAX) {
              sb.append(-1);
          } else {
              if(dp[b][a] != MAX) {
                  sb.append(1);
              } else {
                  //두 관계 모두 MAX라면 0
                  sb.append(0);
              }
          }
          sb.append("\n");
      }
      
      System.out.println(sb.toString().trim());
      
  }
  
  static void floyd(int[][] dp) {
      for(int k=1; k<=n; k++) {
          //k 지점을 거쳐서 가는 경우를 체크
          for(int i=1; i<=n; i++) {
              for(int j=1; j<=n; j++) {
                  dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]); //ex) 1 -> 2, 2 -> 3 => 1 -> 3을 업데이트
              }
          }
      }
  }
}