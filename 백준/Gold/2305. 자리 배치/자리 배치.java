import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      K = Integer.parseInt(br.readLine());
      
      int[] dp = new int[N + 1];
      dp[0] = 1;
      dp[1] = 1;
      dp[2] = 2;
      for(int i=3; i<=N; i++) {
          dp[i] = (dp[i - 1] - dp[i - 2]) + dp[i - 2] * 2;
      }
      
      int[] dp2 = new int[N + 1]; //1 2 3 ㅁ, ㅁ 2 3 4
      dp2[0] = 0;
      dp2[1] = 1;
      for(int i=2; i<=N; i++) {
          dp2[i] = dp2[i - 1] + dp[i - 1];
      }
      int left = K - 1;
      int right = N - K;
      int answer = dp[left] * dp[right]; //자유석이 없다라고 가정했을 때
      
      answer += cal(left, right, dp, dp2); //자유석 O
      answer += cal(right, left, dp, dp2); //자유석 O
      System.out.println(answer);
  }
  
  static int cal(int len1, int len2, int[] dp, int[] dp2) {
      int result = 0;
      for(int i=1; i<=len1; i++) {
          result += (((dp2[i] * dp[len1 - i]) + (dp[i - 1] * dp2[len1 - i])) * dp[len2]);
      }
      return result;
  }
}