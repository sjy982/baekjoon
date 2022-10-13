const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const stringArr = inputData.split(' ');
let count = 0;
for(let i=0; i<stringArr.length; i++) {
    if(stringArr[i] !== '') {
        count += 1;
    } 
}
console.log(count);

