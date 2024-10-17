import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static long I;
  public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      L = Integer.parseInt(st.nextToken());
      I = Long.parseLong(st.nextToken());
      
      long[][] dp = new long[N][L + 1]; //N - 1까지만 구하면 됨 어차피 I번째 이진수가 있는 입력만 주어지기 때문
      
      for(int i=1; i<=N - 1; i++) {
          dp[i][0] = 1;
          dp[i][1] = i;
      }
      
      for(int i=2; i<=N - 1; i++) {
          for(int j=2; j<=L; j++) {
              if(i < j) {
                  break;
              }
              dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
          }
      }
      
      for(int i=1; i<= N - 1; i++) {
          for(int j=1; j<=L; j++) {
              dp[i][j] += dp[i][j - 1];
          }
      }
      
      //N자리 수의 비트를 결정해야 됨
      StringBuilder sb = new StringBuilder();
      for(int i=N; i>=1; i--) {
          long std = i - 1 == 0 ? 1 : dp[i - 1][L];
          if(I > std) {
              sb.append(1);
              L -= 1;
              I -= std;
          } else {
              sb.append(0);
          }
      }
      System.out.println(sb.toString());
  }
}