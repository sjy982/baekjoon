const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let dp = new Array(N + 1);
for(let i=1; i<dp.length; i++) {
    dp[i] = new Array(i+1);
}
dp[1][0] = inputData[0]  * 1;
dp[1][dp[1].length-1] = inputData[0] * 1;
for(let i=2; i<=N; i++) {
    let triangle = inputData[i-1].split(' ');
    let aMax = 0;
    let mMax = new Array(i);
    for(let j=0; j<dp[i-1].length-1; j++) {
        aMax = Math.max(aMax, dp[i-1][j] + triangle[j] * 1, dp[i-1][j] + triangle[j+1] * 1);
        if(mMax[j] === undefined) {
            mMax[j] = dp[i-1][j] + triangle[j] * 1;
        } else {
            let gv = mMax[j] >= dp[i-1][j] + triangle[j] * 1 ? mMax[j] :  dp[i-1][j] + triangle[j] * 1;
            mMax[j] = gv;
        }
        
        if(mMax[j+1] === undefined) {
            mMax[j+1] = dp[i-1][j] + triangle[j+1] * 1;
        } else {
            let gv = mMax[j+1] >= dp[i-1][j] + triangle[j+1] * 1 ? mMax[j+1] : dp[i-1][j] + triangle[j+1] * 1;
            mMax[j+1] = gv;
        }
    }
    dp[i][dp[i].length-1] = aMax;
    for(let k=0; k<dp[i].length-1; k++) {
        dp[i][k] = mMax[k];
    }
}
console.log(dp[N][dp[N].length-1]);