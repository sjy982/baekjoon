const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let dp = new Array(N+1);
let MOD = 9901;
for(let i=1; i<=N; i++) {
    dp[i] = new Array(2);
}
dp[1][0] = 3;
dp[1][1] = 1;
for(let i=2; i<=N; i++) {
    dp[i][0] = ((dp[i-1][0] - dp[i-1][1] + MOD) * 2 + dp[i-1][0]) % MOD;
    dp[i][1] = (dp[i-1][0] - dp[i-1][1] + MOD) % MOD;
}
console.log(dp[N][0]);