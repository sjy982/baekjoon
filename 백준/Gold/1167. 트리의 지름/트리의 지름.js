const fs = require('fs');
let inputData =fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let tree_list = Array.from(Array(N+1), () => Array());
let tree_dtc = Array.from(Array(N+1), () => Array()); //서로 다른 정점끼리의 거리.
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    let a = input[0];
    let j=1;
    while(input[j] !== -1) {
        if(j%2 === 1) {
            tree_list[a].push(input[j]);
        } else {
            tree_dtc[a][input[j-1]] = input[j];
        }
        j+=1;
    }
}
let max_obj = {node: 0, dtc: 0};
let visited = new Array(N+1).fill(false);
visited[1] = true;
DFS(1,0)
visited[1] = false;
visited[max_obj.node] = true;
DFS(max_obj.node,0);
console.log(max_obj.dtc);

function DFS(n, sum) {
    if(sum > max_obj.dtc) {
        max_obj = {node: n, dtc: sum};
    }
    for(let i=0; i<tree_list[n].length; i++) {
        if(!visited[tree_list[n][i]]) {
            visited[tree_list[n][i]] = true;
            DFS(tree_list[n][i], sum + tree_dtc[n][tree_list[n][i]]);
            visited[tree_list[n][i]] = false;
        }
    }
}