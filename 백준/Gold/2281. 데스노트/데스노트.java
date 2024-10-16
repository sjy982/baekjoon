import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int[] name = new int[N + 1];
      for(int i=1; i<=N; i++) {
          name[i] = Integer.parseInt(br.readLine());
      }
      
      int[][] dp = new int[N + 1][M + 1]; // A는 몇 번째 이름인지, B는 현재 줄에서 어디까지 사용했는지
      
      //초기화
      for(int i=1; i<=N; i++) {
          for(int j=1; j<=M; j++) {
              dp[i][j] = Integer.MAX_VALUE;
          }
      }
      
      dp[1][name[1]] = calSquare(M - name[1]);
      ArrayList<Integer> list = new ArrayList<>();
      list.add(name[1]);
      
      for(int i=2; i<=N; i++) {
          ArrayList nList = new ArrayList<>();
          nList.add(name[i]);
          for(int j=0; j<list.size(); j++) {
              int len = list.get(j);
              
              //이어 붙이는 경우
              if(len + (1 + name[i]) <= M) {
                  int newLen = len + (1 + name[i]);
                  int newSquareV = calSquare(M - newLen);
                  if(i == N) {
                      //마지막 줄이라면 값에 포함 x
                      newSquareV = 0;
                  }
                  if(dp[i][newLen] == Integer.MAX_VALUE) {
                      //최초 초기화라면 list에 넣어주기
                      nList.add(newLen);
                  }
                  dp[i][newLen] = Math.min(dp[i][newLen], dp[i - 1][len] - calSquare(M - len) + newSquareV);
              }
              
              //새로운 줄에 쓰는 경우
              int newSquareV2 = calSquare(M - name[i]);
              if(i == N) {
                  //마지막 줄이라면 값에 포함 x
                  newSquareV2 = 0;
              }
              dp[i][name[i]] = Math.min(dp[i][name[i]], dp[i - 1][len] + newSquareV2);
          }
          list = nList;
      }
      int answer = Integer.MAX_VALUE;
      for(int i=0; i<list.size(); i++) {
          answer = Math.min(answer, dp[N][list.get(i)]);
      }
      System.out.println(answer);
  }
  
  static int calSquare(int v) {
      return v * v;
  }
}