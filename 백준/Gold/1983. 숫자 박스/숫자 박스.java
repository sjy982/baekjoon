import java.io.*;
import java.util.*;

public class Main {
    static final int[] ac = {0, 0, 1, 1};
    static final int[] bc = {0, 1, 0, 1};
    static int N, AZC, BZC;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      ArrayList<Integer> A = new ArrayList<>();
      ArrayList<Integer> B = new ArrayList<>();
      AZC = fill(br.readLine(), A);
      BZC = fill(br.readLine(), B);
      int[][][] dp = new int[N + 1][AZC + 1][BZC + 1];
      init(dp);
      for(int i=1; i<=N; i++) {
          for(int j=0; j<=AZC; j++) {
              if(i < j) {
                  break;
              }
              for(int k=0; k<=BZC; k++) {
                  if(i < k) {
                      break;
                  }
                  for(int l=0; l<4; l++) {
                      if(j - ac[l] < 0 || k - bc[l] < 0) {
                          break;
                      }
                      int v = 0;
                      if(l == 0) {
                         v = A.get(i - (j - ac[l])) * B.get(i - (k - bc[l]));
                      }
                      dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - ac[l]][k -bc[l]] + v);
                  }
              }
          }
      }
      System.out.println(dp[N][AZC][BZC]);
  }
  
  static void init(int[][][] dp) {
      for(int i=1; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              for(int k=0; k<dp[i][j].length; k++) {
                  dp[i][j][k] = -1000000;
              }
          }
      }
  }
  
  static int fill(String input, ArrayList<Integer> list) {
      list.add(0);
      int zCout = 0;
      StringTokenizer st = new StringTokenizer(input);
      for(int i=0; i<N; i++) {
          int n = Integer.parseInt(st.nextToken());
          if(n == 0) {
              zCout += 1;
          } else {
              list.add(n);
          }
      }
      for(int i=1; i<=zCout; i++) {
          list.add(0);
      }
      return zCout;
  }
}