import java.io.*;
import java.util.*;

class Node{
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
    static int visited_min[][];
    static int N, M, K;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      map = new int[N][M];
      visited_min = new int[N][M];
      for(int i=0; i<N; i++) {
          String s = br.readLine();
          Arrays.fill(visited_min[i], K+1);
          for(int j=0; j<M; j++) {
              map[i][j] = s.charAt(j) - '0';
          }
      }
      System.out.println(BFS());
    }
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,0,0,1));
        while(!que.isEmpty()) {
            Node v = que.poll();
            if(v.x == M-1 && v.y == N-1) {
                return v.c;
            }
            for(int i = 0; i < 4; i++) {
                int nx = v.x + wx[i];
                int ny = v.y + wy[i];
                if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                    if(map[ny][nx] == 0) {
                        if(visited_min[ny][nx] > v.z) {
                            visited_min[ny][nx] = v.z;
                            que.add(new Node(nx, ny, v.z, v.c+1));
                        } 
                    } else {
                        //벽을 만났을때
                        if(visited_min[ny][nx] > v.z+1) {
                            visited_min[ny][nx] = v.z+1;
                            que.add(new Node(nx, ny, v.z+1, v.c+1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}