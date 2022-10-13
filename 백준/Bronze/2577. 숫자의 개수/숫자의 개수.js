const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let result =  (parseInt(inputData[0])*parseInt(inputData[1])*parseInt(inputData[2])).toString().split('');
let answalArray = [0,0,0,0,0,0,0,0,0,0];
let answal='';
for(let i=0; i<result.length; i++) {
    if(parseInt(result[i])===0) {
        answalArray[0] += 1;
    }else if(parseInt(result[i])===1) {
        answalArray[1] += 1;
    }else if(parseInt(result[i])===2) {
        answalArray[2] += 1;
    }else if(parseInt(result[i])===3) {
        answalArray[3] += 1;
    }else if(parseInt(result[i])===4) {
        answalArray[4] += 1;
    }else if(parseInt(result[i])===5) {
        answalArray[5] += 1;
    }else if(parseInt(result[i])===6) {
        answalArray[6] += 1;
    }else if(parseInt(result[i])===7) {
        answalArray[7] += 1;
    }else if(parseInt(result[i])===8) {
        answalArray[8] += 1;
    }else if(parseInt(result[i])===9) {
        answalArray[9] += 1;
    }
}
for(let j=0; j<answalArray.length; j++) {
    answal += `${answalArray[j]}\n`;
}
console.log(answal)