import java.io.*;
import java.util.*;

class Location {
    int x, y, wl; //wl -> 0 안부심, wl -> 1 부심
    Location(int x, int y, int wl) {
        this.x = x;
        this.y = y;
        this.wl = wl;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N,M;
    
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      int[][] map = new int[N][M];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      int hy = Integer.parseInt(st2.nextToken()) - 1;
      int hx = Integer.parseInt(st2.nextToken()) - 1;
      Location hong = new Location(hx, hy, 0);
      StringTokenizer st3 = new StringTokenizer(br.readLine());
      int ey = Integer.parseInt(st3.nextToken()) - 1;
      int ex = Integer.parseInt(st3.nextToken()) - 1;
      Location exit = new Location(ex, ey, 0);
      
      for(int i=0; i<N; i++) {
          StringTokenizer st4 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              map[i][j] = Integer.parseInt(st4.nextToken());
          }
      }
      int[][][] wVisited = new int[2][N][M];
      initVisited(wVisited);
      bfs(map, hong, wVisited);
      if(wVisited[1][exit.y][exit.x] == Integer.MAX_VALUE) {
          System.out.println(-1);
      } else {
          System.out.println(Math.min(wVisited[0][exit.y][exit.x], wVisited[1][exit.y][exit.x]));
      }
  }
  
  
  static void bfs(int[][] map, Location hong, int[][][] wVisited) {
      Queue<Location> que = new LinkedList<>();
      que.add(hong);
      wVisited[0][hong.y][hong.x] = 1;
      while(!que.isEmpty()) {
          Location node = que.poll();
          for(int i=0; i<4; i++) {
              int nx = node.x + dx[i];
              int ny = node.y + dy[i];
              if((0 <= nx && nx <= M - 1) && (0 <= ny && ny <= N - 1)) {
                  if(map[ny][nx] == 0) {
                      if(wVisited[node.wl][node.y][node.x] + 1 < wVisited[node.wl][ny][nx]) {
                          wVisited[node.wl][ny][nx] = wVisited[node.wl][node.y][node.x] + 1;
                          que.add(new Location(nx, ny, node.wl));
                      }
                  } else {
                      //벽이라면
                      if(node.wl == 0) {
                          //부실 수 있음
                          if(wVisited[node.wl][node.y][node.x] < wVisited[1][ny][nx]) {
                              wVisited[1][ny][nx] = wVisited[node.wl][node.y][node.x];
                              que.add(new Location(nx, ny, 1));
                          }
                      }
                  }
              }
          }
      }
  }
  
  static void initVisited(int[][][] visited) {
      for(int i=0; i<2; i++) {
          for(int j=0; j<N; j++) {
              for(int k=0; k<M; k++) {
                  visited[i][j][k] = Integer.MAX_VALUE;
              }
          }
      }
  }
}