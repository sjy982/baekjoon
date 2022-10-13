const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift();
let output = ''
for(let i=0; i<N; i++) {
    let vpsArr = inputData[i].split('');
    let rpa = []; //오른쪽 괄호
    let end = false;
    while(!end) {
        if(vpsArr[vpsArr.length-1] === ')') {
            rpa.push(vpsArr.pop());
        } else if(vpsArr[vpsArr.length-1] === '(') {
            if(rpa.length === 0) {
                output += 'NO\n';
                end =true;
            } else {
                vpsArr.pop();
                rpa.pop();
            }
        } else if(vpsArr.length === 0 && rpa.length === 0) {
            output += 'YES\n';
            end = true;
        } else {
            output += 'NO\n';
            end = true;
        }
    }
}
console.log(output.trim());