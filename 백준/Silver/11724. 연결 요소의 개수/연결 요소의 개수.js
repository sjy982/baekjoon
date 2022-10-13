const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N,M] = inputData[0].trim().split(' ').map(x=>x*1);
let graph = Array.from(Array(M), () => Array());
let graph_list = Array.from(Array(N+1), () => Array());
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j];
    }
}
for(let [a,b] of graph) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}
let visited = Array.from(Array(N+1)).fill(false);
let result = [];
let answel = [];
for(let i=1; i<=N; i++) {
    if(!visited[i]) {
        BFS(i)
        answel.push(result);
        result = [];
    }
}
//bfs로
console.log(answel.length);
function BFS(n) {
    let que = [n];
    visited[n] = true;
    while(que.length !== 0) {
        let v = que.shift();
        result.push(v);
        for(let i=0; i<graph_list[v].length; i++) {
            if(!visited[graph_list[v][i]]) {
                que.push(graph_list[v][i]);
                visited[graph_list[v][i]] = true;
            }
        }
    }
}