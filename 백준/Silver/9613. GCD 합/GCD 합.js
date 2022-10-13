const fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let output = '';
for(let i=0; i<N; i++) {
    let inputData2 = inputData[i].split(' ').map(x=>x*1);
    let N2 = inputData2.shift();
    let sum = 0;
    for(let j=0; j<N2; j++) {
        for(let k=j+1; k<N2; k++) {
            sum += findGcd(inputData2[j], inputData2[k]);
        }
    }
    output += `${sum}\n`;
}
console.log(output.trim());
function findGcd(a,b) {
    let r;
    if(a>b) {
        r = a%b;
    } else {
        let tem;
        tem = a;
        a = b;
        b = tem;
        r = a%b;
    }
    while(r!==0) {
        a = b;
        b = r;
        r = a%b;
    }
    return b;
}