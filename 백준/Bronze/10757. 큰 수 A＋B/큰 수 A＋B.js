const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let answer = (BigInt(inputData[0]) + BigInt(inputData[1])).toString(); //BigInt는 \n이 같이 붙는다고 한다. toString() \n을 지워줌
console.log(answer);