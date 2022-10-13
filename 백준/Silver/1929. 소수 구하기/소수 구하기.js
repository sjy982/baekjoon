const fs = require('fs');
let [start,end] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let decimalArray = new Array(end+1).fill(true);
decimalArray.splice(0,2,false,false);
for(let i=2; i*i<=end; i++) {
    for(let j=i*i; j<=end; j+=i) {
        decimalArray[j]=false;
    }
}

let answel = ''
for(let i=start; i<=end; i++) {
    if(decimalArray[i]) {
        //소수라면.
        answel += `${i}\n`;
    }
}
console.log(answel);