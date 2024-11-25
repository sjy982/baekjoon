import java.io.*;
import java.util.*;

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int t=0; t<T; t++) {
          boolean[][] dp = new boolean[501][5001];
          dp[0][0] = true;
          StringTokenizer st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken());
          int M = Integer.parseInt(st.nextToken());
          int[] list = new int[M];
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int i=0; i<M; i++) {
              list[i] = Integer.parseInt(st2.nextToken());
          }
          for(int i=1; i<=500; i++) {
              if(i > N) {
                  break;
              }
              for(int j=i; j<=N; j++) {
                  int beforeCnt = j - i; //전의 총 팔굽 횟수
                  for(int k=0; k<M; k++) {
                      int beforeScore = i - list[k];
                      if(beforeScore >= 0 && dp[beforeScore][beforeCnt]) {
                          dp[i][j] = true;
                          break;
                      }
                  }
              }
          }
          int answer = -1;
          for(int i=500; i>=1; i--) {
              if(dp[i][N]) {
                  answer = i;
                  break;
              }
          }
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}