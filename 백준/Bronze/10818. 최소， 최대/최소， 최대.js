const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
const inputData2 = inputData[1].trim().split(' ');
for(let i=0; i<repeat; i++) {
    inputData2[i] = parseInt(inputData2[i]);
}
let max = Math.max(...inputData2);
let min = Math.min(...inputData2);
console.log(`${min} ${max}`)