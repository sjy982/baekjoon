import java.io.*;
import java.util.*;

class Pipe {
    int x,y;
    int s; //0은 가로, 1은 세로, 2는 대각선
    Pipe(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}

public class Main {
    static int N;
    static int board[][];
    static int ans = 0;
    static final int NOC[][] = {{0,2}, {1,2}, {0,1,2}};
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<N; j++) {
              board[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      DFS(new Pipe(1,0,0));
      System.out.println(ans);
    }
    static void DFS(Pipe p) {
        if(p.x == N-1 && p.y == N-1) {
            ans += 1;
            return;
        }
        for(int i=0; i<NOC[p.s].length; i++) {
            Pipe n_p = new Pipe(p.x, p.y, p.s);
            if(NOC[p.s][i] == 0) {
                if(move_w(n_p)) {
                    DFS(n_p);
                }
            } else if(NOC[p.s][i] == 1) {
                if(move_l(n_p)) {
                    DFS(n_p);
                }
            } else if(NOC[p.s][i] == 2) {
                if(move_d(n_p)) {
                    DFS(n_p);
                }
            }
        }
    }
    
    static boolean move_w(Pipe p) {
        //가로 이동
        p.x += 1;
        p.s = 0;
        if(p.x>=0 && p.x<=N-1) {
            if(board[p.y][p.x] != 1) {
                return true;
            }
        }
        return false;
    }
    
    static boolean move_l(Pipe p) {
        //세로 이동
        p.y += 1;
        p.s = 1;
        if(p.y>=0 && p.y<=N-1) {
            if(board[p.y][p.x] != 1) {
                return true;
            }
        }
        return false;
    }
    
    static boolean move_d(Pipe p) {
        //대각선 이동
        p.x += 1;
        p.y += 1;
        p.s = 2;
        if((p.x>=0 && p.x<=N-1) && (p.y>=0 && p.y<=N-1)) {
            if(board[p.y][p.x] != 1 && board[p.y-1][p.x] != 1 && board[p.y][p.x-1] != 1) {
                return true;
            }
        }
        return false;
    }
}