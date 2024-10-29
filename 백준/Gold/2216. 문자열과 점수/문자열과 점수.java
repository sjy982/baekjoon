import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken()); //같을 경우
      int B = Integer.parseInt(st.nextToken()); //하나만 공백인 경우
      int C = Integer.parseInt(st.nextToken()); //두 문자가 다 다른 경우
      
      String X = br.readLine();
      String Y = br.readLine();
      
      int[][] dp = new int[X.length() + 1][Y.length() + 1];
      
      for(int i=1; i<=Y.length(); i++) {
          dp[0][i] = i * B;
      }
      
      for(int i=1; i<=X.length(); i++) {
          dp[i][0] = i * B;
      }
     
      for(int i=1; i<=X.length(); i++) {
          for(int j=1; j<=Y.length(); j++) {
              dp[i][j] = Math.max(dp[i - 1][j] + B, dp[i][j - 1] + B);
              int cv = A; //같은 경우는 그대로 사용됨
              if(X.charAt(i - 1) != Y.charAt(j - 1)) {
                  //다른 경우
                  cv = C;
              } 
              dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + cv);
          }
      }
      
      System.out.println(dp[X.length()][Y.length()]);
  }
}