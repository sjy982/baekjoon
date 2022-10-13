const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, S] = inputData[0].trim().split(' ').map(x=>x*1);
let sn = inputData[1].trim().split(' ').map(x=>x*1);
let result = [];
let cout = 0;
DFS(0);
function DFS(sum) {
    if(sum === S && result.length !==0) {
        cout += 1;
    }
    if(result.length === N) {
        return;
    } else {
        for(let i=0; i<N; i++) {
            let result_top = result.length === 0 ? -1 : result[result.length -1];
            if(!result.includes(i)) {
                if(result_top < i) {
                    result.push(i);
                    DFS(sum + sn[i]);
                    result.pop();
                }
            }
        }
    }
}
console.log(cout);