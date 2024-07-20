import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int dp[][] = new int[n + 1][m + 1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }
        
        int male[] = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            male[i] = Integer.parseInt(st2.nextToken());
        }
        
        int female[] = new int[m];
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            female[i] = Integer.parseInt(st3.nextToken());
        }
        
        Arrays.sort(male);
        Arrays.sort(female);
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(i == j) {
                    dp[i][j] = Math.abs(male[i - 1] - female[j - 1]) + dp[i - 1][j - 1];
                } else if(i > j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]));
                } else {
                    //i < j
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]));
                }
            }
        }
        
        System.out.println(dp[n][m]);
    }
}