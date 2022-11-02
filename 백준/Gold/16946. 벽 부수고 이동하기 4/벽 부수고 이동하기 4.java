import java.io.*;
import java.util.*;

class Node {
    int x,y,z,c;
    Node(int x, int y, int z, int c) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.c = c;
    }
}

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static final int wx[] = {0,0,-1,1};
    static final int wy[] = {-1,1,0,0};
    static int map[][];
    static int w_map[][];
    static Coordinate c_map[][]; 
    static boolean visited[][];
    static ArrayList<Coordinate> sc_arr = new ArrayList<Coordinate>();
    static int N, M;
    static String ans;
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][M];
      w_map = new int[N][M];
      c_map = new Coordinate[N][M];
      for(int i=0; i<N; i++) {
          String s = br.readLine();
          Arrays.fill(w_map[i], -1);
          for(int j=0; j<M; j++) {
              map[i][j] = s.charAt(j) - '0';
              if(map[i][j] == 0) {
                  w_map[i][j] = 0;
              }
          }
      }

      for(int i=0; i<N; i++) {
          for( int j=0; j<M; j++) {
              if(w_map[i][j] == 0) {
                  Coordinate c_start = new Coordinate(j,i);
                  w_DFS(j,i, c_start);
              }
          }
      }
     visited = new boolean[N][M];
     StringBuilder sb = new StringBuilder();
     for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
             if(map[i][j] == 1) {
                 sb.append(find_route(j,i) % 10);
                 back_visited();
             } else {
                 sb.append(0);
             }
         }
         if(i!=N-1) {
             sb.append("\n");
         } 
     }
     System.out.println(sb);
    //  ans = new String("");
    //  for(int i=0; i<N; i++) {
    //      for(int j=0; j<M; j++) {
    //          ans += map[i][j];
    //      }
    //      ans += '\n';
    //  }
    //  System.out.println(ans.trim());
    }
    
    static int w_DFS(int x, int y, Coordinate cs) {
        w_map[y][x] = 1;
        c_map[y][x] = cs;
        for(int i=0; i<4; i++) {
            int nx = x + wx[i];
            int ny = y + wy[i];
            if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                if(w_map[ny][nx] == 0) {
                    w_map[y][x] += w_DFS(nx, ny, cs);
                }
            }
        }
        return w_map[y][x];
    }
    
    static int find_route(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x + wx[i];
            int ny = y + wy[i];
            if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                if(c_map[ny][nx] != null) {
                    int sx = c_map[ny][nx].x;
                    int sy = c_map[ny][nx].y;
                    if(!visited[sy][sx]) {
                        visited[sy][sx] = true;
                        sc_arr.add(new Coordinate(sx,sy));
                        map[y][x] += w_map[sy][sx];
                    }
                }
            }
        }
        return map[y][x];
    }
    
    static void back_visited() {
        for(int i=0; i<sc_arr.size(); i++) {
            visited[sc_arr.get(i).y][sc_arr.get(i).x] = false;
        }
        sc_arr = new ArrayList<Coordinate>(); //초기화
    }
}