const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const repeat = parseInt(inputData);
let answer =''
for(let i=0; i<repeat; i++) {
    for(let j=0; j<repeat; j++) {
        if(j >= repeat-(i+1)){
            answer += '*';
        } else {
            answer += ' ';
        }
    }
    answer += '\n';
}
console.log(answer);
