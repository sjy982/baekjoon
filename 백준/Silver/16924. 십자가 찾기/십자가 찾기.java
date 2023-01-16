import java.io.*;
import java.util.*;

class Cross {
    int x,y,s;
    Cross(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M;
    static char cross_board[][];
    static ArrayList<Cross> cross = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();
    static boolean make = true;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      cross_board = new char[N][M];
      for(int i=0; i<N; i++) {
          String str = br.readLine();
          for(int j=0; j<M; j++) {
              cross_board[i][j] = str.charAt(j);
          }
      }
      for(int i=0; i<N; i++) {
          for(int j=0; j<M; j++) {
              if(cross_board[i][j] != '.') check_cross(j, i);
          }
      }
      if(cross.size() == 0 || cross.size() > N*M) {
          make = false;
      } else {
          for(int i=0; i<N; i++) {
              for(int j=0; j<M; j++) {
                  if(cross_board[i][j]=='*') {
                      make = false;
                      break;
                  }
              }
              if(!make) break;
          }
      }
      if(!make) System.out.println(-1);
      else {
          ans.append(String.valueOf(cross.size()) + "\n");
          for(int i=0; i<cross.size(); i++) {
              ans.append(String.valueOf(cross.get(i).y) + " " + String.valueOf(cross.get(i).x) + " " + String.valueOf(cross.get(i).s) + "\n");
          }
          System.out.println(ans.toString().trim());
      }
    }
    static void check_cross(int x, int y) {
        int size = 0;
        int cross_cout = 0;
        Coordinate left = new Coordinate(x-1,y);
        Coordinate right = new Coordinate(x+1,y);
        Coordinate top = new Coordinate(x,y-1);
        Coordinate bottom = new Coordinate(x,y+1);
        while(true) {
            boolean need_cross = false;
            if(0<=left.x && left.x<=M-1) {
                if(cross_board[left.y][left.x] == '*') need_cross = true;
                if(cross_board[left.y][left.x] == '.') break;
            } else break;
            
            if(0<=right.x && right.x<=M-1) {
                if(cross_board[right.y][right.x] == '*') need_cross = true;
                if(cross_board[right.y][right.x] == '.') break;
            } else break;
            
            if(0<=top.y && top.y<=N-1) {
                if(cross_board[top.y][top.x] == '*') need_cross = true;
                if(cross_board[top.y][top.x] == '.') break;
            } else break;
            
            if(0<=bottom.y && bottom.y<=N-1) {
                if(cross_board[bottom.y][bottom.x] == '*') need_cross = true;
                if(cross_board[bottom.y][bottom.x] == '.') break;
            } else break;
            size += 1;
            cross_board[left.y][left.x] = 'o';
            cross_board[right.y][right.x] = 'o';
            cross_board[top.y][top.x] = 'o';
            cross_board[bottom.y][bottom.x] = 'o';
            left = new Coordinate(left.x-1, y);
            right = new Coordinate(right.x+1, y);
            top = new Coordinate(x, top.y-1);
            bottom = new Coordinate(x, bottom.y+1);
            if(need_cross) {
                cross.add(new Cross(x+1,y+1, size));
                cross_cout += 1;
            }
        }
        if(size>=1) cross_board[y][x] = 'o';
        if(cross_cout == 0 && size >=1 && cross_board[y][x] == '*') {
            cross_board[y][x] = 'o';
            cross.add(new Cross(x+1,y+1,1));
        }
        return;
    }
}