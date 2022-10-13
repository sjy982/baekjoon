const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const repeat = parseInt(inputData);
let answer = '';
for(let i=0; i<repeat; i++) {
    answer += `${i+1}\n`;
}
console.log(answer);