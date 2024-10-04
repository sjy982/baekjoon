import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      int[][] matrix = new int[N][2];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          matrix[i][0] = Integer.parseInt(st.nextToken()); //행
          matrix[i][1] = Integer.parseInt(st.nextToken()); //열
      }
      
      int[][] dp = new int[N][N];
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              if(i != j) {
                  dp[i][j] = Integer.MAX_VALUE;
              }
          }
      }
      
      for(int i=1; i<N; i++) {
          for(int j=0; j<N; j++) {
              //i -> 3이면, j는 0 1 2 3, <= (j + i)
              if(j + i >= N) {
                  //범위를 벗어남
                  break;
              }
              //j는 첫 번째, j + i은 마지막
              
              for(int k=0; k<i; k++) {
                  dp[j][j+i] = Math.min(dp[j][j+i], dp[j][j + k] + dp[j + k + 1][j + i] + matrix[j][0] * matrix[j + k][1] * matrix[j + i][1]);
              }
              
          }
      }
      
      System.out.println(dp[0][N - 1]);
  }
}