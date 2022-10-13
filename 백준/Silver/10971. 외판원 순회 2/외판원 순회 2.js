const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let W = new Array(N);
let MV = 1000000000;
for(let i=0; i<N; i++) {
    W[i] = new Array(N);
}
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        W[i-1][j] = input[j];
    }
}

let result = [];
let answel = MV;
DFS();
function DFS() {
    if(result.length === N) {
        let sum = 0;
        for(let i=0; i<N; i++) {
            if(i===N-1) {
                sum += W[result[i]][result[0]]; //출발했던 도시로 복귀.
            } else {
                sum += W[result[i]][result[i+1]];
            }
        }
        if(answel > sum) {
            answel = sum;
        }
        return;
    } else {
        for(let i=0; i<N; i++) {
            if(!result.includes(i)) {
                if(result.length === 0) {
                    result.push(i);
                    DFS();
                    result.pop();
                } else if(result.length === N-1) {
                    if(W[result[result.length-1]][i] !== 0 && W[i][result[0]] !== 0) {
                        result.push(i);
                        DFS();
                        result.pop();
                    }
                } else {
                    if(W[result[result.length-1]][i] !==0) {
                        result.push(i);
                        DFS();
                        result.pop();
                    }
                }
            }
        }
    }
}
console.log(answel === MV ? 0 : answel);