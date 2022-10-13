const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let N = inputData[0];
let r = inputData[1]; //행
let c = inputData[2]; //열
let output = 0;
zNumber(c, r, N);
function zNumber(x, y, n) {
    if((Math.pow(2,n) * Math.pow(2,n)) === 1){
        return;
    } else {
        let sl = Math.pow(2,n)/2; //side length
        let x2 = x;
        let y2 = y;
        if((x>=0 && x<=sl-1) &&(y>=0 && y<=sl-1)) {
            //제1사
        } else if((x>=sl && x<=2*sl-1) && (y>=0 && y<=sl-1)) {
            //제2사
            output += (Math.pow(2,n) * Math.pow(2,n))/4;
            x2 = x-sl;
        } else if((x>=0 && x<=sl-1) && (y>=sl && y<=2*sl-1)) {
            //제3사
            output += (Math.pow(2,n) * Math.pow(2,n))/4*2;
            y2 = y-sl;
        } else if((x>=sl && x<=2*sl-1) && (y>=sl && y<=2*sl-1)) {
            //제4사
            output += (Math.pow(2,n) * Math.pow(2,n))/4*3;
            x2 = x-sl;
            y2 = y-sl;
        }
        zNumber(x2, y2, n-1);
    }
}
console.log(output);