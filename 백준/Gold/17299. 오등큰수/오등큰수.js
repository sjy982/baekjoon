const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let inputData2 = inputData[0].split(' ').map(x=>x*1);
let map = new Map();
let answel = [];
let output = '';
inputData2.map((ele,index) => {
    if(!map.has(ele)) {
        map.set(ele,1);
    } else {
        map.set(ele, map.get(ele) + 1);
    }
});

let stack = [];
for(let i=inputData2.length-1; i>=0; i--) {
    let end = false;
    while(!end) {
        if(stack.length === 0) {
            answel.push(-1);
            stack.push({key:inputData2[i], value: F(inputData2[i])});
            end = true;
        } else if(F(inputData2[i]) < stack[stack.length-1].value) {
            answel.push(stack[stack.length-1].key);
            stack.push({key:inputData2[i], value: F(inputData2[i])});
            end = true;
        } else {
            stack.pop();
        }
    }
    output = `${answel.pop()} ${output}`;
}
console.log(output.trim());

function F(n) {
    return map.get(n);
}