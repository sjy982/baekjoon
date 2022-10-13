const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let repeat = parseInt(inputData);
let answer ='';
for(let i=0; i<repeat; i++) {
    for(let j=0; j<i+1; j++) {
        answer += "*";
    }
    answer += "\n";
}
console.log(answer);