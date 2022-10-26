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
let [N,M] = inputData[0].trim().split(' ').map(x=>x*1);
let SL = new Array(101).fill(false);
let visited = new Array(101).fill(false);
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    SL[input[0]] = input[1];
}
console.log(BFS());

function BFS() {
    let que = new Queue();
    que.push([1,0]);
    visited[1] = true;
    while(que.size()) {
        let [x,cout] = que.pop_left();
        if(x === 100) {
            return cout;
        }
        for(let i=1; i<=6; i++) {
            let nx = x+i;
            if(!visited[nx] && nx <= 100) {
                if(!SL[nx]) {
                    visited[nx] = true;
                    que.push([nx, cout + 1]); //사다리랑 뱀이 현재 좌표에 없음.
                } else {
                    if(!visited[SL[nx]]) {
                        visited[SL[nx]] = true;
                        que.push([SL[nx], cout + 1]);
                    }
                }
            }
        }
    }
}