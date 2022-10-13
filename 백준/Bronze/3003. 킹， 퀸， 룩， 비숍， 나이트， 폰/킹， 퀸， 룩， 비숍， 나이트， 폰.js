const fs = require('fs');
let [king, queen, look, bishop, night, pawn] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
console.log(`${1-king} ${1-queen} ${2-look} ${2-bishop} ${2-night} ${8-pawn}`);