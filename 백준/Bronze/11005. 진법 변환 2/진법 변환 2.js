const fs = require('fs');
let [N,B] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
console.log(parseInt(N).toString(B).toUpperCase());
