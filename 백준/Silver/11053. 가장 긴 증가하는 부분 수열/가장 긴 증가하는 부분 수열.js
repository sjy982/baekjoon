const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let SN = inputData.shift().split(' ').map(x=>x*1); //수열
let dp = [];
dp[0] = 0;
dp[1] = 1;
for(let i=2; i<=N; i++) {
    let maxLen = [];
    for(let j = 0; j < i-1; j++) {
        if(SN[i-1] > SN[j]) {
            maxLen.push(dp[j+1]);
        }
    }
    if(maxLen.length === 0) {
        dp[i] = 1;
    } else {
        dp[i] = Math.max(...maxLen) + 1;
    }
}
console.log(Math.max(...dp));