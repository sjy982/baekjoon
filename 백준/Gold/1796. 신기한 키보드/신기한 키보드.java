import java.io.*;
import java.util.*;

class Info {
    int ind, v;
    Info(int ind, int v) {
        this.ind = ind;
        this.v = v;
    }
}

public class Main {
    static final int MAX = 100000000;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String S = br.readLine();
      
      boolean[] visited = new boolean[26];
      ArrayList<Character> list = new ArrayList<>();
      for(int i=0; i<S.length(); i++) {
          int ind = (int) S.charAt(i) - 97;
          if(!visited[ind]) {
              visited[ind] = true;
              list.add(S.charAt(i));
          }
      }
      
      Collections.sort(list);
      int[][] cLocation = new int[list.size() + 1][2]; //0은 왼쪽, 1은 오른쪽 인덱스
      for(int i=0; i<list.size(); i++) {
          int cursor = -1;
          for(int j=0; j<S.length(); j++) {
              if(S.charAt(j) == list.get(i)) {
                  if(cursor == -1) {
                      cLocation[i + 1][0] = j;
                  }
                  cursor = j;
              }
          }
          cLocation[i + 1][1] = cursor;
      }
      
      Info[][] dp = new Info[list.size() + 1][2]; //0은 왼쪽 먼저 이동, 1은 오른쪽 먼저 이동
      for(int i=1; i<=list.size(); i++) {
          for(int j=0; j<2; j++)  {
              dp[i][j] = new Info(-1, MAX);
          }
      }
      
      dp[1][1] = new Info(cLocation[1][1], cLocation[1][1]);
      for(int i=2; i<=list.size(); i++) {
          for(int j=0; j<2; j++) {
              if(dp[i - 1][j].ind != -1) {
                  //경우의 수가 존재함
                  if(cLocation[i][0] < dp[i - 1][j].ind && dp[i - 1][j].ind < cLocation[i][1]) {
                      //현재 커서에서 이동할 수 있는 방향이 왼, 오라면
                      int fLeft = (dp[i - 1][j].ind - cLocation[i][0]) + (cLocation[i][1] - cLocation[i][0]);
                      int fRight = (cLocation[i][1] - dp[i - 1][j].ind) + (cLocation[i][1] - cLocation[i][0]);
                      if(dp[i][1].v > fLeft + dp[i - 1][j].v) {
                          dp[i][1] = new Info(cLocation[i][1], fLeft + dp[i - 1][j].v);
                      }
                      if(dp[i][0].v > fRight + dp[i - 1][j].v) {
                          dp[i][0] = new Info(cLocation[i][0], fRight + dp[i - 1][j].v);
                      }
                  } else if(cLocation[i][0] < dp[i - 1][j].ind) {
                      //왼쪽만
                      int v = dp[i - 1][j].ind - cLocation[i][0] + dp[i - 1][j].v;
                      if(dp[i][0].v > v) {
                          dp[i][0] = new Info(cLocation[i][0], v);
                      }
                  } else {
                      //오른쪽만
                      int v = cLocation[i][1] - dp[i - 1][j].ind + dp[i - 1][j].v;
                      if(dp[i][1].v > v) {
                          dp[i][1] = new Info(cLocation[i][1], v);
                      }
                  }
              }
          }
      }
      System.out.println(Math.min(dp[list.size()][0].v + S.length(), dp[list.size()][1].v + S.length()));
  }
}