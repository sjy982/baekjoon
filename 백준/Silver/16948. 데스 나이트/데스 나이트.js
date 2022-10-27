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
            delete this.storage[this.front];
            this.front += 1;
        }
        return value;
    }
}
const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let input2 = inputData[1].split(' ').map(x=>x*1);
let r1 = input2[0];
let c1 = input2[1];
let r2 = input2[2];
let c2 = input2[3]
let visited = Array.from(Array(N), () => Array(N).fill(false));
console.log(BFS());

function BFS() {
    let que = new Queue();
    que.push([r1,c1, 0]);
    visited[c1][r1] = true;
    let wx = [-2, -2, 0, 0, 2, 2];
    let wy = [-1, 1, -2, 2, -1, 1];
    while(que.size()) {
        let [x, y, cout] = que.pop_left();
        if((x === r2) && (y === c2)) {
            return cout;
        }
        for(let i=0; i<6; i++) {
            let nx = x + wx[i];
            let ny = y + wy[i];
            if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
                if(!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    que.push([nx, ny, cout + 1]);
                }
            }
        }
    }
    return -1;
}
