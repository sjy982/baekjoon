
import java.util.*;
class Solution {
    static int[][] dp = new int[181][181];
    public int solution(int alp, int cop, int[][] problems) {
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        //max alp, max cop 찾기
        int max_alp = -1;
        int max_cop = -1;
        for (int i = 0; i < problems.length; i++) {
            max_alp = Math.max(max_alp, problems[i][0]);
            max_cop = Math.max(max_cop, problems[i][1]);
        }
        if ((max_alp <= alp && max_cop <= cop)) return 0;

        if (max_alp < alp) alp = max_alp;
        if (max_cop < cop) cop = max_cop;
        dp[alp][cop] = 0;
        for (int i = alp; i <= max_alp; i++) {
            for (int j = cop; j <= max_cop; j++) {
                if (i == max_alp && j == max_cop) break;
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1); //알고력 높이기
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1); //코딩력 높이기
                //problem sol
                for (int k = 0; k < problems.length; k++) {
                    if (i >= problems[k][0] && j >= problems[k][1]) {
                        if (i + problems[k][2] > max_alp && j + problems[k][3] > max_cop) {
                            dp[max_alp][max_cop] = Math.min(dp[max_alp][max_cop], dp[i][j] + problems[k][4]);
                        } else if (i + problems[k][2] > max_alp) {
                            dp[max_alp][j + problems[k][3]] = Math.min(dp[max_alp][j + problems[k][3]], dp[i][j] + problems[k][4]);
                        } else if (j + problems[k][3] > max_cop) {
                            dp[i + problems[k][2]][max_cop] = Math.min(dp[i + problems[k][2]][max_cop], dp[i][j] + problems[k][4]);
                        } else if (i + problems[k][2] <= max_alp && j + problems[k][3] <= max_cop) {
                            dp[i + problems[k][2]][j + problems[k][3]] = Math.min(dp[i + problems[k][2]][j + problems[k][3]], dp[i][j] + problems[k][4]);
                        }
                    }
                }
            }
        }
        // //최단 시간 찾기
        // int answer = Integer.MAX_VALUE;
        // for(int i=max_alp; i<=180; i++) {
        //     for(int j=max_cop; j<=180; j++) {
        //         answer = Math.min(answer, dp[i][j]);
        //     }
        // }
        return dp[max_alp][max_cop];
    }
}