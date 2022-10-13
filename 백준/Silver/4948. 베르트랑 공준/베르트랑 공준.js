const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let max = Math.max(...inputData);
let end = 2*max;
let decimalArray = new Array(end+1).fill(true);
decimalArray.splice(0,0,false,false);
for(let i=2; i*i<=end; i++) {
    for(let j=i*i; j<=end; j+=i) {
        decimalArray[j] = false;
    }
}
let answel = '';
for(let i=0; i<inputData.length; i++) {
    let N = inputData[i];
    let M = 2*inputData[i];
    let sum = 0;
    for(let j=N+1; j<=M; j++ ){
        if(decimalArray[j]) {
            sum += 1;
        }
    }
    if(N!==0) {
        answel += `${sum}\n`;
    }
}
console.log(answel);
