const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let A = parseInt(inputData[0]); //고정비용
let B = parseInt(inputData[1]); //가변비용
let C = parseInt(inputData[2]); //판매가

if(B>=C) {
    //가변 비용이 판매가보다 높거나 같다면.
    console.log(-1);
} else {
    if(Number.isInteger(A/(C-B)+1)){
       //정수인경우
       console.log(A/(C-B)+1);
    } else {
       //실수인경우
       console.log(parseInt(A/(C-B)+1));
    }
}
