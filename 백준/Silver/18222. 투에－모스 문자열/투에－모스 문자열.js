const fs = require('fs');
let K = fs.readFileSync('/dev/stdin').toString().trim();
let X = [0, 1, 1, 0];
findNumber(BigInt(K),false);
function findNumber(k,t) {
    if (k <= 4) {
        if(t) {
            if(X[k-BigInt(1)] === 1) {
                console.log(0);
            } else {
                console.log(1);
            }
        } else {
            console.log(X[k-BigInt(1)]);
        }
    } else {
        let end = false;
        let togle = !t;
        let k2 = BigInt(k);
        let n = BigInt(2);
        while (!end) {
            if (k2 <= n) {
                end = true;
            } else {
                n *= BigInt(2);
            }
        }
        n = n/BigInt(2);
        k2 = BigInt(k)-BigInt(n);
        findNumber(BigInt(k2), togle);
    }
}

