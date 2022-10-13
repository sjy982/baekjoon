const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let dp = new Array(N+1);
const MOD = 1000000000;
for(let i=0; i<dp.length; i++) {
    dp[i] = new Array(11);
    if(i===1) {
        for(let j=0; j<=9; j++) {
            if(j===0) {
                dp[i][j] = BigInt(0);
            } else {
                dp[i][j] = BigInt(1);
            }
        }
    } else {
        dp[i].fill(BigInt(0));
    }
}
dp[1][10] = BigInt(9);
for(let i=2; i<=N; i++) {
    for(let j=0; j<=9; j++) {
        dp[i][10] = dp[i-1][10] * BigInt(2) - dp[i-1][0] - dp[i-1][9];
        if(j===0) {
            dp[i][j+1] = dp[i-1][j];
        } else if(j===9) {
            dp[i][j-1] += dp[i-1][j];
        } else {
            if(dp[i-1][j] !== 0) {
                dp[i][j-1] += dp[i-1][j];
                dp[i][j+1] += dp[i-1][j];
            }
        }
    }
}
console.log((dp[N][10]%BigInt(MOD)).toString());
