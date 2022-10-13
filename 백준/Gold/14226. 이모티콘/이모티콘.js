class Queue {
    constructor() {
        this.storage = {},
        this.front = 0;
        this.rear = -1;
    }
    
    size() {
        if(this.front > this.rear) {
            return 0;
        } else {
            return this.rear - this.front + 1
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
let S = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let visited = Array.from(Array(1001), () => Array(1001).fill(false));
console.log(BFS());

function BFS() {
    let que = new Queue();
    let depth = -1;
    que.push({s: 1, cb: 0});
    visited[1][0] = true;
    while(que.size() !== 0) {
        depth += 1;
        if(visited[S].includes(true)) {
            return depth;
        }
        let sz = que.size();
        for(let i=0; i<sz; i++) {
            let ps = que.pop_left();
            let ns = [{s: ps.s, cb: ps.s}, {s: ps.s + ps.cb, cb: ps.cb}, {s: ps.s - 1, cb:ps.cb}];
            for(let j=0; j<ns.length; j++) {
                if(ns[j].s >=1 && ns[j].s <= 1000) {
                    if(!visited[ns[j].s][ns[j].cb]) {
                        visited[ns[j].s][ns[j].cb] = true;
                        que.push(ns[j]);
                    }
                }
            }
        }
    }
}
