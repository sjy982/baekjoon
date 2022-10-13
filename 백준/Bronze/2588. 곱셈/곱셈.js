const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let A = parseInt(inputData[0]);
let B = parseInt(inputData[1]);
let string2 = inputData[1].split('');
let C = parseInt(string2[2]);
let D = parseInt(string2[1]);
let F = parseInt(string2[0]);

console.log(A*C);
console.log(A*D);
console.log(A*F);
console.log(A*B);