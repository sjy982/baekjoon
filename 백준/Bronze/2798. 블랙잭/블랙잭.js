const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().split('\n');
let N = inputData[0].split(' ')[0]*1;
let M = inputData[0].split(' ')[1]*1;
let number = inputData[1].split(' ').map(x => x*1);
let answel = [];
for(let i=0; i<N; i++) {
    for(let j=i+1; j<N; j++) {
        for(let k=j+1; k<N; k++) {
            if(number[i] + number[j] + number[k] <= M) {
                answel.push(number[i] + number[j] + number[k]);
            }
        }
    }
}
console.log(Math.max(...answel));