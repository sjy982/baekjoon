import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][N + 1]; // [A][B] 왼쪽부터 A까지, 오른쪽부터 B까지의 최댓값
        int answer = 0;
        for (int k = 1; k <= N; k++) { // k번
            for (int i = 0; i <= k; i++) { // 왼쪽은 i번, 오른쪽은 k - i번
                int r = k - i;
                
                if (i - 1 >= 0) { // 왼쪽에서 arr 요소를 추가하는 경우
                    dp[i][r] = dp[i - 1][r] + arr[i - 1] * k;
                }
                
                if (r - 1 >= 0) { // 오른쪽에서 arr 요소를 추가하는 경우
                    dp[i][r] = Math.max(dp[i][r], dp[i][r - 1] + arr[N - r] * k);
                }
                
                answer = Math.max(answer, dp[i][r]);
            }
        }
        System.out.println(answer);
    }
}