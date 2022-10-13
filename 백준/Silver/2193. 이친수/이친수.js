const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let dp = [];
dp[1] = {lcs: BigInt(1), l1: BigInt(1)};
for(let i=2; i<=N;  i++) {
    dp[i] = {
        lcs: dp[i-1].lcs * BigInt(2) - dp[i-1].l1,
        l1: dp[i-1].lcs - dp[i-1].l1}
}
console.log((dp[N].lcs).toString());