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
        this.storage[this.rear] = value;
    }
    
    pop_left() {
        let value = this.storage[this.front];
        if(this.size()) {
            this.front += 1;
        }
        return value;
    }
}

const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let T = inputData.shift().trim() * 1;
let N;
let start_cdn;
let target_cdn;
let graph;
let output = '';
for(let t=0; t<T; t++) {
    N = inputData.shift().trim() * 1;
    start_cdn = [];
    target_cdn = [];
    for(let i=0; i<2; i++) {
        if(i===0) {
            start_cdn = inputData.shift().split(' ').map(x=>x*1);
        } else if(i===1) {
            target_cdn = inputData.shift().split(' ').map(x=>x*1);
        }
    }
    graph = Array.from(Array(N), () => Array(N).fill(0));
    output += `${BFS(start_cdn)}\n`;
}
console.log(output.trim());

function BFS(start) {
    let que = new Queue();
    que.push(start);
    graph[start[1]][start[0]] = 1;
    let depth = -1;
    let vy = [-1,-2,-2,-1,1,2,2,1]; 
    let vx = [-2,-1,1,2,2,1,-1,-2];
    while(que.size() !== 0) {
        depth += 1;
        if(graph[target_cdn[1]][target_cdn[0]] === 1) {
            break;
        }
        let cur_size = que.size();
        for(let i=0; i<cur_size; i++) {
            let [x,y] = que.pop_left();
            for(let j=0; j<8; j++) {
                let nx = x + vx[j];
                let ny = y + vy[j];
                if((nx >= 0 && nx <= N-1) && (ny >= 0 && ny <= N-1)) {
                    if(graph[ny][nx] !== 1) {
                        que.push([nx,ny]);
                        graph[ny][nx] = 1;
                    }
                }
            }
        }
    }
    return depth;
}