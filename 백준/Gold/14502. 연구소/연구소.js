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
let [N, M] = inputData[0].trim().split(' ').map(x=>x*1);
let lMap = Array.from(Array(N), () => Array(M));
let bfs_lMap = Array.from(Array(N), () => Array(M));
let virus_location = [];
let wx = [-1, 0, 1, 0];
let wy = [0, -1, 0, 1];
let answel = 0;
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        lMap[i-1][j] = input[j];
        if(input[j] === 2) {
            virus_location.push([j,i-1]);
        }
    }
}
makeWall(0,0);
console.log(answel);

function BFS() {
    let que = new Queue();
    virus_location.map((loc) => {
        que.push(loc);
    });
    while(que.size()) {
        let [x,y] = que.pop_left();
        for(let i=0; i<4; i++) {
            let nx = x + wx[i];
            let ny = y + wy[i];
            if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                if(bfs_lMap[ny][nx] === 0) {
                    bfs_lMap[ny][nx] = 2;
                    que.push([nx,ny]);
                }
            }
        }
    }
}

function makeWall(j,cout) {
    if(cout === 3) {
        bfs_lMap = lMap.map(v => [...v]);
        BFS();
        let safe = 0;
        for(let i=0; i<N; i++) {
            for(let j=0; j<M; j++) {
                if(bfs_lMap[i][j] === 0) {
                    safe += 1;
                }
            }
        }
        if(answel < safe) {
            answel = safe;
        }
        return;
    } else {
        for(let i=j; i<N*M; i++) {
            let y = parseInt(i/M);
            let x = i%M;
            if(lMap[y][x] === 0) {
                lMap[y][x] = 1;
                makeWall(j+1, cout+1);
                lMap[y][x] = 0;
            }
        }
    }
}