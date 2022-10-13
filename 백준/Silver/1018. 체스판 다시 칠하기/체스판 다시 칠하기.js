const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let x = inputData[0].trim().split(' ')[1]*1;
let y = inputData[0].trim().split(' ')[0]*1;
let case1 = new Array(8); //row 
let case2 = new Array(8);
let answelChess = new Array(8);
let inputChess = new Array(y);
let answel = [];
for(let i=0; i<8; i++) {
    //column
    case1[i] = new Array(8);
    case2[i] = new Array(8);
    answelChess[i] = new Array(8);
}
for(let i=0; i<y; i++) {
    inputChess[i] = new Array(x);
}
case1[0][0] = 'W';
case2[0][0] = 'B';
for(let i=0; i<8; i++) {
    for(let j=0; j<8; j++) {
        if(i!==0 || j!==0) {
            if(case1[i][j-1] === 'W') {
                case1[i][j] = 'B';
                case2[i][j] = 'W';
            } else if(case1[i][j-1] === 'B') {
                case1[i][j] = 'W';
                case2[i][j] = 'B';
            } else if(j===0) {
                if(case1[i-1][0] === 'W') {
                    case1[i][0] = 'B';
                    case2[i][0] = 'W';
                } else if(case1[i-1][0] === 'B') {
                    case1[i][0] = 'W';
                    case2[i][0] = 'B';
                }
            }
        }
    }
}

for(let i=0; i<y; i++) {
    let inputData2 = inputData[i+1].toString().trim().split('');
    for(let j=0; j<x; j++) {
        inputChess[i][j] = inputData2[j];
    }
}
let weightX = 0;
let weightY = 0;
let CCN1 = 0; //change chess Number 1
let CCN2 = 0; //change chess Number 2
for(let i=0; i<y-7; i++) {
    for(let j=0; j<x-7; j++) {
        for(let k=0; k<8; k++) {
            for(let r=0; r<8; r++) {
                answelChess[k][r] = inputChess[k+weightY][r+weightX];
            }
        }
        for(let c=0; c<8; c++) {
            for(let d=0; d<8; d++) {
                if(answelChess[c][d] !== case1[c][d]) {
                    CCN1 += 1;
                }
                if(answelChess[c][d] !== case2[c][d]) {
                    CCN2 += 1;
                }
            }
        }
        if(CCN1 <= CCN2) {
            answel.push(CCN1);
        } else {
            answel.push(CCN2);
        }
        CCN1 = 0;
        CCN2 = 0;
        weightX += 1;
    }
    weightX = 0; //0으로 초기화
    weightY += 1;
}
console.log(Math.min(...answel));