const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let dp = new Array(N+1);
let MOD = 10007;
for(let i=1; i<=N; i++) {
    dp[i] = new Array(11);
    dp[i].fill(0);
}
for(let i=0; i<dp[1].length; i++) {
    if(i===0) {
        dp[1][i] = 10;
    } else {
        dp[1][i] = 11-i;
    }
}
for(let i=2; i<=N; i++) {
    for(let j=1; j<dp[i].length; j++) {
        dp[i][0] = (dp[i][0] + dp[i-1][j]) % MOD;
    }
    dp[i][1] = dp[i][0];
    for(let j=2; j<dp[i].length; j++) {
        dp[i][j] = (dp[i][j-1] - dp[i-1][j-1] + MOD) % MOD;
    }
}
console.log(dp[N][0]);
