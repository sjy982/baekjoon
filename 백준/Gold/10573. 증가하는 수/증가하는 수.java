import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      
      long[][] dp = new long[81][10]; //[A][B] -> A는 자리(오른쪽 부터 1) B는 그 자릿수 (0은 총 합을 의미) 그래서 [2][1]은 2번째 자릿수가 1일 때 증가하는 수의 총합
      long[] totalDigits = new long[81]; //[3]이면 3자리 + 2자리 + 1자리의 모든 증가하는 수의 합임 0포함
      for(int i=1; i<=9; i++) {
          dp[1][i] = 1;
          dp[1][0] += dp[1][i];
      }
      totalDigits[1] = 10;
      for(int i=2; i<=80; i++) {
          long v = 0; //뺄 값;
          for(int j=1; j<=9; j++) {
              dp[i][j] = dp[i - 1][0] - v;
              dp[i][0] += dp[i][j];
              v += dp[i - 1][j];
          }
          totalDigits[i] += dp[i][0] + totalDigits[i - 1];
      }
      
      StringBuilder sb = new StringBuilder();
      for(int t = 0; t < T; t++) {
          String input = br.readLine();
          if(!isPosible(input)) {
              sb.append(-1).append("\n");
              continue;
          }
          //증가하는 수임 이 수보다 작은 개수를 출력해야 됨
          long answer = totalDigits[input.length()]; //0을 포함
          for(int i=input.length(); i>=1; i--) {
              int ind = input.length() - i;
              int num = input.charAt(ind) - '0';
              //큰 자리부터 첫 번째 자리까지
              for(int j=num + 1; j<=9; j++) {
                  answer -= dp[i][j];
              }
          }
          answer -= 1; //자기 자신 빼기
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static boolean isPosible(String str) {
      int before = str.charAt(0) - '0';
      for(int i=1; i<str.length(); i++) {
          int cur = str.charAt(i) - '0';
          if(before > cur) {
              return false;
          }
          before = cur;
      }
      return true;
  }
}