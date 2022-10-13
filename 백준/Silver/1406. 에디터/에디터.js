const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let front_arr = inputData[0].trim().split('');
let back_arr = [];
let N = inputData[1] * 1;
for(let i=2; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ');
    if(input[0] === 'L') {
        if(front_arr.length !== 0) {
            back_arr.push(front_arr.pop());
        }
    } else if(input[0] === 'D') {
        if(back_arr.length !== 0) {
            front_arr.push(back_arr.pop());
        }
    } else if(input[0] === 'B') {
       front_arr.pop();
    } else if(input[0] === 'P') {
        front_arr.push(input[1]);
    }
}
let answel = front_arr.join('');
while(back_arr.length !== 0) {
    answel += back_arr.pop();
}
console.log(answel.trim());