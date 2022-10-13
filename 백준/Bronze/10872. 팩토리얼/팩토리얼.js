const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let N = parseInt(inputData);
let answel =1;
for(let i=1; i<=N; i++) {
    answel *= i;
}
console.log(answel);