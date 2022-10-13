const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let express = inputData.shift().split('');
let stack = [];
let output = 0;
for(let i=0; i<express.length; i++) {
    if(express[i]==='/') {
        output = stack[stack.length-2] / stack[stack.length - 1];
        sp(output);
    } else if(express[i]==='*') {
        output = stack[stack.length-2] * stack[stack.length - 1];
        sp(output);
    } else if(express[i]==='+') {
        output = stack[stack.length-2] + stack[stack.length - 1];
        sp(output);
    } else if(express[i]==='-') {
        output = stack[stack.length-2] - stack[stack.length - 1];
        sp(output);
    } else {
        stack.push(inputData[express[i].charCodeAt(0)-65] * 1);
    }
}
function sp(n) {
    //스택 처리
    stack.pop();
    stack.pop();
    stack.push(n);
}
console.log(stack[0].toFixed(2));

