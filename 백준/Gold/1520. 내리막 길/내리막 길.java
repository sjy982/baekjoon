import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N,M;
    static int[][] dp;
    static int[][] map;
    static boolean[][] visited;
  public static void main(String args[]) throws IOException {
      //dfs + dp 풀이
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken()); //행
      M = Integer.parseInt(st.nextToken()); //열
      
      dp = new int[N][M];
      map = new int[N][M];
      visited = new boolean[N][M];
      
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              map[i][j] = Integer.parseInt(st2.nextToken());
          }
      }
      
      System.out.println(dfs(0, 0));
  }
  
  static int dfs(int x, int y) {
      if(x == M - 1 && y == N - 1) {
          return 1;
      }
      visited[y][x] = true;
      int cnt = 0;
      for(int i=0; i<4; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          
          if((0 <= nx && nx <= M - 1) && (0 <= ny && ny <= N - 1)) {
              if(map[y][x] <=  map[ny][nx]) {
                  //내가 가려는 곳이 같거나 더 높은 지대라면
                  continue;
              }
              if(visited[ny][nx]) {
                  //내가 가려는 곳이 이미 방문했다면
                  cnt += dp[ny][nx];
                  continue;
              }
              cnt += dfs(nx, ny);
          }
      }
      dp[y][x] = cnt;
      return cnt;
  }
}