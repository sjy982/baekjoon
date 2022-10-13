const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let graph = Array.from(Array(N), () => Array());
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
    }
}
let visited = Array.from(Array(N), () => Array(N).fill(false));
let result = [];
let answel = [];
for (let i = 0; i < graph.length; i++) {
    for (let j = 0; j < graph[i].length; j++) {
        if (!visited[i][j]) {
            DFS(i, j);
            if (result.length !== 0) {
                answel.push(result.length);
                result = [];
            }
        }
    }
}
answel.sort((a,b) => { return a-b;});
let output = `${answel.length}\n`;
answel.map((ele) => {
    output += `${ele}\n`;
})
console.log(output.trim());
function DFS(y, x) {
    if (graph[y][x] === '0') {
        return;
    } else {
        if (!visited[y][x]) {
            visited[y][x] = true;
            result.push(`${y}${x}`);
            if (y !== 0) {
                DFS(y - 1, x);
            }
            if (y !== N - 1) {
                DFS(y + 1, x);
            }
            if (x !== 0) {
                DFS(y, x - 1);
            }
            if (x !== N - 1) {
                DFS(y, x + 1);
            }
        }
    }
}