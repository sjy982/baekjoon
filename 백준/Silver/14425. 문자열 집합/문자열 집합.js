const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let NM = inputData.shift().split(' ').map(x=>x*1);
let N = NM[0];
let M = NM[1];
let wordMap = new Map();
let checkWord = [];
for(let i=0; i<inputData.length; i++) {
    if(i<N) {
        wordMap.set(inputData[i], true);
    } else {
        checkWord.push(inputData[i]);
    }
}
let count = 0;
checkWord.map((ele, index) => {
   if(wordMap.get(ele)) {
       count += 1;
   } 
});
console.log(count);