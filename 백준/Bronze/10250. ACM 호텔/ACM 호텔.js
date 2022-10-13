const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);

for(let i=0; i<repeat; i++) {
    //총 주어진 케이스를 반복문으로 돌린다.
    const inputData2 = inputData[i+1].split(' ');
    const H = parseInt(inputData2[0]);
    const W = parseInt(inputData2[1]);
    const N = parseInt(inputData2[2]);
    //호수부터 찾으면
    let ho = N/H;
    if(!Number.isInteger(ho)) {
        //호수가 소수라면 올림해주고 정수이면 아무런 처리 해주지 않는다.
        ho = parseInt(ho) + 1;
    }
    if(ho<10){
        ho = `0${ho}`;
    }
    let floor = N-(H*ho-(H-1))+1;
    console.log(`${floor}${ho}`);
}