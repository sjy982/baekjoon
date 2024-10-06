import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y, h;
    Node(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.h < o.h) {
            return 1;
        } else if(this.h > o.h) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static boolean[][] visited;
    static int[][] map;
    static int[][] dp;
    static int N,M;
  public static void main(String args[]) throws  IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken()); //행
      M = Integer.parseInt(st.nextToken()); //열
      
      visited = new boolean[N][M];
      map = new int[N][M];
      dp = new int[N][M];
      
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              map[i][j] = Integer.parseInt(st2.nextToken());
          }
      }
      bfs();
      System.out.println(dp[N - 1][M - 1]);
  }
  
  static void bfs() {
      PriorityQueue<Node> pq = new PriorityQueue<>();
      pq.add(new Node(0, 0, map[0][0]));
      visited[0][0] = true;
      dp[0][0] = 1;
      
      while(!pq.isEmpty()) {
          Node n = pq.poll();
          for(int i=0; i<4; i++) {
              int nx = n.x + dx[i];
              int ny = n.y + dy[i];
              if((0<= nx && nx <= M - 1) && (0 <= ny && ny <= N-1)) {
                  if(n.h > map[ny][nx]) {
                      dp[ny][nx] += dp[n.y][n.x];
                      if(!visited[ny][nx]) {
                          visited[ny][nx] = true;
                          pq.add(new Node(nx, ny, map[ny][nx]));
                      }
                  }
              }
          }
      }
  }
}