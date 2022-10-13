const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let repeat = parseInt(inputData);
let answer = '';
for(let i = repeat; i > 0; i--) {
    answer += `${i}\n`;
}
console.log(answer);