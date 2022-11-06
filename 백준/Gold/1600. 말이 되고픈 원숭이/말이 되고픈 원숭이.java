import java.io.*;
import java.util.*;

class Node {
    int x,y,h,c;
    Node(int x, int y, int h, int c) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.c = c;
    }
}

public class Main {
    static final int h_dx[] = {1,2,-1,-2,2,1,-2,-1};
    static final int h_dy[] = {2,1,2,1,-1,-2,-1,-2};
    static final int dx[] = {0,1,0,-1};
    static final int dy[] = {1,0,-1,0};
    static int map[][];
    static int K,W,H;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      K = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      map = new int[H][W];
      for(int i=0; i<H; i++) {
          StringTokenizer sti = new StringTokenizer(br.readLine());
          for(int j=0; j<W; j++) {
              map[i][j] = Integer.parseInt(sti.nextToken());
              if(map[i][j] == 0) {
                  map[i][j] = K+1;
              } else {
                  map[i][j] = -1;
              }
          }
      }
      System.out.println(BFS());
    }
    
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,0,0,0));
        map[0][0] = -1;
        while(!que.isEmpty()) {
            Node n = que.poll();
            if(n.x == W-1 && n.y == H-1) {
                return n.c;
            }
            for(int i=0; i<dx.length; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=W-1) && (ny>=0 && ny<=H-1)) {
                    if(map[ny][nx] > n.h && map[ny][nx] != -1) {
                        map[ny][nx] = n.h;
                        que.add(new Node(nx,ny,n.h,n.c+1));
                    }
                }
            }
            if(K>n.h) {
                for(int i=0; i<h_dx.length; i++) {
                    int nx = n.x + h_dx[i];
                    int ny = n.y + h_dy[i];
                    if((nx>=0 && nx<=W-1) && (ny>=0 && ny<=H-1)) {
                        if(map[ny][nx] > n.h+1 && map[ny][nx] != -1) {
                            map[ny][nx] = n.h+1;
                            que.add(new Node(nx,ny,n.h+1,n.c+1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}