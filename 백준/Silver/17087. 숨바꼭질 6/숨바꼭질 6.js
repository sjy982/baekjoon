const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, S] = inputData.shift().split(' ').map(x => x * 1);
let inputData2 = inputData[0].split(' ').map(x => x * 1);
let D, fn, sn;
let i = 0;
if (N === 1) {
    console.log(Math.abs(inputData2[0] - S));
} else {
    while (i !== N) {
        if (i === 0) {
            fn = Math.abs(inputData2[i] - S);
            sn = Math.abs(inputData2[i + 1] - S);
            i += 2;
        } else {
            fn = D;
            sn = Math.abs(inputData2[i] - S);
            i += 1;
        }
        D = findGcd(fn, sn);
    }
    console.log(D);
}
function findGcd(a, b) {
    let r;
    if (a > b) {
        r = a % b
    } else {
        let tem;
        tem = a;
        a = b;
        b = tem;
        r = a % b;
    }
    while (r !== 0) {
        a = b;
        b = r;
        r = a % b;
    }
    return b;
}