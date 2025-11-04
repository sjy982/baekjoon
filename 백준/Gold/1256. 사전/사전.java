import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long K;
    static final long LIMIT = 1000000000L;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Long.parseLong(st.nextToken());
      
      long[][] dp = new long[N + 1][M + 1]; // [a의 개수][b의 개수] = 전체 경우의 수
      for(int i=1; i<=N; i++) {
          dp[i][0] = 1;
          dp[i][1] = i + 1;
      }
      for(int i=1; i<=M; i++) {
          dp[0][i] = 1;
          dp[1][i] = i + 1;
      }
      
      for(int i=2; i<=N; i++) {
          for(int j=2; j<=M; j++) {
              dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], LIMIT);
          }
      }
      if(dp[N][M] < K) {
          System.out.println(-1);
          return;
      }
      StringBuilder sb = new StringBuilder();
      //첫번째 자리부터 마지막 자리까지 정해준다.
      int leftA = N;
      int leftZ = M;
      for(int i=1; i <= N+M; i++) {
          if(leftA == 0) {
              for(int j=1; j<=leftZ; j++) {
                  sb.append("z");
              }
              break;
          }
          
          if(leftZ == 0) {
              for(int j=1; j<=leftA; j++) {
                  sb.append("a");
              }
              break;
          }
          
          //a, z 둘 다 하나 이상은 남아있는 경우임.
          //결정해야할 자리는 i번째
          if(K <= dp[leftA - 1][leftZ]) {
              sb.append("a");
              leftA -= 1;
          } else {
              //K > dp[leftA - i][leftZ]
              sb.append("z");
              K -= dp[leftA - 1][leftZ];
              leftZ -= 1;
          }
      }
      System.out.println(sb.toString());
  }
}