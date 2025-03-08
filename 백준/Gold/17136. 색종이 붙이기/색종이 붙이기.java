import java.io.*;
import java.util.*;

public class Main {
    static int[] cp = {5, 5, 5, 5, 5}; //0부터 1, 2, 3...
    static int answer = 30;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int[][] map = new int[10][10];
      for(int i=0; i<10; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<10; j++) {
              map[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      dfs(map, 0, 0, 0);
      if(answer == 30) {
          System.out.println(-1);
      } else {
          System.out.println(answer);
      }
  }
  
  static void dfs(int[][] map, int x, int y, int cnt) {
      if(cnt >= answer) {
          return;
      }
      
      if(y == 10) {
          answer = cnt;
          return;
      }
      
      int nx = x + 1;
      int ny = y;
      if(nx == 10) {
          nx = 0;
          ny += 1;
      }
      
      if(map[y][x] == 0) {
          dfs(map, nx, ny, cnt);
      } else {
          for(int i=0; i<5; i++) {
              if(cp[i] >= 1 && check(map, x, y, i + 1)) {
                  //색종이 붙이기가 가능하다면 영역이 전부 1임을 뜻함.
                  paste(map, x, y, i + 1);
                  cp[i] -= 1;
                  dfs(map, nx, ny, cnt + 1);
                  cp[i] += 1;
                  takeOff(map, x, y, i + 1);
              }
          }
      }
  }
  
  static boolean check(int[][] map, int x, int y, int type) {
      int maxY = y + type - 1;
      int maxX = x + type - 1;
      if(maxY >= 10 || maxX >= 10) {
          return false;
      }
      for(int i=y; i<=maxY; i++) {
          for(int j=x; j<=maxX; j++) {
              if(map[i][j] == 0) {
                  return false;
              }
          }
      }
      return true;
  }
  
  static void paste(int[][] map, int x, int y, int type) {
      int maxY = y + type - 1;
      int maxX = x + type - 1;
      
      for(int i=y; i<=maxY; i++) {
          for(int j=x; j<=maxX; j++) {
              map[i][j] = 0;
          }
      }
  }
  
  static void takeOff(int[][] map, int x, int y, int type) {
      int maxY = y + type - 1;
      int maxX = x + type - 1;
      
      for(int i=y; i<=maxY; i++) {
          for(int j=x; j<=maxX; j++) {
              map[i][j] = 1;
          }
      }
  }
}