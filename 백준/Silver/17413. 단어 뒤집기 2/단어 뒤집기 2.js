const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let stack = [];
let check = false;
let output = '';
for(let i=0; i<inputData.length; i++) {
    if(inputData[i] === '<') {
        if(stack.length !== 0) {
            stringReverse(stack);
        }
        check = true;
        output += inputData[i];
    } else if(inputData[i] === '>') {
        check = false;
        output += inputData[i];
    } else {
        //'<' '>'를 제외한 문자들 들어옴.
        if(check) {
            output += inputData[i];
        } else {
            if(inputData[i] === ' ') {
                if(stack.length !== 0) {
                  stringReverse(stack);
                }
                output += inputData[i];
            } else {
                stack.push(inputData[i]);
                if(i===inputData.length - 1) {
                    stringReverse(stack);
                }
            }
        }
    }
}
function stringReverse(arr) {
    while(arr.length !== 0) {
        output += arr.pop();
    }
}
console.log(output);