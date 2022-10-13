const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let answel = '';
let i=2;
while(N!==1) {
    if(N%i===0) {
        answel += `${i}\n`;
        N = N/i;
        i=2;
    } else {
        i+=1;
    }
}
console.log(answel.trim());