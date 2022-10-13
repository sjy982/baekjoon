const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const inputData1 = inputData[0].trim().split(' '); //row1
const inputData2 = inputData[1].trim().split(' '); //row2
const N = parseInt(inputData1[0]);
const X = parseInt(inputData1[1]);
let answer = '';
for(let i=0; i<N; i++) {
    if(X>parseInt(inputData2[i])) {
        answer += `${inputData2[i]} `;
    }
}
console.log(answer);