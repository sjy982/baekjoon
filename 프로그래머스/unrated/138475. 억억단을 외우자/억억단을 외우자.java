class Solution {
    static int[] dp;
    static int[] max_arr;
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        dp = new int[e+1];
        for(int i=1; i<=e; i++) {
            for(int j=1; i*j<=e; j++) {
                dp[i*j] += 1;
            }
        }
        max_arr = new int[e+1];
        int max_value = -1;
        for(int i=e; i>=0; i--) {
            if(max_value <= dp[i]) {
                max_value = dp[i];
                max_arr[i] = i;
            } else max_arr[i] = max_arr[i+1];
        }
        for(int i=0; i<answer.length; i++) answer[i] = max_arr[starts[i]];
        return answer;
    }
}