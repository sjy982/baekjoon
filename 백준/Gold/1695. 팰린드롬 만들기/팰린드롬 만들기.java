import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      int dp[][] = new int[N][N];
      for(int i=0; i<=N - 2; i++) {
          if(arr[i] != arr[i + 1]) {
              dp[i][i + 1] = 1;
          }
      }
      
      for(int i=2; i<=N; i++) {
          for(int j=0; j<=N - 1; j++) {
              int s = j;
              int e = i + j - 1;
              if(e >= N) {
                  break;
              }
              
              if(arr[s] == arr[e]) {
                  dp[s][e] = dp[s + 1][e - 1];
              } else {
                  dp[s][e] = Math.min(dp[s][e - 1] + 1, dp[s + 1][e] + 1);
              }
          }
      }
      
      System.out.println(dp[0][N - 1]);
  }
}