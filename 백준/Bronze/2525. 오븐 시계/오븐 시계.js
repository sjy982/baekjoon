const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let H = parseInt(inputData[0]);
const inputData2 = inputData[1].trim().split('\n');
let M = parseInt(inputData2[0]);
let RM = parseInt(inputData2[1]);
if(M + RM >= 60) {
    let H2 = parseInt((M+RM)/60);
    M = (M+RM)%60;
    if(H + H2 >= 24) {
        H = (H+H2 -24);
    } else {
        H = H+H2;
    }
} else {
    M = M+RM;
}
console.log(`${H} ${M}`);