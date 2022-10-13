const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let output = '';
for(let i=0; i<N; i++) {
    let inputData2 = inputData[i].split(' ').map(x=>x*1);
    let a = inputData2[0];
    let b = inputData2[1];
    output += `${a*b/findGcf(a,b)}\n`;
}

function findGcf(a,b) {
    let an;
    let bn;
    let r;
    if(a>b) {
        an = a;
        bn = b;
        r = an%bn;
    } else {
        an = b;
        bn = a;
        r = an%bn;
    }
    while(r!==0) {
        an = bn;
        bn = r;
        r = an%bn;
    }
    return bn;
}
console.log(output.trim());