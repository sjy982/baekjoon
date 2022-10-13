const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let arr = new Array(26);
arr.fill(0);
for(let i=0; i<inputData.length; i++) {
    arr[inputData[i].charCodeAt(0)-97] += 1;
}
let output = '';
for(let i=0; i<arr.length; i++) {
    output += `${arr[i]} `;
}
console.log(output.trim());