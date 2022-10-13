const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let T = inputData.shift();
let max = Math.max(...inputData);
let MOD = 1000000009;
let dp = [];
dp[1] = 1;
dp[2] = 2;
dp[3] = 4;
for(let i=4; i<=max; i++) {
    dp[i] = dp[i-1] % MOD + dp[i-2] % MOD + dp[i-3] %MOD;
}
let output = '';
for(let i=0; i<T; i++) {
    output += `${dp[inputData[i]] % MOD}\n`;
}
console.log(output.trim());
