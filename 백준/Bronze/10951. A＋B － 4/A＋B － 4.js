const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const length = inputData.length;
let answer ='';
for(let i=0; i<length; i++) {
    const inputData2 = inputData[i].trim().split(' ');
    answer += `${parseInt(inputData2[0])+parseInt(inputData2[1])}\n`;
}
console.log(answer);