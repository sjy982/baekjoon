const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString()*1;
let answelArray = [];
for(let i=N; i>=0; i--) {
    let m = i;
    let mArray = m.toString().split('').map(x => x*1);
    let checkNumber = m;
    for(let j=0; j<mArray.length; j++) {
        checkNumber += mArray[j];
    }
    if(checkNumber === N) {
        answelArray.push(m);
    }
}
console.log(answelArray.length === 0 ? 0 : Math.min(...answelArray));