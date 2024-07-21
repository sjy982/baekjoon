import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            dp[num] += dp[num - 1] + 1;
        }
        
        int maxLen = -1;
        for(int i=1; i<=N; i++) {
            if(dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }
        
        System.out.println(N - maxLen);
    }
}