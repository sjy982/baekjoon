const fs= require('fs');
let inputData =  fs.readFileSync('/dev/stdin').toString().trim().split('');
console.log(inputData.length);