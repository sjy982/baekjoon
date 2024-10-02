import java.io.*;
import java.util.*;

class Node {
    Point p;
    int h, c, d;
    Node(Point p, int h, int d, int c) {
        this.p = p;
        this.h = h;
        this.c = c;
        this.d = d;
    }
}

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static Point start;
    static int N, H, D;
    static int answer = Integer.MAX_VALUE;
  public static void main(String args[]) throws IOException {
      BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      D = Integer.parseInt(st.nextToken());
      
      char[][] map = new char[N][N];
      for(int i=0; i<N; i++) {
          String input = br.readLine();
          for(int j=0; j<N; j++) {
              map[i][j] = input.charAt(j);
              if(map[i][j] == 'S') {
                  start = new Point(j, i);
              }
          }
      }
      
      bfs(map);
      if(answer == Integer.MAX_VALUE) {
          System.out.println(-1);
      } else {
          System.out.println(answer);
      }
  }
  
  static void bfs(char[][] map) {
      Queue<Node> que = new LinkedList<>();
      int[][] visited = new int[N][N];
      visited[start.y][start.x] = H;
      que.add(new Node(start, H, 0, 0));
      
      while(!que.isEmpty()) {
          Node n = que.poll();
          
          if(map[n.p.y][n.p.x] == 'E') {
              answer = Math.min(answer, n.c);
              continue;
          }
          
          for(int i=0; i<4; i++) {
              int nx = n.p.x + dx[i];
              int ny = n.p.y + dy[i];
              if((0 <= nx && nx <= N - 1) && (0 <= ny  && ny <= N-1)) {
                  int nh = n.h;
                  int nd = n.d;
                  if(map[ny][nx] == 'U') {
                      nd = D - 1;
                  } else if(map[ny][nx] == '.') {
                      if(nd > 0) {
                          nd -= 1;
                      } else {
                          nh -= 1;
                      }
                  }
                  if(nh + nd > visited[ny][nx]) {
                      //더 좋은 상태로 방문할 수 있다면
                      visited[ny][nx] = nh + nd;
                      que.add(new Node(new Point(nx, ny), nh, nd, n.c + 1));
                  }
                  
              }
          }
      }
  }
}