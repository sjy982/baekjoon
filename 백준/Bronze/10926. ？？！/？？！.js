const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().split(' ');
console.log(`${inputData[0].trim()}??!`);