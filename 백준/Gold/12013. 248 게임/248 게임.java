import java.io.*;
import java.io.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    int[] arr = new int[N + 1];
    for(int i=1; i<=N; i++) {
        arr[i] = Integer.parseInt(br.readLine());
    }
    
    int[][] dp = new int[N + 1][N + 1];
    dp[1][1] = arr[1];
    for(int i=2; i<=N; i++) {
        int curNum = arr[i];
        int A = i - 1;
        dp[i][i] = curNum;
        for(int j=i - 1; j>=1; j--) {
            if(dp[A][j] == curNum) {
                curNum += 1;
                A = j - 1;
                dp[i][j] = curNum;
            }
        }
    }
    int answer = 0;
    for(int i=1; i<=N; i++) {
        for(int j=1; j<=N; j++) {
            answer = Math.max(answer, dp[i][j]);
        }
    }
    System.out.println(answer);
  }
}