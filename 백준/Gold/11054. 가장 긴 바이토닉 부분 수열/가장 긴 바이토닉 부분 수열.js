const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let input = inputData.shift().trim().split(' ').map(x=>x*1);
let dp = new Array(N+1);
for(let i=1; i<=N; i++) {
    dp[i] = new Array(2); //0은 증가하는 부분 수열 1은 감소하는 부분 수열
}
dp[1][0] = 1;
dp[N][1] = 1;
for(let i=2; i<=N; i++) {
    dp[i][0] = 1;
    for(let j=i-1; j>=1; j--) {
        if(input[j-1] < input[i-1] && dp[j][0] + 1 > dp[i][0]) {
            dp[i][0] = dp[j][0] + 1;
        } 
    }
}
for(let i=N-1; i>=1; i--) {
    dp[i][1] = 1;
    for(let j=i+1; j<=N; j++) {
        if(input[i-1] > input[j-1] && dp[j][1] + 1 > dp[i][1]) {
            dp[i][1] = dp[j][1] + 1
        }
    }
}
let by_max = [];
for(let i=1; i<=N; i++) {
    if(i===1) {
        by_max.push(dp[i][1]);
    } else if(i===N) {
        by_max.push(dp[i][0]);
    } else {
        by_max.push(dp[i][1] + dp[i][0] - 1);
    }
}
console.log(Math.max(...by_max));