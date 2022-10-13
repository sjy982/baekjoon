const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let cantoStr = new Array(inputData.length);
let output = '';
for(let i=0; i<inputData.length; i++) {
    cantoStr[i] = new Array(Math.pow(3,inputData[i]));
}
for(let i=0; i<inputData.length; i++) {
    cantoStr[i].fill('-');
}
for(let i=0; i<inputData.length; i++) {
    cantorSet(0, cantoStr[i].length-1, i);
}
//길이 구하기 end-start+1
function cantorSet(start,end, n) {
    let k = end-start+1; //length
    if(k===1) {
        //base case
        return;
    } else {
        let kn = k/3;
        for(let i=start+kn; i<start+kn*2; i++) {
            cantoStr[n][i] = ' ';
        }
        cantorSet(start, start+kn-1, n);
        cantorSet(start+kn*2, start+kn*2+kn-1, n);
    }
}
for(let i=0; i<inputData.length; i++) {
    output += `${cantoStr[i].join('')}\n`;
}
console.log(output.trim());