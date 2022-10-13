const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift();
let inputData2 = inputData[0].split(' ').map(x=>x*1);
let stack = [];
let answel = [];
let output = '';
for(let i=N-1; i>=0; i--) {
    let end = false
    while(!end) {
        if(stack.length ===0) {
            answel.push(-1);
            stack.push(inputData2[i]);
            end = true;
        } else if(inputData2[i]<stack[stack.length-1]) {
            answel.push(stack[stack.length-1]);
            stack.push(inputData2[i]);
            end = true
        } else {
            stack.pop();
        }
    }
    output = `${answel.pop()} ${output}`;
}
console.log(output.trim());

