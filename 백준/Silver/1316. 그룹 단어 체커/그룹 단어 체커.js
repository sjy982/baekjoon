const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const repeat = parseInt(inputData[0]);
let groupword = true;
let count = 0;
for(let i=1; i<=repeat; i++) {
    groupword = true;
    const word = inputData[i].split(''); //word
    for(let j=0; j<word.length-2; j++) {
        for(let k=0; k<word.length; k++) {
            if(groupword !== false) {
                if(word[j]!==word[j+1]){
                //연속된 문자열이 없으면 뒤에 똑같은 문자가 있는지 확인해야됨.
                    if(k>j+1) {
                    word[j] === word[k] ? groupword=false : groupword=true;
                    } 
                } else {
                //연속된 문자열이면 다음 문자로 넘어감.
                break;
                }                
            }
        }
    }
    if(groupword) {
        count +=1;
    }
}
console.log(count);