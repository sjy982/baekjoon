const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let N = parseInt(inputData);
let sum = 1;
let i = 1;
if(N !== 1) {
    while(sum<N) {
    //합이 입력값보다 작다면 반복문 실행
    sum += 6*i;
    i+= 1;
}
console.log(i);
} else {
    console.log(1);
}

