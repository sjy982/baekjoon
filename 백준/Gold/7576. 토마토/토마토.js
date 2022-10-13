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
let [M, N] = inputData[0].trim().split(' ').map(x => x * 1);
let graph = Array.from(Array(N), () => Array());
let start_arr = [];
let zero = false;
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split(' ');
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
        if (input[j] === '1') {
            start_arr.push([i - 1, j]);
        } else if (input[j] === '0') {
            zero = true;
        }
    }
}
if (zero) {
    let day = BFS(start_arr);
    let check = false;
    for(i=0; i<graph.length; i++) {
        if(graph[i].includes('0')) {
            check = true;
            break;
        }
    }
    if(check) {
        console.log(-1);
    } else {
        console.log(day);
    }
} else {
    //토마토가 전부 익어있는 상태.
    console.log(0);
}

function BFS(start) {
    let que = new Queue;
    let depth = 0;
    let vx = [0, -1, 1, 0];
    let vy = [-1, 0, 0, 1];
    start.map((ele) => {
        que.push(ele)
    });
    while (que.size() !== 0) {
        let cur_size = que.size();
        for (let i = 0; i < cur_size; i++) {
            let [y, x] = que.pop_left();
            for (let j = 0; j < 4; j++) {
                let ny = y + vy[j];
                let nx = x + vx[j];
                if ((ny >= 0 && ny <= N - 1) && (nx >= 0 && nx <= M - 1)) {
                    if (graph[ny][nx] === '0') {
                        que.push([ny, nx]);
                        graph[ny][nx] = '1';
                    }
                }
            }
        }
        if(que.size() !==0) {
            depth += 1;
        }
    }
    return depth;
}