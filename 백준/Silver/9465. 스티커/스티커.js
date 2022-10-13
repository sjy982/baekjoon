const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let T = inputData.shift() * 1;
let output ='';
for(let t = 0; t < T; t++) {
    let N = inputData.shift() * 1;
    let dp = new Array(N+1);
    let top = inputData.shift().trim().split(' ').map(x=>x*1);
    let bottom = inputData.shift().trim().split(' ').map(x=>x*1);
    for(let j = 0; j <=N; j++) {
        dp[j] = new Array(3);
        if(j===0) {
            dp[j].fill(0);
        }
    }
    dp[1][1] = top[0];
    dp[1][2] = bottom[0];
    dp[1][0] = Math.max(top[0], bottom[0]);
    for(let i=2; i<=N; i++) {
        dp[i][1] = Math.max(dp[i-2][0] + top[i-1], dp[i-1][2] + top[i-1]);
        dp[i][2] = Math.max(dp[i-2][0] + bottom[i-1], dp[i-1][1] + bottom[i-1]);
        dp[i][0] = Math.max(dp[i][1], dp[i][2]);
    }
    output += `${dp[N][0]}\n`;
}
console.log(output.trim());