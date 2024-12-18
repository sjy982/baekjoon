import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long answer = 0;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[N + 1];
      HashMap<Integer, Long>[] dp = new HashMap[N + 1];
      for(int i=1; i<=N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
          dp[i] = new HashMap<>();
          
      }
      for(int i=2; i<=N; i++) {
          for(int j=i - 1; j>=1; j--) {
              int diff = arr[i] - arr[j];
              if(dp[j].get(diff) == null) {
                  dp[i].put(diff, (long) arr[j] + arr[i]);
              } else {
                  long v = dp[j].get(diff) + arr[i];
                  dp[i].put(diff, v);
                  answer = Math.max(answer, v);
              }
          }
      }
      System.out.println(answer);
  }
}