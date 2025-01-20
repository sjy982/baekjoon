import java.io.*;

public class Main {
    static int N;
    static final int MAX = 1000001;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String inputStr = " " + br.readLine();
      N = inputStr.length() - 1;
      String bStr = "BANANA";
      int[] dp = new int[N + 1];
      init(dp);
      dp[6] = findChange(1, 6, inputStr, bStr);
      for(int i=7; i<=11; i++) {
          if(N < i) {
              System.out.println(dp[N]);
              return;
          }
          int NA = findChange(i - 1, i, inputStr, "NA");
          dp[i] = Math.min(dp[i], dp[i - 2] + NA);
          bStr = "B" + bStr;
          int B = findChange(1, i, inputStr, bStr);
          dp[i] = Math.min(dp[i], B);
      }
      int minB = findChange(1, 5, inputStr, "BBBBB");
      
      for(int i=12; i<=N; i++) {
          //NA를 붙이는 경우
          int NA = findChange(i - 1, i, inputStr, "NA");
          dp[i] = Math.min(dp[i], dp[i - 2] + NA);
          
          //BANANA를 붙이는 경우
          if(inputStr.charAt(i - 6) != 'B') {
              minB += 1;
          }
          minB = Math.min(minB, dp[i - 6]);
          int BANANA = findChange(i-5, i, inputStr, "BANANA");
          
          dp[i] = Math.min(dp[i], minB + BANANA);
      }
      System.out.println(dp[N]);
  }
  
  static int findChange(int s, int e, String str, String tStr) {
      int result = 0;
      for(int i=s; i<=e; i++) {
          if(str.charAt(i) != tStr.charAt(i - s)) {
              result += 1;
          }
      }
      return result;
  }
  
  static void init(int[] dp) {
      for(int i=0; i<dp.length; i++) {
          dp[i] = MAX;
      }
  }
}