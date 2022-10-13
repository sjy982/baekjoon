const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().split('\n');
let output = '';
let arr = [];
for(let i=0; i<inputData.length; i++) {
    let inputData2 = inputData[i].split('');
    arr = [0,0,0,0];
    if(inputData[i] !== '') {
         for(let j=0; j<inputData2.length; j++) {
        if(inputData2[j].charCodeAt(0)>=97 && inputData2[j].charCodeAt(0)<=122) {
            arr[0] += 1;
        } else if(inputData2[j].charCodeAt(0)>=65 && inputData2[j].charCodeAt(0)<=90) {
            arr[1] += 1;
        } else if(inputData2[j] === ' ') {
            arr[3] += 1;
        } else {
            //숫자
            arr[2] += 1;
        }
    }
    output += `${arr[0]} ${arr[1]} ${arr[2]} ${arr[3]}\n`;
    arr = [];
    }
}
console.log(output.trim());