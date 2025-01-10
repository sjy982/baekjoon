import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken()); //행
      M = Integer.parseInt(st.nextToken()); //열
      
      int[][] map = new int[M + 1][N + 1]; //어떤 라인에 어떤 행에 어떤 값이 있는 지를 저장
      for(int i=1; i<=N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=1; j<=M; j++) {
              map[j][i] = Integer.parseInt(st2.nextToken());
          }
      }
      
      long[][][] dp = new long[3][M + 1][N + 1]; //3가지 타입, 어떤 라인에 어떤 행에 있을 때 최적값
      initDp(dp); //-1은 탐색하지 않은 영역.
      
      long answer = 0;
      for(int i=1; i<=N; i++) {
          for(int j=0; j<3; j++) {
              answer = Math.max(answer, findAnswer(j, 0, i, map, dp));
          }
      }
      System.out.println(answer);
  }
  
  static long findAnswer(int s, int r, int c, int[][] map, long[][][] dp) {
      if(r >= M) {
          return 0;
      }
      
      //가능한 영역
      if(dp[s][r][c] != -1) {
          //이미 방문했다면.
          return dp[s][r][c];
      }
      
      
      //방문하지 않았다면
      
       int nextR = r;
       int nextC = c;
          
       long up = 0;
       long down = 0;
       long right = 0;
      if(s == 0) {
          //정수
          //위
          nextR = r + 1;
          nextC = c - 1;
          if(1 <= nextC && nextC <= N) {
              up += map[nextR][nextC];
              up += findAnswer(s, nextR, nextC, map, dp);
          }
          
          //아래
          nextC = c + 1;
          if(1 <= nextC && nextC <= N) {
              down += map[nextR][nextC];
              down += findAnswer(s, nextR, nextC, map, dp);
          }
          
          //오른쪽
          nextR = r + 2;
          nextC = c;
          right += map[r + 1][nextC];
          if(nextR <= M) {
              right += map[nextR][nextC];
          }
          right += findAnswer(s, nextR, nextC, map, dp);
      } else if(s == 1) {
          //실수, 위
          //위
          nextR = r + 1;
          nextC = c - 1;
          if(1 <= nextC && nextC <= N) {
              up += map[nextR][nextC];
              up += findAnswer(s, nextR, nextC, map, dp);
          }
          //아래
          nextC = c + 1;
          if(1 <= nextC && nextC <= N) {
              down += map[nextR][c];
              down += findAnswer(s, nextR, nextC, map, dp);
          }
      } else {
          //s == 2 영역
          //실수, 아래
          //위
          nextR = r + 1;
          nextC = c - 1;
          if(1 <= nextC && nextC <= N) {
              up += map[nextR][c];
              up += findAnswer(s, nextR, nextC, map, dp);
          }
          //아래
          nextC = c + 1;
          if(1 <= nextC && nextC <= N) {
              down += map[nextR][nextC];
              down += findAnswer(s, nextR, nextC, map, dp);
          }
      }
      
      long v = up;
      v = Math.max(v, down);
      v = Math.max(v, right);
      dp[s][r][c] = v;
      
      return dp[s][r][c];
  }
  
  static void initDp(long[][][] dp) {
      for(int i=0; i<dp.length; i++) {
          for(int j=0; j<dp[i].length; j++) {
              for(int k=0; k<dp[i][j].length; k++) {
                  dp[i][j][k] = -1;
              }
          }
      }
  }
}