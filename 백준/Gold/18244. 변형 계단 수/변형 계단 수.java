import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    static final int inDe[] = {1, -1}; //증가, 감소
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      int[][][][] dp = new int[2][3][101][10]; //[A][B][C][D] -> A는 감소,증가, B는 몇 번째 증가 인지, C는 몇 번째 자리인지, D는 어떤 수인지
      init(dp); //-1은 방문하지 않음을 의미
      
      int answer = 0;
      if(N == 1) {
          System.out.println(10);
          return;
      }
      for(int i=0; i<=9; i++) {
          for(int j=0; j<=1; j++) {
              //증가 or 감소
              answer = (answer + find(j, 1, 2, i + inDe[j], dp)) % MOD;
          }
      }
      System.out.println(answer);
  }
  
  static int find(int A, int B, int C, int D, int[][][][] dp) {
      //[A][B][C][D] -> A는 감소,증가, B는 몇 번째 증가 인지, C는 몇 번째 자리인지, D는 어떤 수인지
      if(D < 0 || 9 < D) {
          //D는 0부터 9 사이의 수임
          return 0;
      }
      
      if(3 <= B) {
          //증가, 감소가 연속 3번 이상인 경우 탐색 x
          return 0;
      }
      
      if(C == N) {
          return 1;
      }
      
      if(dp[A][B][C][D] != -1) {
          //이미 방문을 했다면
          return dp[A][B][C][D];
      }
      
      int v = 0;
      for(int i=0; i<=1; i++) {
          //증가 or 감소
          int nextB = B + 1;
          if(A == 0 && i == 1) {
              //현재 증가 중인데 감소하는 경우
              nextB = 1;
          }
          if(A == 1 && i == 0) {
              //현재 감소 중인데 증가하는 경우
              nextB = 1;
          }
          v = (v + find(i, nextB, C + 1, D + inDe[i], dp)) % MOD;
      }
      
      dp[A][B][C][D] = v;
      return dp[A][B][C][D];
      
  }
  
  static void init(int[][][][] arr) {
      for(int i=0; i<arr.length; i++) {
          for(int j=0; j<arr[i].length; j++) {
              for(int k=0; k<arr[i][j].length; k++) {
                  for(int l=0; l<arr[i][j][k].length; l++) {
                      arr[i][j][k][l] = -1;
                  }
              }
          }
      }
  }
}