const fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString().trim().split('');
let answel = '';
while(input.length>=3) {
    answel = parseInt(input.splice(input.length-3,3).join(''),2).toString(8) + answel;
}
if(input.length !== 0) {
    answel = parseInt(input.join(''),2).toString(8) + answel;
}
console.log(answel);