const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let ep1 = inputData[0] + inputData[1];
let ep2 = inputData[2] + inputData[3];
console.log(parseInt(ep1) + parseInt(ep2));