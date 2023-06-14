import java.util.*;
class Solution {
    static int[][] dp;
    public int solution(int n, int[][] results) {
        int answer = 0;
        dp = new int[n+1][n+1];
        for(int i=0; i < results.length; i++) {
            dp[results[i][0]][results[i][1]] = 1; //이김
            dp[results[i][1]][results[i][0]] = -1; //짐
        }
        //폴로이드 와샬
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    if(dp[i][j] == 1 && dp[j][k] == 1) {
                        //ex) 1 > 2 > 5, 순서대로 i, j, k
                        dp[i][k] = 1;
                        dp[k][i] = -1;
                    } else if(dp[i][j] == -1 && dp[j][k] == -1) {
                        //ex) 1 < 2 < 5, 순서대로 i, j, k
                        dp[i][k] = -1;
                        dp[k][i] = 1;
                    }
                }
            }
        }
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) if(dp[i][j] != 0) cnt += 1;
            //모든 노드와의 승패를 알 수 있다면
            if(cnt == n-1) answer += 1;
        }
        return answer;
    }
}