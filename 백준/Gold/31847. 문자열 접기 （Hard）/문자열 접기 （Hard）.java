import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      String S = " " + br.readLine();
      int[][] dp = new int[N + 1][N + 1]; //[l][r]
      int[][] answerDp = new int[N + 1][N + 1];
      for(int i=1; i<=N - 1; i++) {
          if(S.charAt(i) == S.charAt(i + 1)) {
              dp[i][i + 1] = 1;
              answerDp[i][i + 1] = 1;
          } 
      }
      
      for(int k=4; k<=N; k++) {
          //k는 구간의 길이를 뜻함. 구간 짝수 길이
          for(int i=1; i<=N; i++) {
              if(k % 2 == 1) {
                 //홀수 일때는 건너뛴다.
                 break;
              }
              int end = i + k - 1; //구간의 마지막 인덱스
              if(end > N) {
                  //end가 넘으면 break;
                  break;
              }
              int v = 0;
              if(S.charAt(i) == S.charAt(end)) {
                  v += 1;
              }
              dp[i][end] = v + dp[i + 1][end - 1];
          }
      }
      for(int k=3; k<=N; k++) {
          for(int i=1; i<=N; i++) {
              int end = i + k - 1;
              if(end > N) {
                  break;
              }
              
              answerDp[i][end] = dp[i][end];
              answerDp[i][end] = Math.max(answerDp[i][end], answerDp[i + 1][end]);
              answerDp[i][end] = Math.max(answerDp[i][end], answerDp[i][end - 1]);
          }
      }
     
      StringBuilder sb = new StringBuilder();
      Q = Integer.parseInt(br.readLine());
      for(int t=0; t<Q; t++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int s = Integer.parseInt(st.nextToken());
          int e = Integer.parseInt(st.nextToken());
          sb.append(answerDp[s][e]).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}