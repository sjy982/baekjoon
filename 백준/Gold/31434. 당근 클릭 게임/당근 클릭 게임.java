import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      int[][] dp = new int[K + 1][5001]; //[A][B] A는 초, B는 S
      int[][] item = new int[N][2]; //0은 당근 지불 개수, 1은 스피드 증가수
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          item[i][0] = Integer.parseInt(st2.nextToken());
          item[i][1] = Integer.parseInt(st2.nextToken());
      }
      init(dp);
      dp[1][1] = 1; //0초에서는 마우스 클릭이라는 선택지밖에 없음
      
      //100 * 5000 * 100
      for(int i=2; i<=K; i++) {
          for(int j=0; j<=5000; j++) {
              if(dp[i - 1][j] != -1) {
                  //전의 경우의 수가 존재한다면
                  //마우스 클릭
                  dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + j);
                  //아이템 사용
                  for(int ind = 0; ind < N; ind++) {
                      if(dp[i - 1][j] >= item[ind][0]) {
                          //당근을 지불할 수 있다면
                          dp[i][j + item[ind][1]] = Math.max(dp[i][j + item[ind][1]], dp[i - 1][j] - item[ind][0]);
                      }
                  }
              }
          }
      }
      
      int answer = -1;
      for(int i=0; i<=5000; i++) {
          answer = Math.max(answer, dp[K][i]);
      }
      System.out.println(answer);
  }
  
  static void init(int[][] dp) {
      for(int i=1; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              dp[i][j] = -1;
          }
      }
  }
}