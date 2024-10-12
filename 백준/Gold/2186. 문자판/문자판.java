import java.io.*;
import java.util.*;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class UpdateInfo {
    Point p;
    int cnt;
    UpdateInfo(Point p, int cnt) {
        this.p = p;
        this.cnt = cnt; //경로 수
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N, M, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      char[][] map = new char[N][M];
      for(int i=0; i<N; i++) {
          String input = br.readLine();
          for(int j=0; j<M; j++) {
              map[i][j] = input.charAt(j);
          }
      }
      String str = br.readLine();
      ArrayList<Point>[] alpList = new ArrayList[str.length()];
      for(int i=str.length() - 1; i>=0; i--) {
          char c = str.charAt(i);
          alpList[i] = new ArrayList<>();
          for(int j=0; j<N; j++) {
              for(int k=0; k<M; k++) {
                  if(map[j][k] == c) {
                      alpList[i].add(new Point(k, j));
                  }
              }
          }
      }
      int[][] fl = new int[N][M]; //완성된 길이
      int[][] dp = new int[N][M];
      for(int i=0; i<alpList[str.length() - 1].size(); i++) {
          Point p = alpList[str.length() - 1].get(i);
          dp[p.y][p.x] = 1;
          fl[p.y][p.x] = 1;
      }
      
      for(int i=str.length() - 2; i>=0; i--) {
          char bc = str.charAt(i + 1); //before char
          int ev = str.length() - (i + 1);//기댓값
          ArrayList<UpdateInfo> uList = new ArrayList<>();
          for(int j=0; j<alpList[i].size(); j++) {
              Point cp = alpList[i].get(j);
              int cnt = 0;
              for(int d=0; d<4; d++) {
                  //4방향 K만큼 탐색
                  for(int m=1; m<=K; m++) {
                      int nx = cp.x + dx[d] * m;
                      int ny = cp.y + dy[d] * m;
                      if(!checkRange(nx, ny)) {
                          //범위를 벗어난다면 그 방향은 더이상 탐색 x
                          break;
                      }
                      //범위에 들어온다면
                      if(map[ny][nx] == bc && fl[ny][nx] == ev) {
                          //문자를 이을 수 있다면
                          cnt += dp[ny][nx];
                      }
                  }
              }
              if(cnt > 0) {
                  //경로가 하나라도 존재한다면 dp update
                  uList.add(new UpdateInfo(cp, cnt));
              }
          }
          for(int j=0; j<uList.size(); j++) {
              UpdateInfo ui = uList.get(j);
              dp[ui.p.y][ui.p.x] = ui.cnt;
              fl[ui.p.y][ui.p.x] = ev + 1;
          }
      }
      
      int answer = 0;
      for(int i=0; i<N; i++) {
          for(int j=0; j<M; j++) {
              if(map[i][j] == str.charAt(0) && fl[i][j] == str.length()) {
                  answer += dp[i][j];
              }
          }
      }
      System.out.println(answer);
  }
  
  static boolean checkRange(int x, int y) {
      if((0 <= x && x<= M - 1) && (0<= y && y<= N -1)) {
          return true;
      }
      return false;
  }
}