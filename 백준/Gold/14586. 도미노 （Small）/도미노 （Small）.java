import java.io.*;
import java.util.*;

class Dominoes implements Comparable<Dominoes> {
    long x, h;
    
    public Dominoes(long x, long h) {
        this.x = x;
        this.h = h;
    }

    @Override
    public int compareTo(Dominoes other) {
        return Long.compare(this.x, other.x);
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Dominoes[] dominoes = new Dominoes[N + 1];
      
      for(int i=1; i<=N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          dominoes[i] = new Dominoes(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
      }
      
      Arrays.sort(dominoes, 1, N + 1);
      
      int[] dp = new int[N + 1];
      for(int i=1; i<=N; i++) {
          dp[i] = Integer.MAX_VALUE;
      }
      dp[0] = 0;
      
      for(int i=1; i<=N; i++) {
          //오른쪽으로
          int maxNum = passedMaxDominones(true, i, dominoes);
          dp[maxNum] = Math.min(dp[maxNum], dp[i - 1] + 1);
      
          //왼쪽으로
          int minNum = passedMaxDominones(false, i, dominoes);
          if(minNum == 1) {
              //다 넘어갔다면
              dp[i] = 1;
          } else {
              //다 넘어가지 않았다면
              for(int j=minNum - 1; j<=i - 1; j++) {
                  dp[i] = Math.min(dp[i], dp[j] + 1);
              }
          }
      }
      
      System.out.println(dp[N]);
  }
  
  static int passedMaxDominones(boolean right, int std, Dominoes[] dominoes) {
      int result = std;
      if(right) {
          long max = dominoes[std].x + dominoes[std].h;
          for(int i=std + 1; i<=N; i++) {
              if(max < dominoes[i].x) {
                  break;
              }
              result = i;
              max = Math.max(max, dominoes[i].x + dominoes[i].h);
          }
      } else {
          long min = dominoes[std].x - dominoes[std].h;
          for(int i=std - 1; i>=1; i--) {
              if(dominoes[i].x < min) {
                  break;
              }
              result = i;
              min = Math.min(min, dominoes[i].x - dominoes[i].h);
          }
      }
      return result;
  }
}