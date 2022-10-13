const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let N = inputData.shift();
let max = Math.max(...inputData);
const MOD = 1000000009;
let dp = [];
dp[1] = {value: 1, one: 1, two:0, three: 0};
dp[2] = {value: 1, one: 0, two:1, three: 0};
dp[3] = {value: 3, one: 1, two:1, three: 1};
for(let i = 4; i<=max; i++) {
    let o = (dp[i-1].value - dp[i-1].one) % MOD;
    let t = (dp[i-2].value - dp[i-2].two) % MOD;
    let th = (dp[i-3].value - dp[i-3].three) % MOD;
    dp[i] = {value: (o + t + th), one: o, two: t, three: th};
}
let output = '';
for(let i=0; i<N; i++) {
    output += `${(dp[inputData[i]].value)%MOD}\n`;
}
console.log(output.trim());