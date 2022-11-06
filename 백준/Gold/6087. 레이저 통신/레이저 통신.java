import java.io.*;
import java.util.*;

class Node {
    int x,y,cm,dir;
    //dir => 초기 0, x축 1, y축 2
    Node(int x, int y, int cm, int dir) {
        this.x = x;
        this.y = y;
        this.cm = cm;
        this.dir = dir;
    }
}

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class MapInfo {
    int v;
    boolean col,row;
    MapInfo(int v, boolean col, boolean row) {
        this.v = v;
        this.col = col;
        this.row = row;
    }
}

public class Main {
    static final int dx[] = {0,0,-1,1}; // ind 0,1 => y축 움직임, ind 1,2 => x축 움직임
    static final int dy[] = {-1,1,0,0};
    static final int INFINITY = Integer.MAX_VALUE;
    static MapInfo map[][];
    static Coordinate end;
    static Node start;
    static int W,H;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      map = new MapInfo[H][W];
      for(int i=0; i<H; i++) {
          String s = br.readLine();
          for(int j=0; j<W; j++) {
              if(s.charAt(j) == '*') {
                  map[i][j] = new MapInfo(-2,false,false); //벽은 -2로 표현
              } else if(s.charAt(j) == 'C') {
                  if(start == null) {
                      map[i][j] = new MapInfo(-2,false,false);
                      start = new Node(j,i,0,0);
                  } else {
                      map[i][j] = new MapInfo(INFINITY,false,false);
                      end = new Coordinate(j,i);
                  }
              } else {
                  map[i][j] = new MapInfo(INFINITY,false,false);
              }
          }
      }
      BFS();
    //   String ans = new String("");
    //   for(int i=0; i<H; i++) {
    //       for(int j=0; j<W; j++) {
    //           ans += Integer.toString(map[i][j].v);
    //       }
    //       ans += '\n';
    //   }
    //   System.out.println(ans);
    System.out.println(map[end.y][end.x].v);
    }
    
    static void BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        while(!que.isEmpty()) {
            Node n = que.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=W-1) && (ny>=0 && ny<=H-1)) {
                    if(map[ny][nx].v != -2) {
                        //벽이 아니라면
                        if(i==0 || i==1) {
                            //y축 움직임
                            if(n.dir == 1) {
                                if(map[ny][nx].v > n.cm+1) {
                                    map[ny][nx].v = n.cm+1;
                                    map[ny][nx].col = true;
                                    que.add(new Node(nx,ny,n.cm+1,2));
                                } else if(map[ny][nx].v == n.cm+1 && !map[ny][nx].col) {
                                    map[ny][nx].col = true;
                                    que.add(new Node(nx,ny,n.cm+1,2));
                                }
                            } else {
                                if(map[ny][nx].v > n.cm) {
                                    map[ny][nx].v = n.cm;
                                    map[ny][nx].col = true;
                                    que.add(new Node(nx,ny,n.cm,2));
                                } else if(map[ny][nx].v == n.cm && !map[ny][nx].col) {
                                    map[ny][nx].col = true;
                                    que.add(new Node(nx,ny,n.cm,2));
                                }
                            } 
                        } else {
                            //x축 움직임
                            if(n.dir == 2) {
                                if(map[ny][nx].v > n.cm+1) {
                                    map[ny][nx].v = n.cm+1;
                                    map[ny][nx].row = true;
                                    que.add(new Node(nx,ny,n.cm+1,1));
                                } else if(map[ny][nx].v == n.cm+1 && !map[ny][nx].row) {
                                    map[ny][nx].row = true;
                                    que.add(new Node(nx,ny,n.cm+1,1));
                                }
                                
                            } else {
                                if(map[ny][nx].v > n.cm) {
                                    map[ny][nx].v = n.cm;
                                    map[ny][nx].row = true;
                                    que.add(new Node(nx,ny,n.cm,1));
                                } else if(map[ny][nx].v == n.cm && !map[ny][nx].row) {
                                    map[ny][nx].row = true;
                                    que.add(new Node(nx,ny,n.cm,1));
                                }
                            } 
                        }
                    }
                }
            }
        }
    }
}