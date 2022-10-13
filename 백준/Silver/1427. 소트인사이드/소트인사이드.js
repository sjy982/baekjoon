const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim().split('').map(x=>x*1);
N.sort((a,b) => {return b-a});
console.log(N.join(''));