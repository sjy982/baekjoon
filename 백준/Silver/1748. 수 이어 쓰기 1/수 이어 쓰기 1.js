const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim();
let len = N.length;
let w = '9';
let c = '1';
let answel = 0;
for(let i=1; i<len; i++) {
    answel += parseInt(w)*i;
    w+='0';
    c+='0';
}
answel += (N - parseInt(c) + 1) * len;
console.log(answel);