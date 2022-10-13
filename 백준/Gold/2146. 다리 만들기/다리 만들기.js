class Queue {
    constructor() {
        this.storage = {};
        this.front = 0;
        this.rear = -1;
    }
    size() {
        if(this.front > this.rear) {
            return 0;
        } else {
            return this.rear - this.front + 1;
        }
    }
    push(value) {
        this.rear += 1;
        this.storage[this.rear] = value
    }
    pop_left() {
        let value = this.storage[this.front];
        if(this.size()) {
            delete this.storage[this.front];
            this.front += 1;
        }
        return value;
    }
}
const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let graph = Array.from(Array(N), () => Array());
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j];
    }
}
let visited = Array.from(Array(N), () => Array(N).fill(false));
let vx = [0, 1, 0, -1];
let vy = [-1, 0, 1, 0];
let land = 1;
for(let i=0; i<graph.length; i++) {
    for(let j=0; j<graph[i].length; j++) {
        if(!visited[i][j] && graph[i][j] === 1) {
            visited[i][j] = true;
            graph[i][j] = land;
            DFS(i, j);
            land += 1;
        }
    }
}
let output = [];
for(let i=0; i<graph.length; i++) {
    for(let j=0; j<graph[i].length; j++) {
        if(graph[i][j] !== 0) {
            let answel = BFS([i,j]);
            if(answel !== undefined) {
                output.push(answel - 1);
            }
            visited.map((v) => v.fill(false));
        }
    }
}
console.log(Math.min(...output));

function DFS(y, x) {
    for(let i=0; i<4; i++) {
        let nx = x + vx[i];
        let ny = y + vy[i];
        if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
            if(!visited[ny][nx] && graph[ny][nx] === 1) {
                visited[ny][nx] = true;
                graph[ny][nx] = land;
                DFS(ny,nx);
            }
        }
    }
}

function BFS(start) {
    let que = new Queue();
    let depth = -1;
    let [fy, fx] = start;
    que.push(start);
    while(que.size() !== 0) {
        depth += 1;
        let sz = que.size();
        for(let i=0; i<sz; i++) {
            let [y, x] = que.pop_left();
            if((graph[y][x] !== 0) && (graph[y][x] !== graph[fy][fx])) {
                return depth;
            }
            for(let j=0; j<4; j++) {
                let nx = x + vx[j];
                let ny = y + vy[j];
                if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
                    if(!visited[ny][nx] && graph[ny][nx] !== graph[fy][fx]) {
                        que.push([ny,nx]);
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }
    return undefined;
}