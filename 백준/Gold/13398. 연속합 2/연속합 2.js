const fs= require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let input = inputData.shift().split(' ').map(x=>x*1);
let dp = new Array(N+1);
for(let i=1; i<=N; i++) {
    dp[i] = new Array(4); //0은 max, 1은 sum, 2는 -포함 sum, 3은 마이너스
}
dp[1][0] = input[0];
dp[1][3] = 0;
if(input[0] > 0) {
    dp[1][1] = input[0];
    dp[1][2] = input[0];
} else {
    dp[1][1] = 0;
    dp[1][2] = 0;
}
for(let i=2; i<=N; i++) {
    if(dp[i-1][2] + input[i-1] < 0) {
        dp[i][2] = 0;
    } else {
        dp[i][2] = dp[i-1][2] + input[i-1];
    }
    if(input[i-1] < 0) {
        if(dp[i-1][1] + input[i-1] < dp[i-1][1] + dp[i-1][3]) {
            dp[i][1] = dp[i-1][1] + dp[i-1][3];
            dp[i][3] = input[i-1];
        } else {
            dp[i][1] = dp[i-1][1] + input[i-1];
            dp[i][3] = dp[i-1][3];
        }
    } else {
        dp[i][1] = dp[i-1][1] + input[i-1];
        dp[i][3] = dp[i-1][3];
    }
    if(dp[i][1] < dp[i-1][2]) {
        dp[i][1] = dp[i-1][2];
        dp[i][3] = input[i-1];
    }
    if(dp[i][1] <= 0) {
        dp[i][1] = 0;
        dp[i][3] = 0;
        dp[i][0] = Math.max(dp[i-1][0], input[i-1]);
    } else {
        dp[i][0] = Math.max(dp[i-1][0], dp[i][1]);
    }
}
console.log(dp[N][0]);