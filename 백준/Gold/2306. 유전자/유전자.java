import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String dna = br.readLine();
      N = dna.length();
      int[][] dp = new int[N][N];
      
      for(int i=0; i<=N-2; i++) {
          if(checkKOI(dna.charAt(i), dna.charAt(i + 1))) {
              dp[i][i + 1] = 2;
          }
      }
      
      //간격 2부터 시작
      for(int i=2; i<=N; i++) {
          for(int j=0; j<=N - 1; j++) {
              int s = j;
              int e = j + i - 1;
              if(e >= N) {
                  break;
              }
              
              if(checkKOI(dna.charAt(s), dna.charAt(e))) {
                  //감싸는 형태 맨 앞과 맨 뒤가 같음
                  dp[s][e] = dp[s + 1][e - 1] + 2;
              }
              
              //붙여지는 형태
              for(int k=s + 1; k<=e; k++) {
                  dp[s][e] = Math.max(dp[s][e], dp[s][k - 1] + dp[k][e]);
              }
          }
      }
      
      System.out.println(dp[0][N - 1]);
  }
  
  static boolean checkKOI(char c1, char c2) {
      if((c1 == 'a' && c2 == 't') || (c1 == 'g' && c2 == 'c')) {
          return true;
        }
      return false;
  }
}