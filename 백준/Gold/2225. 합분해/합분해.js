const fs =require('fs');
let inputData =fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let N = inputData[0];
let K = inputData[1];
let dp = new Array(K+1);
const MOD = 1000000000;
for(let i=0; i<=K; i++) {
    dp[i]= new Array(N+1);
    if(i===1) {
        dp[i].fill(1);
    } else {
        dp[i].fill(0);
    }
}
for(let i=2; i<=K; i++) {
    for(let j=0; j<=N; j++) {
        for(let k=0; k<=j; k++) {
            dp[i][j] += dp[i-1][j-k];
        }
        dp[i][j] = dp[i][j] % MOD;
    }
}
console.log(dp[K][N]);
