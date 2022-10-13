const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const lowerData = inputData.toLowerCase();
const arr = lowerData.split('');
const length = arr.length;
const alphabet = [];
const arr2 = [];
for(let j=97; j<=122; j++) {
    alphabet.push(String.fromCharCode(j));
    arr2.push(0);
}
for(let i=0; i<length; i++) {
    const index = alphabet.indexOf(arr[i]);
    arr2[index] += 1;
}
let max = Math.max(...arr2);
let maxIndex = arr2.indexOf(max);
let maxLastIndex = arr2.lastIndexOf(max);
if(maxIndex === maxLastIndex) {
    //중복값 없음
    console.log(alphabet[maxIndex].toUpperCase());
} else {
    //중복값 있음
    console.log("?");
}