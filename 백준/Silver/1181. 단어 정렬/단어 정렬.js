const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let output = '';
inputData.sort((a,b) => {
    if(a.length === b.length) {
        return a < b ? -1 : a > b ? 1 : 0;
    } else {
        return a.length - b.length;
    }
});
let answel = inputData.filter((number,index) => index === 0 ? true : number !== inputData[index-1]);
answel.map((ele,index) => {
    output += `${ele}\n`;
});
console.log(output.trim());