import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, dp;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][M];
      dp = new int[N][M];
      for(int i=0; i<N; i++) {
          String line = br.readLine();
          for(int j=0; j<M; j++) {
              //H인 경우는 -1;
              char c = line.charAt(j);
              if (c == 'H') {
                  map[i][j] = -1;
              } else {
                  map[i][j] = c - '0';
              }
              dp[i][j] = -1; //방문하지 않은 상태.
          }
      }
      
      System.out.println(dfs(0, 0, 0));
  }
  
  static int dfs(int x, int y, int depth) {
      if((N * M) < depth) {
          return -1;
      }
      
      if(!((0 <= x && x < M) && (0 <= y && y < N))) {
          //범위 밖이라면 더 이상 움직일 수 없음.
          return 0;
      }
      
      if(map[y][x] == -1) {
          //구멍이라면
          return 0;
      }
      
      if(dp[y][x] != -1) {
          //이미 방문했다면.
          return dp[y][x];
      }
      //방문하지 않은 경우.
      int result = 0;
      for(int i=0; i<4; i++) {
          int cnt = map[y][x]; //움직여야 할 횟수.
          int[] dir = findPoint(i, cnt);
          int nx = x + dir[0];
          int ny = y + dir[1];
          int value = dfs(nx, ny, depth + 1);
          if(value == -1) {
              return -1;
          }
          result = Math.max(result, 1 + value);
      }
      
      dp[y][x] = result;
      return dp[y][x];
  }
  
  static int[] findPoint(int dir, int cnt) {
      int[] result = new int[2];
      if(dir == 0) {
          //위
          result[1] = cnt * -1;
      } else if(dir == 1) {
          //오른쪽
          result[0] = cnt;
      } else if(dir == 2) {
          //아래
          result[1] = cnt;
      } else {
          //왼쪽
          result[0] = cnt * -1;
      }
      return result;
  }
}