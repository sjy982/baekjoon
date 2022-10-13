const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let H = parseInt(inputData[0]);
let M = parseInt(inputData[1]);
if(M < 45) {
    if(H === 0) {
        H = 23;
    } else {
        H = H-1;
    }
    let M2 = 45-M;
    M = 60-M2;
} else {
    M = M-45;
}
console.log(`${H} ${M}`);