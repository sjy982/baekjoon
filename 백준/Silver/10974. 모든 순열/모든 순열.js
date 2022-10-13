const fs= require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let result = [];
let answel = '';
DFS();
function DFS() {
    if(result.length === N) {
        answel += `${result.join(' ')}\n`;
        return;
    } else {
        for(let i=1; i<=N; i++) {
            if(!result.includes(i)) {
                result.push(i);
                DFS();
                result.pop();
            }
        }
    }
}
console.log(answel.trim());