const fs = require('fs');
let [n, m] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x => x * 1);
let r = n - m;
let fac = [n, m, r];
//n!5의 개수 - (!r의 5의 개수 + !m의 5의 개수);
let coutArr = [];
for (let i = 0; i < fac.length; i++) {
    let cout = 0;
    let cout2 = 0;
    let end = false;
    let end2 = false;
    while (!end || !end2) {
        if(!end) {
            if (fac[i] >= Math.pow(5, cout)) {
                cout += 1;
            } else {
                cout -= 1;
                 end = true;
            }
        }
        if(!end2) {
            if(fac[i] >= Math.pow(2, cout2)) {
                cout2 += 1;
            } else {
                cout2 -= 1;
                end2 = true;
            }
        }
    }
    let coutZero = 0;
    let coutZero2 = 0;
    for(let j = 1; j <= cout; j++) {
        coutZero += Math.floor(fac[i] / Math.pow(5, j));
    }
    for(let j=1; j<=cout2; j++) {
        coutZero2 += Math.floor(fac[i]/Math.pow(2,j));
    }
    coutArr[i] = {two: coutZero2, five: coutZero};
}
let twoCout = coutArr[1].two + coutArr[2].two;
let fiveCout = coutArr[1].five + coutArr[2].five;
twoCout = coutArr[0].two - twoCout;
fiveCout = coutArr[0].five - fiveCout;
twoCout >= fiveCout ? console.log(fiveCout) : console.log(twoCout);