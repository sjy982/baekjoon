import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      
      int[] beads = new int[6]; 
      for(int i=1; i<=N; i++) {
          beads[i] = Integer.parseInt(br.readLine());
      }
      
      long[][][][][][][] dp = new long[6][6][11][11][11][11][11];
      
      ArrayList<Integer> list = new ArrayList<>();
      System.out.println(answer(list, beads, dp));
  }
  
  static long answer(ArrayList<Integer> curList, int[] beads, long[][][][][][][] dp) {
      for(int i=1; i<=5; i++) {
          if(beads[i] == -1) {
              return 0;
          }
      }
      
      //현재 구슬 위치부터 -2까지 서로 다른 종류인지 체크 (3이면 1까지 3 2 1)
      boolean[] visited = new boolean[6];
      for(int i=curList.size() - 1; i>=(curList.size() - 1) - 2; i--) {
          if(i >= 0) {
              int ind = curList.get(i);
              if(visited[ind]) {
                  return 0;
              }
              visited[ind] = true;
          }
      }
      
      int lastInd = curList.size() - 1;
      int bI1 = lastInd >= 0 ? curList.get(lastInd) : 0;
      int bI2 = lastInd - 1 >= 0 ? curList.get(lastInd - 1) : 0;
      if(dp[bI2][bI1][beads[1]][beads[2]][beads[3]][beads[4]][beads[5]] == -1) {
          //-1이면 경우의 수가 없음을 의미
          return 0;
      }
      
      if(dp[bI2][bI1][beads[1]][beads[2]][beads[3]][beads[4]][beads[5]] > 0) {
          //0보다 크면 방문했고, 구해놓은 경우의 수를 반환
          return dp[bI2][bI1][beads[1]][beads[2]][beads[3]][beads[4]][beads[5]];
      }
      
      //구슬을 전부 사용했고, 앞의 불가능한 경우의 해당하지 않는 경우
      boolean isPosible = true;
      for(int i=1; i<=5; i++) {
          if(beads[i] > 0) {
              isPosible = false;
          }
      }
      if(isPosible) {
          return 1;
      }
      
      //탐색
      long result = 0;
      
      for(int i=1; i<=5; i++) {
          beads[i] -= 1;
          curList.add(i);
          result += answer(curList, beads, dp);
          beads[i] += 1;
          curList.remove(curList.size() - 1);
      }
      
      if(result == 0) {
          dp[bI2][bI1][beads[1]][beads[2]][beads[3]][beads[4]][beads[5]] = -1;
      } else {
          dp[bI2][bI1][beads[1]][beads[2]][beads[3]][beads[4]][beads[5]] = result;
      }
      
      return result;
  }
}