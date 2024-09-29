import java.io.*;
import java.util.*;

class Location {
    int x, y, wl, c; //wl -> 0 안부심, wl -> 1 부심
    Location(int x, int y, int wl, int c) {
        this.x = x;
        this.y = y;
        this.wl = wl;
        this.c = c;
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
      Location hong = new Location(hx, hy, 0, 1);
      StringTokenizer st3 = new StringTokenizer(br.readLine());
      int ey = Integer.parseInt(st3.nextToken()) - 1;
      int ex = Integer.parseInt(st3.nextToken()) - 1;
      Location exit = new Location(ex, ey, 0, 0);
      
      for(int i=0; i<N; i++) {
          StringTokenizer st4 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              map[i][j] = Integer.parseInt(st4.nextToken());
          }
      }
      System.out.println(bfs(map, hong, exit));
  }
  
  
  static int bfs(int[][] map, Location hong, Location exit) {
      Queue<Location> que = new LinkedList<>();
      boolean[][][] visited = new boolean[2][N][M];
      que.add(hong);
      int result = Integer.MAX_VALUE;
      while(!que.isEmpty()) {
          Location node = que.poll();
          if(node.x == exit.x && node.y == exit.y) {
              result = Math.min(result, node.c);
          }
          for(int i=0; i<4; i++) {
              int nx = node.x + dx[i];
              int ny = node.y + dy[i];
              if((0 <= nx && nx <= M - 1) && (0 <= ny && ny <= N - 1)) {
                  if(map[ny][nx] == 0) {
                      if(!visited[node.wl][ny][nx]) {
                          visited[node.wl][ny][nx] = true;
                          que.add(new Location(nx, ny, node.wl, node.c + 1));
                      }
                  } else {
                      //벽이라면
                      if(node.wl == 0 && !visited[1][ny][nx]) {
                          //부실 수 있음
                          visited[1][ny][nx] = true;
                          que.add(new Location(nx, ny, 1, node.c));
                      }
                  }
              }
          }
      }
      if(result == Integer.MAX_VALUE) {
          return -1;
      }
      return result;
  }
}