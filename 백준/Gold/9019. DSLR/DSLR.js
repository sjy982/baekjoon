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
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let T = inputData[0].trim() * 1;
let A;
let B;
let visited;
let output = '';
let op = ['D', 'S', 'L', 'R'];
for (let i = 1; i <= T; i++) {
    let [a, b] = inputData[i].trim().split(' ').map(x => x * 1);
    A = a;
    B = b;
    visited = new Array(10000).fill(false);
    output += `${BFS()}\n`;
}
console.log(output.trim());

function BFS() {
    let que = new Queue();
    que.push([A, '']);
    visited[A] = true;
    while (que.size()) {
        let [a, op_str] = que.pop_left();
        if (a === B) {
            return op_str;
        }
        for (let i = 0; i < op.length; i++) {
            let na = DSLR(a, op[i]);
            if (!visited[na]) {
                visited[na] = true;
                que.push([na, op_str + op[i]]);
            }
        }
    }
}

function DSLR(v, op) {
    let nv;
    if (op === 'D') {
        nv = v * 2;
        if (nv > 9999) {
            nv = nv % 10000;
        }
    } else if (op === 'S') {
        if (v === 0) {
            nv = 9999;
        } else {
            nv = v - 1;
        }
    } else if( op === 'L') {
        nv = v % 1000 * 10 + parseInt(v/1000);
    } else if( op === 'R') {
        nv = parseInt(v/10) + v % 10 * 1000
    }
    return nv;
}
