class Queue {
    constructor() {
        this.storage = {};
        this.front = 0;
        this.rear = -1;
    }

    size() {
        if (this.front > this.rear) {
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
        if (this.size()) {
            delete this.storage[this.front];
            this.front += 1;
        }
        return value;
    }
}
const fs = require('fs');
let [N, K] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x => x * 1);
let visited = new Array(100001).fill(false);
console.log(BFS());


function BFS() {
    let que = new Queue();
    let depth = -1;
    let answel_arr = [];
    que.push({ x: N, mx: 0 });
    visited[N] = true;
    while (que.size() !== 0) {
        depth += 1;
        let sz = que.size();
        for (let i = 0; i < sz; i++) {
            let n_obj = que.pop_left();
            if(n_obj.x === K) {
                return depth - n_obj.mx;
            }
            let nx = [n_obj.x * 2 ,n_obj.x - 1, n_obj.x + 1];
            for (let j = 0; j < nx.length; j++) {
                if (nx[j] >= 0 && nx[j] <= 100000) {
                    if (!visited[nx[j]]) {
                        visited[nx[j]] = true;
                        let obj;
                        if (j === 0) {
                            obj = { x: nx[j], mx: n_obj.mx + 1 };
                            que.push(obj);
                        } else {
                            obj = { x: nx[j], mx: n_obj.mx };
                            que.push(obj);
                        }
                    }
                }
            }
        }
    }
}
