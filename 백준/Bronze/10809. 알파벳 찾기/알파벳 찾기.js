const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let answer = '';
for(let i=97; i<=122; i++) {
    const searchData = String.fromCharCode(i);
    answer += `${inputData.indexOf(searchData)} `;
}
console.log(answer);
