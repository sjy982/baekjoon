const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
const A = parseInt(inputData[0]);
const B = parseInt(inputData[1]);
const C = parseInt(inputData[2]);

if(A===B && B===C){
    console.log(10000+A*1000);
} else if(A===B || A===C || B===C) {
    if(A===B) {
        console.log(1000+A*100);
    } else if(A===C) {
        console.log(1000+A*100);
    } else if (B===C) {
        console.log(1000+B*100);
    }
} else {
    if(A>B&&A>C) {
        console.log(A*100);
    } else if(B>C&&B>A) {
        console.log(B*100);
    } else if(C>A&&C>B){
        console.log(C*100);
    }
}