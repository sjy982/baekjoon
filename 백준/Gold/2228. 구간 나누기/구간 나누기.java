import java.io.*;
import java.util.*;

public class Main {
    static final int MIN = -32768 * 100;
    static int N, M;
    
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      int[][] dp = new int[N + 1][M + 1];
      int[] arr = new int[N];
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(br.readLine());
      }
      
      for(int i=0; i<=N; i++) {
          for(int j=1; j<=M; j++) {
              dp[i][j] = MIN;
          }
      }
      dp[1][1] = arr[0];
      for(int i=1; i<=M; i++) {
          for(int j=i+1; j<=N; j++) {
              int sum = 0;
              for(int k=j-2; k>=-1; k--) {
                  if(i != 1 && k < i - 1) {
                      break;
                  }
                  int front = 0;
                  if(k >= 0) {
                      front += dp[k][i - 1];
                  }
                  if(front == MIN) {
                      //구간이 존재할 수 없기 때문에 
                      break;
                  }
                  sum += arr[k + 1];
                  dp[j][i] = Math.max(dp[j][i], front + sum);
              }
              dp[j][i] = Math.max(dp[j][i], dp[j - 1][i]);
          }
      }
      
      System.out.println(dp[N][M]);
  }
}