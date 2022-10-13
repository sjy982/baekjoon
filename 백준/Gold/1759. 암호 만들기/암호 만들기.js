const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [L, C] = inputData[0].split(' ').map(x=>x*1);
let input = inputData[1].split(' ');
input.sort();
let vow = ['a','e','i','o','u']; //알파벳 모음
let result = [];
let answel = '';
DFS();
function DFS() {
    if(result.length === L) {
        let vowels = 0; //모음 하나 이상
        let conso = 0; //자음 두개 이상
        let check = false;
        for(let i=0; i<L; i++) {
            if(vow.includes(result[i])) {
                vowels += 1;
            } else {
                conso += 1;
            }
            if(vowels >=1 && conso >=2) {
                check = true;
                break;
            }
        }
        if(check) {
            answel += `${result.join('')}\n`;
        }
        return;
    } else {
        for(let i=0; i<C; i++) {
            if(!result.includes(input[i])){
                if(result.length === 0) {
                    result.push(input[i]);
                    DFS();
                    result.pop();
                } else {
                    if(input[i] > result[result.length-1]) {
                        result.push(input[i]);
                        DFS();
                        result.pop();
                    }
                }
            }
        }
    }
}
console.log(answel.trim());