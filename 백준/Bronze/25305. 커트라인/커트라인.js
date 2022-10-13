const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim().split(' ')[0]*1;
let k = inputData[0].trim().split(' ')[1]*1;
let score = inputData[1].trim().split(' ').map(x=>x*1);
score.sort((a,b)=>{return b-a});
console.log(score[k-1]);