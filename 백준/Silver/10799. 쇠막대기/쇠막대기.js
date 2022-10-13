const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let end = false;
let stack = []; //close stack;
let lStack = []; //laser stack;
let output = 0;
while(inputData.length !== 0) {
    if(inputData[inputData.length-1] === ')') {
        stack.push(inputData.pop());
        if(inputData[inputData.length-1]=== '(') {
            stack.pop();
            inputData.pop();
            for(let i=0; i<lStack.length; i++) {
                lStack[i] += 1;
            }
        } else {
            // ')'다음 바로 ')'이면
            lStack.push(0);
        }
    } else {
        //'(' 나오면
        inputData.pop();
        stack.pop();
        output += lStack.pop() + 1;
    }
}
console.log(output);