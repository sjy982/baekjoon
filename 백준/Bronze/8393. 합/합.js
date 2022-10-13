const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const repeat = parseInt(inputData);
let hop = 0;
for(let i=0; i<repeat; i++) {
    hop = hop + (i+1);
}
console.log(hop);