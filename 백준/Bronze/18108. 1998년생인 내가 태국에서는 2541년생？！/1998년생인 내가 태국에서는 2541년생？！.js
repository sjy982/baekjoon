const fs = require('fs');
let inputData = fs.readFileSync("/dev/stdin").toString().split(' ');
const A = parseInt(inputData[0]);
console.log(A - 543);