const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let N = inputData.shift();
let max = Math.max(...inputData);
let dp = [];
dp[1] = 1;
dp[2] = 2;
dp[3] = 4;
for(let i=4; i<=max; i++) {
    dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
}
let output = '';
for(let i=0; i<N; i++) {
    output += `${dp[inputData[i]]}\n`;
}
console.log(output.trim());