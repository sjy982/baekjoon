const fs= require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = inputData.length;
for(let i=0; i<repeat; i++) {
    inputData[i] = parseInt(inputData[i]);
}
let max = Math.max(...inputData);
let maxIndex = inputData.indexOf(max);
console.log(`${max}\n${maxIndex+1}`);