const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let output = '';
inputData.sort((a,b) => {
    let ageA = a.split(' ')[0] * 1;
    let ageB = b.split(' ')[0] * 1;
    return ageA - ageB;
})
inputData.map((ele) => {
    output += `${ele}\n`;
})
console.log(output.trim());