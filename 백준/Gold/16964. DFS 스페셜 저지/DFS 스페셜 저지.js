const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let graph = Array.from(Array(N-1),()=>Array());
let graph_list = Array.from(Array(N+1), () => Array());
let visited = new Array(N+1).fill(false);
for(let i=1; i<inputData.length-1; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j];
    }
}
for(let [a,b] of graph) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}
let nCheck = inputData[inputData.length-1].trim().split(' ').map(x=>x*1);
let output = 0;
let end = false;
let pi = 1; //값을 넣어야 할 인덱스 값.
if(nCheck[0] === 1) {
    visited[1] = true;
    DFS(1);
} 
console.log(output);
function DFS(n) {
    if(pi===N) {
        output = 1;
        end = true;
        return;
    } else if(!end){
        let nArr = [];
        for(let i=0; i<graph_list[n].length; i++) {
            if(!visited[graph_list[n][i]]) {
                nArr[graph_list[n][i]] = true;
            }
        }
        for(let i=0; i<graph_list[n].length; i++) {
            if(nArr[nCheck[pi]] !== undefined) {
                pi += 1;
                visited[nCheck[pi-1]] = true;
                DFS(nCheck[pi-1]);
            } else {
                let allVisited = true;
                for(let j=0; j<graph_list[n].length; j++) {
                    if(!visited[graph_list[n][j]]) {
                        allVisited = false;
                        break;
                    }
                }
                if(!allVisited) {
                    output = 0;
                    end = true;
                    return;
                }
            }
        }
    }
}