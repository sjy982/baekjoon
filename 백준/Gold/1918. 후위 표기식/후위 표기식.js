const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let stack = [];
let output = '';
for (let i = 0; i < inputData.length; i++) {
    if (inputData[i].charCodeAt(0) >= 65 && inputData[i].charCodeAt(0) <= 90) {
        output += inputData[i];
    } else if (inputData[i] === '(') {
        stack.push(inputData[i]);
    } else if (inputData[i] === ')') {
        // '('까지 pop해줌.
        if (stack[stack.length - 1] !== '(') {
            let end = false;
            while (!end) {
                output += stack.pop();
                if (stack[stack.length - 1] === '(' || stack.length === 0) {
                    end = true;
                }
            }
        }
        stack.pop(); // '('를 pop해서 빼줌.
    } else {
        // */+-연산자들 들어옴
        if (stack.length === 0 || stack[stack.length - 1] === '(') {
            stack.push(inputData[i]);
        } else if (stack[stack.length - 1] === '*' || stack[stack.length - 1] === '/') {
            let end = false;
            while (!end) {
                output += stack.pop();
                if (inputData[i] === '*' || inputData[i] === '/') {
                    if (stack[stack.length - 1] === '+' || stack[stack.length - 1] === '-' || stack[stack.length - 1] === '(' || stack.length === 0) {
                        end = true;
                    }
                } else if (inputData[i] === '+' || inputData[i] === '-') {
                    if (stack[stack.length - 1] === '(' || stack.length === 0) {
                        end = true;
                    }
                }
            }
            stack.push(inputData[i]);
        } else if (stack[stack.length - 1] === '+' || stack[stack.length - 1] === '-') {
            if (inputData[i] === '+' || inputData[i] === '-') {
                let end = false;
                while (!end) {
                    output += stack.pop();
                    if (stack[stack.length - 1] === '(' || stack.length === 0) {
                        end = true;
                    }
                }
            }
            stack.push(inputData[i]);
        }
    }
}
while (stack.length !== 0) {
    output += stack.pop();
}
console.log(output);