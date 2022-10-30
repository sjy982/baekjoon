class Queue {
    constructor() {
        this.storage = {};
        this.rear = -1;
        this.front = 0;
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
let [N,M] = inputData[0].trim().split(' ').map(Number);
let map = Array.from(Array(N), () => Array(M));
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('').map(Number);
    for(let j=0; j<input.length; j++) {
        map[i-1][j] = input[j];
    }
}
let visited = new Array(N); //x좌표 y좌표 0 => 벽 안부심, 1=> 벽 부심
for(let i=0; i<visited.length; i++) {
    visited[i] = new Array(M);
    for(let j=0; j<visited[i].length; j++) {
        visited[i][j] = new Array(2).fill(false); //3차원 배열 생성 
    }
}
console.log(BFS());

function BFS() {
    let que = new Queue();
    que.push([0,0,0,0]); //시작하는 칸 포함
    visited[0][0][0] = true;
    visited[0][0][1] = true;
    let wx = [0, 0, -1, 1];
    let wy = [-1, 1, 0, 0];
    
    while(que.size()) {
        let [x,y,z, cout] = que.pop_left();
        if(x===M-1 && y=== N-1) {
            return cout + 1; //끝나는 칸 포함
        }
        for(let i=0; i<4; i++) {
            let nx = x + wx[i];
            let ny = y + wy[i];
            if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                if(map[ny][nx] === 0) {
                    //벽이 없다면
                    if(!visited[ny][nx][z]) {
                        visited[ny][nx][z] = true;
                        que.push([nx, ny, z, cout + 1]);
                    }
                } else {
                    //벽이 있는데 z가 0이라면
                    if(z === 0) {
                        if(!visited[ny][nx][1]) {
                            visited[ny][nx][1] = true;
                            que.push([nx, ny, 1, cout + 1]);
                        }
                    }
                }
            }
        }
    }
    return -1;
}