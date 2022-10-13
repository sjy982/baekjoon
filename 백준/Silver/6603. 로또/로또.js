const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let answel = '';
let result = [];
for(let i=0; i<inputData.length-1; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    let N = input.shift();
    DFS(N,input);
    answel += '\n';
}
function DFS(N, input) {
    if(result.length === 6) {
        answel += `${result.join(' ')}\n`;
        return; 
    } else {
        for(let i=0; i<N; i++) {
            let result_top = result.length === 0 ? -1 : result[result.length-1];
            if(!result.includes(input[i]) && result_top < input[i]) {
                result.push(input[i]);
                DFS(N,input);
                result.pop();
            }
        }
    }
}
console.log(answel.trim());