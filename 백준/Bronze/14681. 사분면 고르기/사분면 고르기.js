const fs = require('fs');
const inputData = fs.readFileSync(0).toString().trim().split('\n');
const A = Number(inputData[0]);
const B = Number(inputData[1]);
if(A>0 && B>0) {
    console.log('1');
}else if(A<0 && B>0) {
    console.log('2');
}else if(A<0 && B<0) {
    console.log('3');
}else if(A>0 && B<0) {
    console.log('4');
}