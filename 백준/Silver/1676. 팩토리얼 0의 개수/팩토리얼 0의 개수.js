const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let arr = [];
let answel = BigInt(1);
for(let i=1; i<=N; i++) {
    answel *= BigInt(i);
}
let count = 0;
arr = answel.toString().split('');
for(let i=arr.length-1; i>=0; i--) {
    if(arr[i]!=='0') {
        break;
    } else {
        count += 1;
    }
}
console.log(count);