const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let dp = [];
dp[1] = {value: 1, fs: 1, ns: 2};
for(let i=2; i<=N; i++) {
    let arr = [];
    if(Math.pow(dp[i-1].ns, 2) === i) {
        dp[i] = {value: 1, fs: dp[i-1].ns, ns: dp[i-1].ns + 1};
    } else {
        for(let j=1; j<=dp[i-1].fs; j++) {
            arr.push(1 + dp[i-Math.pow(j,2)].value);
        }
        dp[i] = {
            value: Math.min(...arr), 
            fs: dp[i-1].fs,
            ns: dp[i-1].ns
        };
    }
}
console.log(dp[N].value);