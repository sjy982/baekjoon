const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let tree_list = Array.from(Array(N+1), () => Array());
let tree_v = Array.from(Array(N+1), () => Array());
let max_node = new Array(N+1).fill(0);
let root = new Array(N+1).fill(true);

for(let i=1; i<inputData.length; i++) {
    let node = inputData[i].trim().split(' ').map(x=>x*1);
    tree_list[node[0]].push(node[1]);
    tree_v[node[0]][node[1]] = node[2];
    root[node[1]] = false;
}

let root_node;
for(let i=1; i<root.length; i++) {
    if(root[i]) {
        root_node = i;
        break;
    }
}
let answel = [];
if(N === 1) {
    console.log(0);
} else {
    DFS(root_node);
    console.log(Math.max(...answel));
}

function DFS(n) {
    if(tree_list[n].length === 0) {
        return;
    } else {
        let result = [];
        for(let i=0; i < tree_list[n].length; i++) {
            DFS(tree_list[n][i]);
            result.push(tree_v[n][tree_list[n][i]] + max_node[tree_list[n][i]]);
        }
        result.sort((a,b) => {
            return b-a;
        })
        max_node[n] = result[0];
        result.length >=2 ? answel.push(result[0] + result[1]) : answel.push(result[0]);
    }
}