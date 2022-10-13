const fs= require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let SN = inputData.shift().split(' ').map(x=>x*1);
let dp = [];
if(SN[0] > 0) {
    dp[1] = {max: SN[0], sum: SN[0]};
} else {
    dp[1] = {max: SN[0], sum: 0};
}
for(let i=2; i<=N; i++) {
    let max_n = dp[i-1].sum + SN[i-1];
    let sum_n = dp[i-1].sum + SN[i-1];
    if(max_n <= dp[i-1].max) {
        max_n = dp[i-1].max;
    }
    if(sum_n <= 0) {
        sum_n = 0;
    }
    dp[i] = {max: max_n, sum: sum_n};
}
console.log(dp[N].max);