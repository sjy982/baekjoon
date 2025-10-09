import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int targetAlp  = alp; //알고력
        int targetCop = cop; //코딩력
        for(int i=0; i<problems.length; i++) {
            targetAlp = Math.max(targetAlp, problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
        }
        
        int[][] dp = new int[targetAlp + 1][targetCop + 1];
        for(int i=alp; i<=targetAlp; i++) {
            for(int j=cop; j<=targetCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;
        for(int i=alp; i<=targetAlp; i++) {
            for(int j=cop; j<=targetCop; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if(i == targetAlp && j == targetCop) {
                    continue;
                }
                
                //알고력 높이기.
                int nextAlg = i + 1 >= targetAlp ? targetAlp : i + 1;
                dp[nextAlg][j] = Math.min(dp[nextAlg][j], dp[i][j] + 1);
                
                //코딩력 높이기
                int nextCop = j + 1 >= targetCop ? targetCop : j + 1;
                dp[i][nextCop] = Math.min(dp[i][nextCop], dp[i][j] + 1);
                
                solve(i, j, targetAlp, targetCop, dp, problems);
            }
        }
        
        return dp[targetAlp][targetCop];
    }
    
    static void solve(int alp, int cop, int maxAlp, int maxCop, int[][] dp, int[][] problems) {
        for(int i=0; i<problems.length; i++) {
            if(problems[i][0] <= alp && problems[i][1] <= cop) {
                //풀 수 있다면
                int nextAlp = alp + problems[i][2] >= maxAlp ? maxAlp : alp + problems[i][2];
                int nextCop = cop + problems[i][3] >= maxCop ? maxCop : cop + problems[i][3];
                
                dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[alp][cop] + problems[i][4]);
            }
        }
    }
}