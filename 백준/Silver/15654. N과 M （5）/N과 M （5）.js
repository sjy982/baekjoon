const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N,M] = inputData[0].split(' ').map(x=>x*1);
let input = inputData[1].split(' ').map(x=>x*1);
input.sort((a,b) => {
    return a-b;
})
let result = [];
let answel = '';
DFS();
function DFS() {
    if(result.length === M) {
        answel += `${result.join(' ')}\n`;
        return;
    } else {
        for(let i=0; i<N; i++) {
            if(!result.includes(input[i])) {
                result.push(input[i]);
                DFS(); //하위 노드로 이동.
                result.pop(); //이전 노드로 복귀
            }
        }
    }
}
console.log(answel.trim());