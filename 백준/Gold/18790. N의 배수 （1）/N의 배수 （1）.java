import java.io.*;
import java.util.*;

class Link {
    int cur;
    Link beforeLink;
    Link(int cur, Link beforeLink) {
        this.cur = cur;
        this.beforeLink = beforeLink;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[2 * N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      int last = (2 * N - 1);
      for(int i=1; i<=last; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      Link[][] dp = new Link[N + 1][N + 1];
      
      dp[1][arr[1]] = new Link(arr[1], null);
      for(int i=2; i <= last; i++) {
          for(int j=N-1; j>=1; j--) {
              if(i <= j) {
                  continue;
              }
              
              if((last - i) + 1 + j < N) {
                  break;
              }
              
              for(int k=0; k<=N; k++) {
                  if(dp[j][k] != null) {
                      int nSum = (k + arr[i]) > N ? (k + arr[i]) - N : (k + arr[i]);
                      if(dp[j + 1][nSum] == null) {
                          dp[j + 1][nSum] = new Link(arr[i], dp[j][k]);
                      }
                  }
              }
          }
          dp[1][arr[i]] = new Link(arr[i], null);
      }
      if(dp[N][N] == null && dp[N][0] == null) {
          System.out.println(-1);
      } else {
          StringBuilder sb = new StringBuilder();
          if(dp[N][0] != null) {
              for(int i=1; i<=N; i++) {
                  sb.append(0).append(" ");
              }
          } else {
              Link link = dp[N][N];
              for(int i=1; i<=N; i++) {
                 sb.append(link.cur).append(" ");
                 link = link.beforeLink;
                }
          }
          System.out.println(sb.toString().trim());
      }
  }
}