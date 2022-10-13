const fs= require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x=>x*1);
let input = inputData[1].trim().split(' ').map(x=>x*1);
let result = [];
let answel = '';
input.sort((a,b) => {
    return a-b;
})
DFS();
function DFS() {
    if(result.length === M) {
        answel += `${result.join(' ')}\n`;
        return;
    } else {
        let cn_arr = [];
        for(let i=0; i<N; i++) {
            let cn = cn_arr.length !==0 ? cn_arr[cn_arr.length - 1] : undefined;
            if(cn !== input[i]) {
                result.push(input[i]);
                DFS();
                result.pop();
                cn_arr.push(input[i]);
            }
        }
    }
}
console.log(answel.trim());