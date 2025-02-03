import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int initV = Integer.MAX_VALUE;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] map = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          map[i] = Integer.parseInt(st.nextToken());
      }
      int std = (N * (N + 1)) / 2; //중간 값 100이면 5050 -> 5050은 0을 뜻함.
      int[][][] dp = new int[N + 1][101][(std * 2) + 1];
      init(dp); //initV로 초기값을 채운다.
      
      for(int h=0; h<=100; h++) {
          int needBlocks = h - map[1]; // -는 줄 수 있는거, +는 받아야 하는거, 옮기는 횟수
          if(0 <= (std + needBlocks) && (std + needBlocks) <= std * 2) {
              if(needBlocks >= 0) {
                  dp[1][h][std+needBlocks] = 0;
              } else {
                  //옮겨야 됨
                  dp[1][h][std+needBlocks] = Math.abs(needBlocks);
              }
          }
      }
      
      for(int i=2; i<=N; i++) {
          //열 번호
          for(int h=0; h<=100; h++) {
              //높이
              for(int j=0; j<=std*2; j++) {
                  //dp[2][5][7],
                  //일단 가져와야될 것
                  int needBlocks = h - map[i]; //이 값이 +면 받아야 됨
                  
                  for(int bh=h - 1; bh<=h + 1; bh++) {
                      if(bh < 0) {
                          continue;
                      }
                      
                      if(bh > 100) {
                          break;
                      }
                      
                      if(needBlocks >= 0) {
                          //받아야 하는 값임 +이기 때문에
                          int nextNB = j - needBlocks;
                          if(nextNB >= 0) {
                              //0보다 작으면 모든 블록을 다 빌렸음을 의미한다.
                              if(dp[i - 1][bh][nextNB] != initV) {
                                  dp[i][h][j] = Math.min(dp[i][h][j], dp[i - 1][bh][nextNB]);
                              }
                          }
                      } else if(needBlocks < 0) {
                          //줄 수 있는 경우임.
                          int nextNB = j - needBlocks;
                          if(nextNB <= std * 2) {
                              if(dp[i - 1][bh][nextNB] != initV) {
                                  dp[i][h][j] = Math.min(dp[i][h][j], dp[i - 1][bh][nextNB] + Math.abs(needBlocks));
                              }
                          }
                      } 
                  }
              }
          }
      }
      
      int answer = initV;
      for(int h=0; h<=100; h++) {
          answer = Math.min(answer, dp[N][h][std]);
      }
      System.out.println(answer);
  }
  
  static void init(int[][][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              for(int k=0; k<dp[i][j].length; k++) {
                  dp[i][j][k] = initV;
              }
          }
      }
  }
}