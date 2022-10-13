const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let sum = 0;
inputData.map((ele,index) => {
    sum += ele;
});
let arr = [];
let dtc = sum - 100;
let check = false;
for(let i=0; i<8; i++) {
    for(let j=i+1; j<9; j++) {
        if((inputData[i] + inputData[j]) === dtc) {
            delete inputData[i];
            delete inputData[j];
            check = true;
            break;
        }
    }
    if(check) {
        break;
    }
}
inputData.sort((a,b) => {
    return a-b;
})
let answel = '';
for(let i=0; i<7; i++) {
    answel += `${inputData[i]}\n`;
}
console.log(answel.trim());