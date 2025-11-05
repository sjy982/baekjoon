import java.io.*;
import java.util.*;
class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class Log {
    int x, y; //중싱점
    int type, cnt;
    Log(int x, int y, int type, int cnt) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.cnt = cnt;
    }
}

public class Main {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] digx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final int[] digy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static char[][] map;
    static boolean[][][] visited;
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      map = new char[N][N];
      visited = new boolean[N][N][2];
      for(int i=0; i<N; i++) {
          String line = br.readLine();
          for(int j=0; j<N; j++) {
             map[i][j] = line.charAt(j); 
          }
      }
      
      Log log = null;
      Log target = null;
      boolean logFind = false; 
      boolean targetFind = false;
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              if(logFind && map[i][j] == 'B') {
                  continue;
              }
              if(targetFind && map[i][j] == 'E') {
                  continue;
              }
              
              if(map[i][j] == 'B') {
                  logFind = true;
                  log = setLog(j, i, 'B');
              } else if(map[i][j] == 'E') {
                  targetFind = true;
                  target = setLog(j, i, 'E');
              }
              
              if(logFind && targetFind) {
                  break;
              }
          } 
      }
      System.out.println(bfs(log, target));
  }
  
  static int bfs(Log log, Log target) {
      int result = 0;
      Queue<Log> que = new LinkedList<>();
      que.add(log);
      visited[log.y][log.x][log.type] = true;
      
      while(!que.isEmpty()) {
          Log node = que.poll();
          if(checkEnd(node, target)) {
              result = node.cnt;
              break;
          }
          
          //방향 이동
          for(int i=0; i<4; i++) {
              int nx = node.x + dx[i]; //새로운 중심점
              int ny = node.y + dy[i];
              //이제 나머지 점들 구해서 범위, 통나무있는지 체크
              ArrayList<Point> list = findAllPosition(nx, ny, node.type);
              boolean isPosible = true;
              for(int j=0; j<3; j++) {
                  if(!checkRange(list.get(j).x, list.get(j).y)) {
                      //범위밖
                      isPosible = false;
                      break;
                  }
                  if(map[list.get(j).y][list.get(j).x] == '1') {
                      //통나무
                      isPosible = false;
                      break;
                  }
              }
              if(!isPosible) {
                  //이동이 불가능한 경우임.
                  continue;
              }
              
              //이번엔 방문체크
              if(visited[ny][nx][node.type]) {
                  continue;
              }
              
              //가능한 경우임
              visited[ny][nx][node.type] = true;
              que.add(new Log(nx, ny , node.type, node.cnt + 1));
          }
          //턴이 가능한지.
          if(checkTurn(node.x, node.y)) {
              //가능하다면
              int nextType = node.type == 0 ? 1 : 0;
              if(!visited[node.y][node.x][nextType]) {
                  visited[node.y][node.x][nextType] = true;
                  que.add(new Log(node.x, node.y, nextType, node.cnt + 1));
              }
          }
      }
      return result;
  }
  
  static boolean checkTurn(int x, int y) {
      for(int i=0; i<=7; i++) {
          int nx = x + digx[i];
          int ny = y + digy[i];
          if(!checkRange(nx, ny)) {
              return false;
          }
          if(map[ny][nx] == '1') {
              return false;
          }
      }
      return true;
  }
  
  static ArrayList<Point> findAllPosition(int x, int y, int type) {
      ArrayList<Point> result = new ArrayList<>();
      result.add(new Point(x, y));
      if(type == 0) {
          result.add(new Point(x - 1, y));
          result.add(new Point(x + 1, y));
      } else {
          result.add(new Point(x, y - 1));
          result.add(new Point(x, y + 1));
      }
      return result;
  }
  
  static boolean checkEnd(Log log, Log target) {
      if((log.x == target.x) && (log.y == target.y) && (log.type == target.type)) {
          return true;
      }
      return false;
  }
  
  static Log setLog(int x, int y, char c) {
      //오른쪽 체크
      if(checkRange(x + 1, y) && map[y][x + 1] == c) {
          //오른쪽으로 이어져 있다면.
          return new Log(x + 1, y, 0, 0);
      }
      //아래로 이어져 있음.
      return new Log(x, y + 1, 1, 0);
  }
  
  static boolean checkRange(int x, int y) {
      if((0 <= x && x <= N - 1) && (0 <= y && y <= N - 1)) {
          return true;
      }
      return false;
  }
}