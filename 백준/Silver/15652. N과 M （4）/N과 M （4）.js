const fs = require('fs');
let [N,M] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let result = [];
let answel = '';
DFS();
function DFS() {
    if(result.length === M) {
        answel += `${result.join(' ')}\n`;
        return;
    } else {
        for(let i=1; i<=N; i++) {
            if(result.length === 0) {
                result.push(i);
                DFS();
                result.pop();
            } else {
                if(result[result.length - 1] <= i) {
                    result.push(i);
                    DFS();
                    result.pop();
                }
            }
        }
    }
}
console.log(answel.trim());