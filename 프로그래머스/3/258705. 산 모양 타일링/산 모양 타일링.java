class Solution {
    static final int MOD = 10007;
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n + 1][2]; //두 가지 타입이 있음.
        if(tops[0] == 0) {
            dp[1][0] = 3; //전체 모양
            dp[1][1] = 2; //type 2
        } else {
            dp[1][0] = 4;
            dp[1][1] = 3;
        }
        
        for(int i=2; i<=n; i++) {
            int top = tops[i - 1];
            int cnt1 = 3;
            int cnt2 = 2;
            if(top == 0) {
                cnt1 = 2;
                cnt2 = 1;
            } 
            dp[i][0] = (cnt1 * dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][1] = (cnt2 * dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }
        return dp[n][0];
    }
}