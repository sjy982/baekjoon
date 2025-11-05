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
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    static int N;
    static boolean[][] visited;
    static boolean[][] map;
    static ArrayList<Point>[][] switchList;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      visited = new boolean[N + 1][N + 1];
      map = new boolean[N + 1][N + 1];
      map[1][1] = true;
      switchList = new ArrayList[N + 1][N + 1];
      int M = Integer.parseInt(st.nextToken());
      for(int i=0; i<M; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int y = Integer.parseInt(st2.nextToken());
          int x = Integer.parseInt(st2.nextToken());
          if(switchList[y][x] == null) {
              switchList[y][x] = new ArrayList<>();
          }
          int y2 = Integer.parseInt(st2.nextToken());
          int x2 = Integer.parseInt(st2.nextToken());
          switchList[y][x].add(new Point(x2, y2));
      }
      bfs();
      int answer = 0;
      for(int i=1; i<=N; i++) {
          for(int j=1; j<=N; j++) {
              if(map[i][j]) {
                  answer += 1;
              }
          }
      }
      System.out.println(answer);
  }
  
  static void bfs() {
      Queue<Point> que = new LinkedList<>();
      que.add(new Point(1, 1));
      visited[1][1] = true;
      
      while(!que.isEmpty()) {
          //새롭게 방문한 node임
          Point node = que.poll();
          //가능한 불을 전부 켜준다.
          if(switchList[node.y][node.x] == null) {
              switchList[node.y][node.x] = new ArrayList<>();
          }
          for(int i=0; i<switchList[node.y][node.x].size(); i++) {
              Point p = switchList[node.y][node.x].get(i);
              if(map[p.y][p.x]) {
                  continue;
              }
              //새롭게 불을 키는 경우라면.
              map[p.y][p.x] = true;
              //기존 경로와 연결할 수 있는지 봐야됨.
              for(int j=0; j<4; j++) {
                  int nx = p.x + dx[j];
                  int ny = p.y + dy[j];
                  if(!checkRange(nx, ny)) {
                      continue;
                  }
                  if(!visited[ny][nx]) {
                      continue;
                  }
                  //기존 경로와 연결된 경우
                  visited[p.y][p.x] = true; //방문처리도 해준다.
                  que.add(p);
                  break;
              }
          }
          
          //현재 위치에서도 상하좌우로 새롭게 움직일 수 있는 경우를 찾는다.
          for(int i=0; i<4; i++) {
              int nx = node.x + dx[i];
              int ny = node.y + dy[i];
              if(!checkRange(nx, ny)) {
                  continue;
              }
              if(visited[ny][nx]) {
                  //방문했다면 pass
                  continue;
              }
              if(!map[ny][nx]) {
                  //불이 켜져있지 않다면 pass
                  continue;
              }
              //방문하지도 않았고, 불도 켜져있다면. 방문.
              visited[ny][nx] = true;
              que.add(new Point(nx, ny));
          }
      }
  }
  
  static boolean checkRange(int x, int y) {
      if((1 <= x && x <= N) && (1 <= y && y <= N)) {
          return true;
      }
      return false;
  }
}