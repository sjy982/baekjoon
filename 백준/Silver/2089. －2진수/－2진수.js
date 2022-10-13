const fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let answel = '';
//양수면 Math.floor()버림 음수면 Math.round로 버림
while(true) {
    let mok = input/-2;
    mok < 0 ? mok = Math.round(mok) : mok = Math.floor(mok);
    let rmd = input % -2; //나머지
    input = mok;
    if(rmd < 0) {
        //나머지가 음수이면 양수로 표현해주기 위해서
        input += 1;
        rmd = 1;
    }
    answel = `${rmd}${answel}`;
    if(input === 0) {
        break;
    }
}
console.log(answel);