class Queue {
    constructor() {
        this.storage = {};
        this.front = 0;
        this.rear = -1
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
let [A,B,C] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number);
let visited = Array.from(Array(1501), () => Array(1501).fill(0));
console.log(BFS());

function BFS() {
    let que = new Queue();
    que.push([A,B,C]);
    let sn = [[0,1,2], [0,2,1], [1,2,0]]; //select number
    while(que.size()) {
        let rockGup = que.pop_left();
        if(rockGup[0]===rockGup[1] && rockGup[1]===rockGup[2]) {
            return 1;
        }
        for(let i=0; i<sn.length; i++) {
            let [n1,n2,left] = sn[i];
            if(rockGup[n1] !== rockGup[n2]) {
                let a,b;
                let c = rockGup[left];
                if(rockGup[n1] > rockGup[n2]) {
                    //rockGup[n1]이 더 큰 경우
                    a = rockGup[n1] - rockGup[n2];
                    b = rockGup[n2] * 2;
                } else {
                    //rockGup[n2]가 더 큰 경우
                    a = rockGup[n2] - rockGup[n1];
                    b = rockGup[n1] * 2;
                }
                if(visited[a][b]===0) {
                     visited[a][b] = 1;
                     visited[b][a] = 1;
                     que.push([a,b,c]);
                }
            }
        }
    }
    return 0;
}
