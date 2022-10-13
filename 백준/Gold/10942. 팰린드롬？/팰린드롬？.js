const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let SN = inputData.shift().split(' ').map(x=>x*1);
let M = inputData.shift() * 1;
let dp = new Array(N + 1);
for(let i=1; i<=N; i++) {
    dp[i] = new Array(N+1);
}
dp[1][1] = 1;
for( let i=2; i<=N; i++) {
    for(let j=1; j<=i-1; j++) {
        if(j+1 < i-1) {
            if(SN[i-1] === SN[j-1]) {
                if(dp[j+1][i-1] === 1) {
                    dp[j][i] = 1;
                } 
            } 
        } else {
            if(SN[i-1] === SN[j-1]) {
                dp[j][i] = 1;
            } 
        }
    }
    dp[i][i] = 1;
}
let output = '';
for(let i=0; i<M; i++) {
    let [a, b] = inputData[i].split(' ');
    if(dp[a][b] === 1) {
        output += '1\n';
    } else {
        output += '0\n';
    }
}
console.log(output.trim());