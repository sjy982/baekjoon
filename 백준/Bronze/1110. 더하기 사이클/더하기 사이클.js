const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let answer ='';
let sycle =0;
if(inputData.length ===1) {
    inputData = `0${inputData[0]}`;
} else {
    inputData = `${inputData[0]}${inputData[1]}`;
}
let inputData2 = inputData.split('');

while(parseInt(inputData) !== parseInt(answer)) {
    sycle +=1;
    let hop = (parseInt(inputData2[0]) + parseInt(inputData2[1])).toString().split('');
    if(hop.length===1){
        answer = `${inputData2[1]}${hop[0]}`;
    } else {
        answer = `${inputData2[1]}${hop[1]}`;
    }
    inputData2 = answer.split('');
}
console.log(sycle);