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

public class Main {
    static final int wx[] = {0,0,-1,1};
    static final int wy[] = {-1,1,0,0};
    static int map[][];
    static boolean visited[][][];
    static int N, M;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][M];
      visited = new boolean[N][M][2];
      for(int i=0; i<N; i++) {
          String s = br.readLine();
          for(int j=0; j<M; j++) {
              map[i][j] = s.charAt(j) - '0';
          }
        //   System.out.println(Arrays.toString(map[i]));
      }
      System.out.println(BFS());
    }
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,0,0,1));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        
        while(!que.isEmpty()) {
            Node v = que.poll();
            if(v.x == M-1 && v.y == N-1) {
                return v.c;
            }
            for(int i=0; i<4; i++) {
                int nx = v.x + wx[i];
                int ny = v.y + wy[i];
                if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                    if(map[ny][nx] == 0) {
                        if(!visited[ny][nx][v.z]) {
                            visited[ny][nx][v.z] = true;
                            que.add(new Node(nx,ny,v.z,v.c+1));
                        }
                    } else {
                        if(v.z==0) {
                            if(!visited[ny][nx][1]) {
                                visited[ny][nx][1] = true;
                                que.add(new Node(nx,ny,1,v.c+1));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}