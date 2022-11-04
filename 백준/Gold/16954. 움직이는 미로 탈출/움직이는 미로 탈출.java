import java.io.*;
import java.util.*;

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int dx[] = {0, 0, 0, -1, 1, -1, -1, 1, 1}; //제자리, 위, 아래, 왼, 오, 대각선 왼쪽 위, 대각선 왼쪽 아래, 대각선 오른쪽 위, 대각선 오른쪽 아래
    static final int dy[] = {0, -1, 1, 0, 0, -1, 1, -1, 1};
    static char board[][] = new char[8][8];
    static boolean visited[][] = new boolean[8][8];
    static ArrayList<Coordinate> wall_coor = new ArrayList<Coordinate>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      for(int i=0; i<8; i++) {
          String s = br.readLine();
          for(int j=0; j<8; j++) {
              board[i][j] = s.charAt(j);
              if(board[i][j] == '#') {
                  wall_coor.add(new Coordinate(j, i));
              }
          }
      }
      System.out.println(BFS());
    }
    
    static int BFS() {
        Queue<Coordinate> que = new LinkedList<>();
        que.add(new Coordinate(0,7));
        while(!que.isEmpty()) {
            int sz = que.size();
            visited = new boolean[8][8];
            for(int j=0; j<sz; j++) {
                Coordinate v = que.poll();
                if((wall_coor.size() == 0) || ((v.x == 7) && (v.y == 0))) {
                    return 1;
                }
                if(board[v.y][v.x] == '#') {
                    continue;
                }
                for(int i=0; i<dx.length; i++) {
                    int nx = v.x + dx[i];
                    int ny = v.y + dy[i];
                    if((nx>=0 && nx<=7) && (ny>=0 && ny<=7)) {
                        if(board[ny][nx] != '#' && !visited[ny][nx]) {
                            visited[ny][nx] = true;
                            que.add(new Coordinate(nx, ny));
                        }
                    }
                }
            }
            move_wall();
        }
        return 0;
    }
    
    static void move_wall() {
        if(wall_coor.size() != 0) {
            ArrayList<Coordinate> nWall_coor = new ArrayList<Coordinate>();
            for(int i=0; i<wall_coor.size(); i++) {
                board[wall_coor.get(i).y][wall_coor.get(i).x] = '.';
            }
            for(int j=0; j<wall_coor.size(); j++) {
                int ny = wall_coor.get(j).y + 1;
                if(ny <= 7) {
                    board[ny][wall_coor.get(j).x] = '#';
                    nWall_coor.add(new Coordinate(wall_coor.get(j).x, ny));
                }
            }
            wall_coor = nWall_coor;
        }
    }
}