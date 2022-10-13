const fs = require('fs');
let wine = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let N = wine.shift();
let dp = [];
dp[0] = 0
dp[1] = wine[0];
dp[2] = wine[0] + wine[1];
dp[3] = Math.max(wine[2] + wine[1], wine[2] + wine[0], wine[1] + wine[0]);
for(let i=4; i<=N; i++) {
    dp[i] = Math.max(dp[i-1], wine[i-1] + wine[i-2] + dp[i-3], wine[i-1] + dp[i-2]);
}
console.log(dp[N]);