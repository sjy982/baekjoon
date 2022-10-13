const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
let answer='';
for(let i=0; i<repeat; i++) {
    const inputData2 = inputData[i+1].trim().split(' ');
    answer += `Case #${i+1}: ${parseInt(inputData2[0]) + parseInt(inputData2[1])}\n`;
}
console.log(answer);