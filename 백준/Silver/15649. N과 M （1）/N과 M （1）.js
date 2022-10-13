const fs = require('fs');
let [N, M] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let answel = '';
let result = [];
function DFS() {
    if(result.length === M) {
        //리프 노드의 도착했다면 종료 조건임.
        answel += `${result.join(' ')}\n`;
        return ;
    } else {
        for(let i=1; i<=N; i++) {
            if(!result.includes(i)) {
                //중복이 아니라면
                result.push(i); //요소 추가
                DFS(); //하위 노드로 이동.
                result.pop(); //이전 노드로 복귀한다.
            }
        }
    }
}
DFS();
console.log(answel);