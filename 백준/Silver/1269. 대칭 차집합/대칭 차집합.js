const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let NM = inputData.shift().split(' ').map(x=> x*1);
let N = NM[0];
let M = NM[1];
let Amap = new Map();
let Bmap = new Map();
let inputA = inputData[0].split(' ').map(x=>x*1);
let inputB = inputData[1].split(' ').map(x=>x*1);
for(let i=0; i<N; i++) {
    Amap.set(inputA[i], true);
}
for(let i=0; i<M; i++) {
    Bmap.set(inputB[i], true);
}
let lenA = N;
let lenB = M;

Amap.forEach((value,key) => {
   if(Bmap.has(key)) {
       lenA -= 1;
   } 
});

Bmap.forEach((value,key) => {
    if(Amap.has(key)) {
        lenB -= 1;
    }
});

console.log(lenA+lenB);