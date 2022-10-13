const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
let answer='';
for(let i=0; i<repeat; i++) {
    const inputData2 = inputData[i+1].split('');
    let length=inputData2.length;
    let weight=0;
    let hop=0;
    for(let j=0; j<length; j++) {
        if(inputData2[j] === 'O') {
            weight += 1;
            hop += 1+(weight-1);
        } else {
            weight =0;
        }
    }
    answer += `${hop}\n`;
}
console.log(answer);