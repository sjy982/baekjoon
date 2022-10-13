const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x => x * 1);
let input = Array.from(Array(M), () => Array());
let graph_list = Array.from(Array(N), () => Array());
let visited = Array.from(Array(N)).fill(false);
for (let i = 1; i < inputData.length; i++) {
    let data = inputData[i].trim().split(' ');
    for (let j = 0; j < data.length; j++) {
        input[i - 1][j] = data[j]
    }
}
for (let [a, b] of input) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}

let check = false;
DFS(0);
if(check) {
    console.log(1);
} else {
    console.log(0);
}
function DFS(cout, ind) {
    if (cout === 4) {
        check = true;
        return;
    } else if (!check) {
        if (cout === 0) {
            for (let i = 0; i < graph_list.length; i++) {
                for (let j = 0; j < graph_list[i].length; j++) {
                    visited[i] = true;
                    DFS(cout + 1, graph_list[i][j]);
                    visited[i] = false;
                }
            }
        } else {
            for (let i = 0; i < graph_list[ind].length; i++) {
                if (!visited[graph_list[ind][i]]) {
                    visited[ind] = true;
                    DFS(cout + 1, graph_list[ind][i]);
                    visited[ind] = false;
                }
            }
        }
    }
}