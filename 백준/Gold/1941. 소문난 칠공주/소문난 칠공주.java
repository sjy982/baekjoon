import java.io.*;
import java.util.*;


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
    static int answer = 0;
    static ArrayList<Point> result = new ArrayList<>();
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      char[][] map = new char[5][5];
      
      for(int i=0; i<5; i++) {
          String input = br.readLine();
          for(int j=0; j<5; j++) {
              map[i][j] = input.charAt(j);
          }
      }
      dfs(0, map);
      System.out.println(answer);
  }
  
  static void dfs(int ind, char[][] map) {
      if(result.size() == 7) {
          int cnt = 0;
          //인접한지 테스트
          if(!bfs(map)) {
              return;
          }
          for(int i=0; i<result.size(); i++) {
              Point p = result.get(i);
              if(map[p.y][p.x] == 'S') {
                  cnt += 1;
              }
          }
          if(cnt >= 4) {
              answer += 1;
          }
          return;
      }
      
      for(int i=ind; i<=24; i++) {
          result.add(new Point(i%5, i/5));
          dfs(i + 1, map);
          result.remove(result.size() - 1);
      }
  }
  
  static boolean bfs(char[][] map) {
     Queue<Point> que = new LinkedList<>();
     boolean[][] visited = new boolean[5][5];
     boolean[][] contains = new boolean[5][5];
     for(int i=0; i<result.size(); i++) {
         Point p = result.get(i);
         contains[p.y][p.x] = true;
     }
     visited[result.get(0).y][result.get(0).x] = true;
     que.add(result.get(0));
     
     int cnt = 1;
     while(!que.isEmpty()) {
         if(cnt == 7) {
             return true;
         }
         Point p = que.poll();
         for(int i=0; i<4; i++) {
             int nx = p.x + dx[i];
             int ny = p.y + dy[i];
             if((0 <= nx && nx <=4) && (0<= ny && ny <= 4)) {
                 if(!visited[ny][nx] && contains[ny][nx]) {
                     visited[ny][nx] = true;
                     cnt += 1;
                     que.add(new Point(nx, ny));
                 }
             }
         }
     }
     return false;
  }
}