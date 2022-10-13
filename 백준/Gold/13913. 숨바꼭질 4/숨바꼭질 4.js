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
let output;
if(N===K) {
    output = `0\n${N}`;
} else {
    let answel_obj = BFS();
    output = `${answel_obj.dep}\n${answel_obj.vStr}`;
}
console.log(output);

function BFS() {
    let que = new Queue();
    let depth = -1;
    que.push({n: N, visit: `${N}`});
    visited[N] = true;
    while(que.size() !== 0) {
        depth += 1;
        let sz = que.size();
        for(let i=0; i<sz; i++) {
            let cur_obj = que.pop_left();
            let x = cur_obj.n;
            let str_visit = cur_obj.visit;
            let nx = [x-1, x+1, 2*x];
            for(let j=0; j<3; j++) {
                if(nx[j] >=0 && nx[j] <= 100000) {
                    if(!visited[nx[j]]) {
                        let n_str = str_visit;
                        n_str += ` ${nx[j]}`;
                        que.push({n: nx[j], visit: n_str});
                        visited[nx[j]] = true;
                        if(visited[K]) {
                            return {dep : depth+1, vStr: que.storage[que.rear].visit};
                        }
                    }
                }
            }
        }
    }
}