const fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString().trim().split('');
let answel ='';
for(let i=0; i<input.length; i++) {
    let binary = parseInt(input[i],8).toString(2);
    if(i===0) {
        answel += binary;
    } else {
        answel += '0'.repeat(3-binary.length) + binary;
    }
}
console.log(answel);