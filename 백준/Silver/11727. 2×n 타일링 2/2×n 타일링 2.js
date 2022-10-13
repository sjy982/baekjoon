const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let dp = [];
dp[1] = BigInt(1);
dp[2] = BigInt(3);
for(let i=3; i<=N; i++) {
    dp[i] = dp[i-1] + BigInt(2) * dp[i-2];
}
console.log(parseInt(dp[N] % BigInt(10007)));