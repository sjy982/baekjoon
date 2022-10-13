const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let cards = inputData.shift().split(' ').map(x=>x*1);
let dp = [];
dp[1] = cards[0];
for(let i=2; i<=N; i++) {
    dp[i] = cards[i-1];
    for(let j=i-1; j>=i-j; j--) {
        dp[i] = Math.min(dp[i], dp[j] + dp[i-j]);
    }
}
console.log(dp[N]);
