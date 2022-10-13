const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let input = inputData[1].trim().split(' ').map(x=>x*1);
let ind = [];
let answel = -1;
DFS();
function DFS() {
    if(ind.length === N) {
        let sum = 0;
        for(let i=0; i<N-1; i++) {
            sum += Math.abs(input[ind[i]] - input[ind[i+1]]);
        }
        if(answel < sum) {
            answel = sum;
        }
        return;
    } else {
        for(let i=0; i<N; i++) {
            if(!ind.includes(i)) {
                ind.push(i);
                DFS();
                ind.pop();
            }
        }
    }
}
console.log(answel);