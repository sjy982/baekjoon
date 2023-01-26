import java.io.*;
import java.util.*;

public class Main {
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int board[][] = new int[5][5];
    static ArrayList<String> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      for(int i=0; i<5; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<5; j++) {
              board[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      for(int i=0; i<5; i++) {
          for(int j=0; j<5; j++) {
              StringBuilder sb = new StringBuilder();
              find_num(j, i, sb);
          }
      }
      System.out.println(ans.size());
    }
    
    static void find_num(int x, int y, StringBuilder n) {
        if(n.length() == 6) {
            if(!ans.contains(n.toString())) {
                ans.add(n.toString());
            }
            return;
        }
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if((0<=nx && nx<=4) && (0<=ny && ny<=4)) {
                StringBuilder n_n = new StringBuilder(n.toString());
                n_n.append(String.valueOf(board[ny][nx]));
                find_num(nx, ny, n_n);
            }
        }
    }
}