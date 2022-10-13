const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let dp = [];
dp[0] = 0;
let max_arr = new Array(N+1).fill('');
for(let i=1; i<=N; i++) {
    let input = inputData[i-1].trim().split(' ').map(x=>x*1);
    max_arr[i+input[0]-1] = `${max_arr[i+input[0]-1]} ${dp[i-1] + input[1]}`;
    let am = Math.max(...max_arr[i].trim().split(' ').map(x=>x*1));
    dp[i] = Math.max(am, dp[i-1]);
}
console.log(dp[N]);