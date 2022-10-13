const fs = require('fs');
let [start, end] = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let decimalArray = new Array(end+1).fill(true);
decimalArray.splice(0,2,false,false);
for(let i=2; i*i<=end; i++) {
    for(let j=i*i; j<=end; j+=i) {
        decimalArray[j]=false;
    }
}

let sum = 0;
let min_decimal = [];
for(let i=start; i<=end; i++) {
    if(decimalArray[i]===true) {
        min_decimal.push(i);
        sum += i;
    }
}
if(sum ===0) {
    console.log(-1);
} else {
    console.log(`${sum}\n${Math.min(...min_decimal)}`);
}
