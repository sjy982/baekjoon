const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].split(' ').map(x=>x*1);
let input = inputData[1].split(' ').map(x=>x*1);
input.sort((a,b) => {
    return a-b;
})
let result = [];
let index_arr = [];
let answel = '';
DFS();

function DFS() {
    if(result.length === M) {
        answel += `${result.join(' ')}\n`;
        return ;
    } else {
        let bn_arr = [];
        for(let i=0; i<N; i++) {
            if(bn_arr.length === 0) {
                if(!index_arr.includes(i)) {
                    result.push(input[i]);
                    index_arr.push(i);
                    DFS();
                    result.pop();
                    index_arr.pop();
                    bn_arr.push(input[i]);
                }
            } else {
                if(!index_arr.includes(i) && bn_arr[bn_arr.length-1] !== input[i]) {
                    result.push(input[i]);
                    index_arr.push(i);
                    DFS();
                    result.pop();
                    index_arr.pop();
                    bn_arr.push(input[i]);
                }
            }
        }
    }
}
console.log(answel.trim());