const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let A = parseInt(inputData[0]);
let B = parseInt(inputData[1]);
let V = parseInt(inputData[2]);
let day = 0;
if(Number.isInteger(V/(A-B))){
    //정수이면
    day = V/(A-B);
}else {
    //실수이면
    day = parseInt(V/(A-B)) + 1;
}

while((day-1)*(A-B)+ A>=V) {
   day-= 1;
}
day += 1;
console.log(day);
