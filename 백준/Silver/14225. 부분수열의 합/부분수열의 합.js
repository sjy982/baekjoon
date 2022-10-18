const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let sn = inputData[1].trim().split(' ').map(x => x * 1);
let visited = new Array(N).fill(false);
let check = []; //부분 수열의 합을 저장해줄 배열
let result = [];
let sum = 0;
let answel = 0;
DFS();
for(let i=1; i<check.length; i++) {
    if(check[i] === undefined) {
        answel = i;
        break;
    }
}
if(answel === 0) {
    answel = check.length;
}
console.log(answel);

function DFS() {
    if (result.length === N) {
        return;
    } else {
        let bn_arr = []; //before_number
        for(let i=0; i<sn.length; i++) {
            let bn_top = bn_arr.length === 0 ? -1 : bn_arr[bn_arr.length-1];
            let re_top = result.length === 0 ? -1 : result[result.length-1];
            if(!visited[i]) {
                if(bn_top !== sn[i] && re_top <= sn[i]) {
                    visited[i] = true;
                    result.push(sn[i]);
                    sum += sn[i];
                    check[sum] = true;
                    DFS();
                    visited[i] = false;
                    result.pop();
                    sum -= sn[i];
                    bn_arr.push(sn[i]);
                }
            }
        }
    }
}