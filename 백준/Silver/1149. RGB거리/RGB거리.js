const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let dp = new Array(N+1);
for(let i=1; i<=N; i++) {
    dp[i] = new Array(3);
}
dp[1] = inputData[0].split(' ').map(x=>x*1);
for(let i=2; i<=N; i++) {
    let price = inputData[i-1].split(' ').map(x=>x*1);
    for(let j=0; j<price.length; j++) {
        if(j===0) {
            dp[i][j] = Math.min(price[j] + dp[i-1][1], price[j] + dp[i-1][2])
        }else if(j===1) {
            dp[i][j] = Math.min(price[j] + dp[i-1][0], price[j] + dp[i-1][2])
        } else {
            dp[i][j] = Math.min(price[j] + dp[i-1][0], price[j] + dp[i-1][1])
        }
    }
}
console.log(Math.min(...dp[N]));