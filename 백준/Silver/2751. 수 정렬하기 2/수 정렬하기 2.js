const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let arr = [];
for(let i=1; i<inputData.length; i++) {
    arr.push(inputData[i]*1);
}
arr.sort((a,b) => {
    return a-b;
})
let answel = '';
arr.map((number) => answel += `${number}\n`);
console.log(answel.trim());