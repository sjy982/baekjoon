const fs =require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let dp = [];
dp[0] = 1;
dp[1] = 0;
dp[2] = 3;
for(let i=4; i<=N; i+=2) {
    dp[i] = dp[i-2] * dp[2];
    //4부터 새로운 형태가 나옴.
    for(j=i; j>=4; j-=2) {
        dp[i] += dp[i-j] * 2;
    }
}
console.log(N%2===1 ? 0 : dp[N]);