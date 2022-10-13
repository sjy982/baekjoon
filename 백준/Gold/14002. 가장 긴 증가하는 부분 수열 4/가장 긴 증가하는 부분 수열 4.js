const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let SN = inputData.shift().split(' ').map(x=>x*1);
let dp = [];
dp[1] = {len: 1, sn: `${SN[0]}`};
for(let i=2; i<=N; i++) {
    let tem = [];
    for(let j=0; j<i-1; j++) {
        if(SN[i-1] > SN[j]) {
            tem.push(dp[j+1]);
        }
    }
    if(tem.length === 0) {
        dp[i] = {len: 1, sn: `${SN[i-1]}`};
    } else {
        tem.sort((a,b) => {
            return b.len - a.len;
        })
        dp[i] = {len: tem[0].len + 1, sn: `${tem[0].sn} ${SN[i-1]}`};
    }
}
dp.sort((a,b) => {
    return b.len - a.len;
})
console.log(`${dp[0].len}\n${dp[0].sn}`);