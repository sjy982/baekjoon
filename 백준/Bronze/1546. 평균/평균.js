const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let repeat = parseInt(inputData[0]);
let inputData2 = inputData[1].toString().split(' ');
for(let i=0; i<repeat; i++) {
    inputData2[i] = parseInt(inputData2[i]);
}
let max = Math.max(...inputData2);
let maxIndex = inputData2.indexOf(max);
let average = 0;

for(let i=0; i<repeat; i++) {
    average += inputData2[i]/inputData2[maxIndex]*100;
}
average = average / repeat;
console.log(average);


