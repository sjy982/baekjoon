const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
let inputData2 = [];
let index = 1;
let answel ='';

for(let j=0; j<repeat; j++) {
    inputData2[j] = [inputData[index],inputData[index+1]];
    index +=2;
}
for(let i=0; i<repeat; i++) {
    let sum = 0;
    let floor = parseInt(inputData2[i][0]);
    let ho = parseInt(inputData2[i][1]);
    let apart = new Array(ho);
    for(let t=0; t<floor+1; t++) {
        //2차원 배열 생성.
        apart[t] = new Array(1);
    }
    for(let b=0; b<floor+1; b++) {
        //2차원 배열 초기화
        for(let c=0; c<ho; c++) {
            //호수만큼 초기화
            apart[b][c] = 0;
        }
    }
    for(let j=0; j<floor+1; j++) {
        for(let k=0; k<ho; k++) {
            if(j===0) {
                //0층이라면
              apart[j][k] = k+1;
            } else {
                //1층부터는 j-1층에 값을 더해줌.
                for(let r=0; r<k+1; r++) {
                    apart[j][k] += apart[j-1][r];
                }
            }
        }
    }
    answel += `${apart[floor][ho-1]}\n`;
}
console.log(answel);
