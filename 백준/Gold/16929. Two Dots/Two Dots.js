const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x => x * 1);
let graph = Array.from(Array(N), () => Array());
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
    }
}
let visited = Array.from(Array(N), () => Array(M).fill(false));
let vx = [0, 1, 0, -1];
let vy = [-1, 0, 1, 0];
let check = false;
let result = [];
for (let i = 0; i < graph.length; i++) {
    for (let j = 0; j < graph[i].length; j++) {
        if (!check) {
            result.push([i, j]);
            visited[i][j] = true;
            DFS();
            result.pop();
            visited[i][j] = false;
        }
    }
}
check ? console.log('Yes') : console.log('No');
function DFS() {
    if (!check) {
        let [y, x] = result[result.length - 1];
        for (let i = 0; i < 4; i++) {
            let nx = x + vx[i];
            let ny = y + vy[i];
            if ((nx >= 0 && nx <= M - 1) && (ny >= 0 && ny <= N - 1)) {
                if (!visited[ny][nx] && graph[y][x] === graph[ny][nx]) {
                    visited[ny][nx] = true;
                    result.push([ny, nx]);
                    DFS();
                    visited[ny][nx] = false;
                    result.pop();
                } else {
                    if (result.length >= 4) {
                        let [ry, rx] = result[result.length - 1];
                        let [fy, fx] = result[0];
                        for (let i = 0; i < 4; i++) {
                            let nrx = rx + vx[i];
                            let nry = ry + vy[i];
                            if ((nrx === fx) && (nry === fy)) {
                                check = true;
                            }
                        }
                    }
                }
            }
        }
    }
}