import java.io.*;
import java.util.*;

class Coin {
    int cnt, v;
    Coin(int v, int cnt) {
        this.v = v;
        this.cnt = cnt;
    }
}

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      for(int t=1; t<=3; t++) {
          int N = Integer.parseInt(br.readLine());
          
          Coin[] coins = new Coin[N + 1];
          int sum = 0; //총 합.
          for(int i=1; i<=N; i++) {
              StringTokenizer st = new StringTokenizer(br.readLine());
              int v = Integer.parseInt(st.nextToken());
              int cnt = Integer.parseInt(st.nextToken());
              sum += (v * cnt);
              coins[i] = new Coin(v, cnt);
          }
          
          if(sum % 2 != 0) {
              //정확히 반으로 나누어지지 않는다면 불가능.
              sb.append(0).append("\n");
              continue;
          }
          //나누어진다면
          int target = sum / 2;
          
          int[][] dp = new int[N + 1][target + 1]; //각 종류의 사용된 개수를 저장할 것임.
          
          //-1로 초기화
          for(int i=0; i<=N; i++) {
              for(int j=1; j<=target; j++) {
                  dp[i][j] = -1;
              }
          }
          
          for(int i=1; i<=N; i++) {
              for(int j=1; j<=target; j++) {
                  if(dp[i - 1][j] != -1) {
                      //-1이 아니라면 0개로 가능.
                      dp[i][j] = 0;
                  } else {
                      //불가능하다면, 현재 동전으로 채울 수 있는지 확인.
                      if(j - coins[i].v >= 0) {
                          int useCnt = dp[i][j - coins[i].v]; //사용된 코인의 개수.
                          if(useCnt != -1) {
                              //존재한다면 가능성이 있음. -1이라면 불가능함.
                              if(useCnt + 1 <= coins[i].cnt) {
                                  //하나를 추가적으로 더 사용했을 때 가능하다면.
                                  dp[i][j] = useCnt + 1;
                              }
                          }
                      }
                  }
              }
          }
          
          if(dp[N][target] != -1) {
              sb.append(1).append("\n");
          } else {
              sb.append(0).append("\n");
          }
      }
      
      System.out.println(sb.toString().trim());
  }
}