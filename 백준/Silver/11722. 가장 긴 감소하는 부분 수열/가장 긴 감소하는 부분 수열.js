const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin', "utf8").toString().trim().split('\n');
let N = inputData[0] * 1;
let dp = [];
dp[0] = 0;
dp[1] = 1;
let SN = inputData[1].split(' ').map(x=>x*1);
for(let i=2; i<=N; i++) {
    let len_max = [];
    dp[i] = 1;
    for(let j=i-1; j>=1; j--) {
        if(SN[i-1] < SN[j-1] && dp[i] < dp[j] + 1) {
            dp[i] = dp[j] + 1;
        }
    }
}
let answer = 0;
for (var j = 1; j <=N; j++) {
  answer = Math.max(answer, dp[j]);
}
console.log(answer);