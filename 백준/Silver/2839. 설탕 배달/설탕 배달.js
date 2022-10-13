const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let N = parseInt(inputData);
let sum = 0;
let mok = N/5;

if(Number.isInteger(mok)) {
    //몫이 딱 떨어진다면
    sum = mok;
} else {
    //실수이면 소수 버리고 정수로 만듬.
    mok = parseInt(mok);
    if(mok===0) {
       if(N%3===0) {
           sum = N/3;
       }else {
           sum = -1;
       }
    } else {
        //N이 5보다 큰거임.
        let threeKG = false;
        for(let i=mok; i>=1; i--) {
            if((N-(i*5))%3===0) {
                sum = i+(N-(i*5))/3;
                threeKG = false;
                break;
            } else {
                threeKG = true;
            } 
        }
        if(threeKG) {
            //3kg체크하면됨.
            if(N%3===0) {
                sum = N/3;
            } else {
                sum = -1;
            }
        }
    }
}
console.log(sum);