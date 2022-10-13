const fs = require('fs');
let [N,B] = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
console.log(parseInt(N,B));