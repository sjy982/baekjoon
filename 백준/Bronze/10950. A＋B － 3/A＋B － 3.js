const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
for(let i=0; i<repeat; i++) {
    const forInput = inputData[i+1].trim().split(' ');
    console.log(parseInt(forInput[0]) + parseInt(forInput[1]));
}