const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let X = parseInt(inputData[0]);
let N = parseInt(inputData[1]);
let sum = 0;
for(let i=0; i<N; i++) {
    const inputData2= inputData[i+2].split(' ');
    sum += parseInt(inputData2[0]) * parseInt(inputData2[1]);
}
if(X===sum) {
    console.log('Yes');
} else {
    console.log('No');
}