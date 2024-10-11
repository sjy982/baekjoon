import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[][] map = new int[N][N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<N; j++) {
              map[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      int[][] wVisited = new int[N][N];
      
      int answer = 0;
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              answer = Math.max(answer, dfs(j, i, map, wVisited));
          }
      }
      System.out.println(answer);
      
  }
  
  static int dfs(int x, int y, int[][] map, int[][] wVisited) {
      wVisited[y][x] = 1;
      for(int i=0; i<4; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          if(((0 <= nx && nx <= N - 1) && (0 <= ny && ny <= N - 1)) && map[y][x] < map[ny][nx]) {
              //방문 가능함
              if(wVisited[ny][nx] == 0) {
                  //방문해야됨
                  wVisited[y][x] = Math.max(wVisited[y][x], dfs(nx, ny, map, wVisited) + 1);
              } else {
                  //방문 했다면 중복 방문할 필요 없음
                  wVisited[y][x] = Math.max(wVisited[y][x], wVisited[ny][nx] + 1);
              }
          }
      }
      return wVisited[y][x];
  }
}