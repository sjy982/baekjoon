import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      int[] timeLine = new int[N + 2];
      for(int i=0; i<M; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st2.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          for(int j=a; j<b; j++) {
              timeLine[j] += 1;
          }
      }
      int[][][] dp = new int[N + 2][K + 1][K + 1];
      for(int i=1; i<=N+1; i++) {
          for(int j=0; j<=K; j++) {
              for(int l=0; l<=K; l++) {
                  dp[i][j][l] = -1;
              }
          }
      }
      dp[1][K][0] = 0;
      for(int i=1; i<=N; i++) {
          for(int j=0; j<=K; j++) {
              for(int l=0; l<=K; l++) {
                  if(dp[i][j][l] >= 0) {
                      if(timeLine[i] >= T) {
                          //T명 이상이면 투입된 영선 친구는 다 나감
                          dp[i + 1][j][0] = Math.max(dp[i + 1][j][0] , dp[i][j][l] + 1);
                      } else {
                          //T명 미만이라면 투입된 영선 친구는 유지됨
                          if(timeLine[i] + l >= T) {
                              //현재 욱제 친구들 + 투입된 영선 친구의 합이 T명 이상이라면
                              dp[i + 1][j][l] = Math.max(dp[i + 1][j][l], dp[i][j][l] + 1);
                          } else {
                              //그렇지 않다면 두 가지 유형으로 나눠짐
                              //type1 영선 친구를 투입하지 않는다.
                              dp[i + 1][j][l] = Math.max(dp[i + 1][j][l], dp[i][j][l]);
                              //type2 영선 친구를 투입한다.
                              //먼저 필요한 인원을 찾음
                              int need = T - (timeLine[i] + l);
                              if(need <= j) {
                                  //투입시킬 수 있는 여건이 된다면 투입
                                  dp[i + 1][j - need][l + need] = Math.max(dp[i + 1][j - need][l + need], dp[i][j][l] + 1);
                              }
                          }
                          
                      }
                  }
              }
          }
      }
      int answer = -1;
      for(int i=0; i<=K; i++) {
          for(int j=0; j<=K; j++) {
              answer = Math.max(dp[N + 1][i][j], answer);
          }
      }
      System.out.println(answer);
  }
}