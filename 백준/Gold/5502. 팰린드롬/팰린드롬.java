import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      String input = br.readLine();
      
      int[][] dp = new int[input.length() + 1][input.length() + 1]; //[A][B] A부터 B까지 팰린드롬을 만드는 최소 비용
      
      for(int i=1; i<=input.length() - 1; i++) {
          if(input.charAt(i - 1) == input.charAt(i)) {
              dp[i][i + 1] = 0;
          } else {
              dp[i][i + 1] = 1;
          }
      }
      
      for(int i=3; i<=input.length(); i++) {
          //3 부터 length()까지 구간 별 최소 비용을 구한다.
          for(int j=1; j <= input.length() - (i - 1); j++) {
              int lastInd = j + (i - 1);
              dp[j][lastInd] = Math.min(dp[j + 1][lastInd] + 1, dp[j][lastInd - 1] + 1); //새로 추가하는 경우의 수
              
              if(input.charAt(j - 1) == input.charAt(lastInd - 1)) {
                  //맨 앞과 맨 뒤가 같은 경우는 재사용 가능
                  dp[j][lastInd] = Math.min(dp[j][lastInd], dp[j + 1][lastInd - 1]);
              }
          }
          
      }
      
      System.out.println(dp[1][input.length()]);
  }
}