const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let ti = [];
let pi = [];
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].split(' ').map(x=>x*1);
    ti.push(input[0]);
    pi.push(input[1]);
}
let result = [];
let answel = -1;
DFS(0);
function DFS(sum_day) {
    if(sum_day >= N) {
        let sum =0;
        for(let i=0; i<result.length; i++) {
            sum += result[i];
        }
        if(answel < sum) {
            answel = sum;
        }
        return;
    } else {
        for(let i=1; i<=N; i++) {
            if(sum_day < i) {
                if(i+ti[i-1]-1 <= N) {
                    result.push(pi[i-1]);
                    DFS(i + ti[i-1] - 1);
                    result.pop();
                } else {
                    DFS(i + ti[i-1] - 1);
                }
            }
        }
    }
}
console.log(answel);