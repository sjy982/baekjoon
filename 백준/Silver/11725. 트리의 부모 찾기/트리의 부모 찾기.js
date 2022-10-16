const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let tree = Array.from(Array(N-1),() => Array());
let tree_list = Array.from(Array(N+1), () => Array());
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        tree[i-1][j] = input[j];
    }
}

for(let [a,b] of tree) {
    tree_list[a].push(b);
    tree_list[b].push(a);
}
let visited = new Array(N+1).fill(-1);
visited[0] = 0;
visited[1] = 0;
DFS(1);
let answel = '';
for(let i=2; i<visited.length; i++) {
    answel += `${visited[i]}\n`;
}
console.log(answel.trim());

function DFS(n) {
    for(let i=0; i<tree_list[n].length; i++) {
        if(visited[tree_list[n][i]] === -1) {
            visited[tree_list[n][i]] = n;
            DFS(tree_list[n][i]);
        }
    }
}