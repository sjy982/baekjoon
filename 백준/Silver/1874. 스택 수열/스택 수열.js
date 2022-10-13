const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let N = inputData.shift();
let sortInput = [...inputData];
let stack = [];
let output = '';
sortInput.sort((a,b) => {
    return b-a;
})
for(let i=0; i<N; i++) {
    let end = false;
    while(!end) {
        let top = sortInput.length - 1;
        if(inputData[i] >= sortInput[top]) {
            stack.push(sortInput.pop());
            output += '+\n';
        } else {
            if(inputData[i] === stack[stack.length-1]) {
                stack.pop();
                output += '-\n';
                end = true;
            } else {
                output = 'NO';
                end = true;
            }
        }
    }
    if(output === 'NO') {
        break;
    }
}
console.log(output.trim());
