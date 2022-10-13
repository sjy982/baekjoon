const fs = require('fs')
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let SN = inputData.shift().split(' ').map(x=>x*1);
let dp = [];
dp[0] = 0;
dp[1] = SN[0];
for(let i=2; i<=N; i++) {
    let arr = [];
    for(let j=i-2; j>=0; j--) {
        if(SN[i-1] > SN[j]) {
            arr.push(dp[j+1] + SN[i-1]);
        } 
    }
    dp[i] = arr.length === 0 ? SN[i-1] : Math.max(...arr);
}
console.log(Math.max(...dp));