const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
const T = inputData[0];
const decimalArray = new Array(10001).fill(true);
decimalArray.splice(0,2,false,false);
for(let i=2; i*i<=10001; i++) {
    for(let j=i*i; j<=10001; j+=i) {
        decimalArray[j] = false;
    }
}
let answel = '';
for(let i=1; i<=T; i++) {
    let N = inputData[i];
    let decimal = [];
    let min = N;
    let answel2 = '';
    let zero = false;
    for(let j=2; j<N; j++) {
        if(decimalArray[j]) {
            decimal.push(j);
        }
    }
   for(let i=0; i<decimal.length; i++) {
         if(decimalArray[N-decimal[i]]) {
             //소수인 경우만 그러면 무조건 소수+소수는 = 2의배수 이기 때문에 이제 차이값이 제일 적은경우만 answel변수에 넣어주면됨.
             if(decimal[i] === (N-decimal[i])) {
                 //차이값이 0인경우임.
                  min = 0;
                 answel2 = `${decimal[i]} ${N-decimal[i]}`;
             } else {
                 //차이값이 0보다 큰경우
                 if(Math.abs(decimal[i]-(N-decimal[i]))<min) {
                 //차이값이 더 작다면 min값을 갱신해준다.
                 min = Math.abs(decimal[i]-(N-decimal[i]));
                 answel2 = `${decimal[i]} ${N-decimal[i]}`;
             }
             }
         }
   }
   answel += `${answel2}\n`;
}
console.log(answel);
