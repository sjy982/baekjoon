const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].split(' ').map(x=>x*1);
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
        return ;
    } else {
        for(let i=0; i<N; i++) {
            result.push(input[i]);
            DFS();
            result.pop();
        }
    }
}
console.log(answel.trim());