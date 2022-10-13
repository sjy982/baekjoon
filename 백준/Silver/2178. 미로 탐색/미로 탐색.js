const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x => x * 1);
let graph = Array.from(Array(N), () => Array());
let visited = Array.from(Array(N), () => Array(M).fill(false));
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('').map(x => x * 1);
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
    }
}
//start [y,x]
BFS([0,0]);
console.log(graph[N-1][M-1]);
function BFS(start) {
    let que = [];
    que.push(start);
    visited[start[0]][start[1]] = true;
    let vy = [-1, 0, 0, 1];
    let vx = [0, -1, 1, 0];
    while (que.length !== 0) {
        if (visited[N - 1][M - 1]) {
            return;
        } else {
            let [y, x] = que.shift();
            for (let i = 0; i < 4; i++) {
                let ny = y + vy[i];
                let nx = x + vx[i];
                if ((ny >= 0 && ny <= N - 1) && (nx >= 0 && nx <= M - 1)) {
                    if (!visited[ny][nx] && graph[ny][nx] !== 0) {
                        graph[ny][nx] += graph[y][x];
                        visited[ny][nx] = true;
                        que.push([ny, nx]);
                    }
                }
            }
        }
    }
}