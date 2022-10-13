class Queue {
    constructor() {
        this.storage={};
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
let [N, K] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x=>x*1);
let visited = new Array(100001).fill(false);
console.log(BFS());
function BFS() {
    let que = new Queue();
    let depth = -1;
    que.push(N);
    visited[N] = true;
    while(que.size() !== 0) {
        depth += 1;
        if(visited[K]) {
            return depth;
        }
        let sz = que.size();
        for(let i=0; i<sz; i++) {
            let x = que.pop_left();
            let nx = [x-1, x+1, 2*x];
            for(let j=0; j<3; j++) {
                if(nx[j] >=0 && nx[j] <= 100000) {
                    if(!visited[nx[j]]) {
                        que.push(nx[j]);
                        visited[nx[j]] = true;
                    }
                }
            }
        }
    }
    return depth;
}