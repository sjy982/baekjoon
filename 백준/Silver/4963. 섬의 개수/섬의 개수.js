const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [w, h] = inputData.shift().trim().split(' ').map(x => x * 1);
let graph;
let visited;
let result = [];
let answel = '';
while (w !== 0 || h !== 0) {
    graph = Array.from(Array(h), () => Array());
    visited = Array.from(Array(h), () => Array(w).fill(false));
    for (let i = 0; i < h; i++) {
        let input = inputData.shift().trim().split(' ');
        for (let j = 0; j < input.length; j++) {
            graph[i][j] = input[j];
        }
    }
    let cout = 0;
    for (let i = 0; i < graph.length; i++) {
        for (let j = 0; j < graph[i].length; j++) {
            if (!visited[i][j] && graph[i][j] === '1') {
                DFS(i, j);
                if (result.length > 0) {
                    cout += 1;
                }
                result = [];
            }
        }
    }
    answel += `${cout}\n`;
    
    let [nw, nh] = inputData.shift().trim().split(' ').map(x => x * 1);
    w = nw;
    h = nh;
}
console.log(answel.trim());
function DFS(y, x) {
    if(y < 0 || y >= h || x < 0 || x >= w ) {
        return;
    } else {
        if(graph[y][x] === '1') {
            if(!visited[y][x]) {
                visited[y][x] = true;
                result.push(`${y}${x}`);
                for(let i= y-1; i<=y+1; i++) {
                    for(let j=x-1; j<=x+1; j++) {
                        if(i!==y || j!==x) {
                            DFS(i, j);
                        }
                    }
                }
            }
        }
    }
}