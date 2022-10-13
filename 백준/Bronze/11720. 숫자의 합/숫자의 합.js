const fs =require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
const inputData2 = inputData[1].trim().split('');
let answer=0;
for(let i=0; i<repeat; i++) {
    answer += parseInt(inputData2[i]);
}
console.log(answer);