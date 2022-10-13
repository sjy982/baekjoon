const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let lneq_arr = inputData[1].trim().split(' ');
let result = [];
let max_value = -1;
let min_value = 10000000000;
DFS();
function DFS() {
    if(result.length === N + 1) {
        let cur_value = result.join('')*1;
        if(max_value < cur_value) {
            max_value = cur_value;
        }
        if(min_value > cur_value) {
            min_value = cur_value;
        }
        return;
    } else {
        for(let i=0; i<=9; i++) {
            if(!result.includes(i)) {
                if(result.length === 0) {
                    result.push(i);
                    DFS();
                    result.pop();
                } else {
                    if(calcul(result[result.length-1], i, lneq_arr[result.length-1])) {
                        result.push(i);
                        DFS();
                        result.pop();
                    }
                }
            }
        }
    }
} 
function calcul(a,b,lneq) {
    if(lneq === '>') {
        return a>b;
    } else if(lneq === '<') {
        return a<b;
    }
}
max_value = max_value.toString();
min_value = min_value.toString();
max_value.length !== N+1 ? max_value = '0' + max_value : max_value = max_value;
min_value.length !== N+1 ? min_value = '0' + min_value : min_value = min_value;
console.log(`${max_value}\n${min_value}`);