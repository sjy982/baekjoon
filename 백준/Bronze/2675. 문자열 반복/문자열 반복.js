const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const forR = parseInt(inputData[0]);
let answel = '';
for(let i=0; i<forR; i++) {
    const inputData2 = inputData[i+1].split(' ');
    const R = parseInt(inputData2[0]);
    const inputData3 = inputData2[1].split('');
    const len_string = inputData3.length;
    for(let j=0; j<len_string; j++) {
        for(let k=0; k<R; k++) {
            answel += `${inputData3[j]}`
        }
    }
    answel += '\n';
}
console.log(answel);