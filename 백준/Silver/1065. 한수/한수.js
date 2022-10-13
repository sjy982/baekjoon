const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
const N = parseInt(inputData);

console.log(searchHansu(N));
            
function searchHansu(N) {
    let count=0;
    if(N<=99 && N>=1) {
        count=N;
    } else {
        //여기부턴 3자리 이상
        count = 99;
        for(let i=100; i<=N; i++) {
            let hansu = i.toString().split('');
            let length = hansu.length;
            let gongcha = parseInt(hansu[1]) - parseInt(hansu[0]);
            let boolHansu = true;
            for(let j=1; j<length-1; j++) {
                if((gongcha === parseInt(hansu[j+1])-parseInt(hansu[j]))&&boolHansu) {
                    boolHansu = true;
                    gongcha = parseInt(hansu[j+1])-parseInt(hansu[j]);
                } else {
                    boolHansu = false;
                }
            }
            if(boolHansu) {
                count += 1;
            }
        }
    }
    return count;
}