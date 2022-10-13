const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let NM = inputData.shift().split(' ');
let N = NM[0] * 1;
let M = NM[1] * 1;
let nhMap = new Map() // not heard
let nsMap = new Map() // not seen
let answel = [];
let output = '';

for(let i=0; i<inputData.length; i++) {
    if(i<N) {
        nhMap.set(inputData[i], inputData[i]);
    } else {
        nsMap.set(inputData[i], inputData[i]);
    }
}
let count = 0;
nsMap.forEach((key,value) => {
    if(nhMap.has(value)) {
        count += 1;
        answel.push(value);
    }
})
output = `${count}\n`;
answel.sort();
answel.map((ele) => {
    output += `${ele}\n`;
})
console.log(output.trim());