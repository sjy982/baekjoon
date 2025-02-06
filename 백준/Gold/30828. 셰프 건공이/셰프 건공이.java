import java.io.*;
import java.util.*;

// class State {
//     int xorV, cnt;
//     State(int xorV, int cnt) {
//         this.xorV = xorV;
//         this.cnt = cnt;
//     }
// }

public class Main {
    static int N, Q;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      int[][][] dp = new int[N + 1][N + 1][511 + 1];
      init(dp); // -1로 초기화
      for(int l=1; l<=N; l++) {
          dp[l][l - 1][0] = 0;
          for(int r=l; r<=N; r++) {
              for(int t=0; t<=511; t++) {
                  if(dp[l][r - 1][t] != -1) {
                      //값이 존재한다면
                      //현재 재료를 선택하는 경우와 그렇지 않은 경우로 나눠진다.
                      
                      //선택한 경우
                      int sv = t ^ arr[r]; //xor한 값
                      dp[l][r][sv] = Math.max(dp[l][r][sv], dp[l][r - 1][t] + 1); //재료를 선택했기 때문에 +1
                      
                      //선택하지 않은 경우
                      dp[l][r][t] = Math.max(dp[l][r][t], dp[l][r - 1][t]);
                  }
              }
          }
      }
      
      StringBuilder sb = new StringBuilder();
      Q = Integer.parseInt(br.readLine());
      for(int i=0; i<Q; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int l = Integer.parseInt(st2.nextToken());
          int r = Integer.parseInt(st2.nextToken());
          int answer = -1;
          for(int t=0; t<=511; t++) {
              if(dp[l][r][t] != -1) {
                  answer = Math.max(answer, t + dp[l][r][t]);
              }
          }
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static void init(int[][][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              for(int k=0; k<dp[i][j].length; k++) {
                  dp[i][j][k] = -1;
              }
          }
      }
  }
}