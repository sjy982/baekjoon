import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      T = Integer.parseInt(br.readLine());
      ArrayList<Integer> list = new ArrayList<>();
      for(int i=0; i<T; i++) {
          list.add(Integer.parseInt(br.readLine()));
      }
      
      int[][] dp = new int[N + 2][N + 2]; //[left][right];
      for(int i=N; i>=1; i--) {
          dp[0][i] = N - (i - 1);
      }
      for(int i=1; i<=N; i++) {
          dp[i][N + 1] = i;
      }
      
      for(int i=1; i<=N - 2; i++) {
          dp[i][N] = arr[i] == arr[N] ? i - 1 : 1 + dp[i - 1][N];
          for(int j=N - 1; j>=i+2; j--) {
              if(arr[i] == arr[j]) {
                  //같다면
                  dp[i][j] = dp[i - 1][j + 1];
              } else {
                  //같지 않다면
                  dp[i][j] = Math.min(dp[i][j + 1] + 1, dp[i - 1][j] + 1);
              }
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<list.size(); i++) {
          int mv = list.get(i);
          sb.append(dp[mv - 1][mv + 1]).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}