import java.io.*;
import java.util.*;

class Node {
    int x,y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int dx[] = {0,1,0,-1};
    static final int dy[] = {1,0,-1,0};
    static char rgb_map[][];
    static char rb_map[][];
    static boolean rgb_visited[][];
    static boolean rb_visited[][];
    static int N, rgb_cout, rb_cout;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      rgb_cout = 0;
      rb_cout = 0;
      rgb_map = new char[N][N];
      rb_map = new char[N][N];
      rgb_visited = new boolean[N][N];
      rb_visited = new boolean[N][N];
      for(int i=0; i<N; i++) {
          String s = br.readLine();
          for(int j=0; j<N; j++) {
              rgb_map[i][j] = s.charAt(j);
              if(rgb_map[i][j] == 'G') {
                  rb_map[i][j] = 'R';
              } else {
                  rb_map[i][j] = s.charAt(j);
              }
          }
      }
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              if(!rgb_visited[i][j]) {
                  rgb_cout += BFS(j, i, rgb_map, rgb_visited);
              }
              if(!rb_visited[i][j]) {
                  rb_cout += BFS(j, i, rb_map, rb_visited);
              }
          }
      }
    System.out.printf("%d %d",rgb_cout, rb_cout);
    }
    
    static int BFS(int sx, int sy, char map[][], boolean visited[][]) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(sx,sy));
        visited[sy][sx] = true;
        while(!que.isEmpty()) {
            Node n = que.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
                    if(!visited[ny][nx] && map[ny][nx] == map[n.y][n.x]) {
                        visited[ny][nx] = true;
                        que.add(new Node(nx,ny));
                    }
                }
            }
        }
        return 1;
    }
}