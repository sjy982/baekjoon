const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
let answer = '';
for(let i=0; i<repeat; i++) {
    const inputData2 = inputData[i+1].split(' ');
    const students = parseInt(inputData2[0]);
    let average = 0;
    let AAS = 0; //above average student;
    for(let j=0; j<students; j++) {
        average += parseInt(inputData2[j+1]);
    }
    average = average/students;
    for(let k=0; k<students; k++) {
        if(inputData2[k+1] > average) {
            AAS += 1;
        }
    }
    answer += `${(100*AAS/students).toFixed(3)}%\n`;
}
console.log(answer);