const fs= require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
const inputData2 = inputData[1].split(' ');
let sum = 0;
//에라토스테네스의 체 알고리즘 사용. => 대량으로 소수를 판별하는 알고리즘이다.
let decimal = new Array(1001).fill(true);
decimal.splice(0,2,false,false);
for(let i=2; i*i<=1001; i++) {
    for(let j=i*i; j<=1001; j+=i) {
        decimal[j] = false;
    }
}

for(let b=0; b<repeat; b++) {
    let checkNumber = parseInt(inputData2[b]);
    if(decimal[checkNumber]) {
        sum += 1;
    }
}

console.log(sum);

