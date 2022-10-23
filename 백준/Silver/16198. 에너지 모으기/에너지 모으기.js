const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let sn = inputData[1].trim().split(' ').map(x => x * 1);
let max_value = -1;
DFS(0, sn);
console.log(max_value);

function DFS(sum, sn_arr) {
    if (sn_arr.length === 2) {
        if(max_value < sum) {
            max_value = sum;
        }
        return;
    } else {
        for (let i = 1; i < sn_arr.length - 1; i++) {
            let nsum = sum + sn_arr[i - 1] * sn_arr[i + 1];
            let sort_sn = sn_arr.filter((ele, ind) => ind !== i);
            DFS(nsum , sort_sn);
        }
    }
}