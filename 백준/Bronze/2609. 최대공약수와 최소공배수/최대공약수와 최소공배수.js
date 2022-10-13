const fs = require('fs');
let [A,B] = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let gcf = findGcf(A,B);
let lcm = (A*B)/gcf;
console.log(`${gcf}\n${lcm}`);
function findGcf(a,b) {
    //최대공약수.
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
        r= an%bn;
    }
    while(r!==0) {
        an = bn;
        bn = r;
        r = an%bn;
    }
    return bn;
}