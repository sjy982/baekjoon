//0-1 BFS 
class Deque {
    constructor() {
        this.storage = {},
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
    
    push_front(value) {
        this.front -= 1;
        this.storage[this.front] = value;
    }
    
    push_back(value) {
        this.rear +=1;
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
let graph = Array.from(Array(M), () => Array());
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j];
    }
}
let visited = Array.from(Array(M), () => Array(N).fill(-1));
BFS();
console.log(visited[M-1][N-1]);

function BFS() {
    //0-1 BFS => deque를 이용해서 품.
    let deque = new Deque();
    deque.push_back([0,0]);
    visited[0][0] = 0;
    let vx = [0,1,0,-1];
    let vy = [-1,0,1,0];
    while(deque.size() !== 0) {
        if(visited[M-1][N-1] !== -1) {
            return;
        }
        let [x,y] = deque.pop_left();
        for(let i=0; i<4; i++) {
            let nx = x + vx[i];
            let ny = y + vy[i];
            if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=M-1)) {
                if(visited[ny][nx] === -1) {
                    if(graph[ny][nx] === 0) {
                        visited[ny][nx] = visited[y][x];
                        deque.push_front([nx, ny]);
                    } else {
                        visited[ny][nx] = visited[y][x] + 1;
                        deque.push_back([nx, ny]);
                    }
                }
            }
        }
    }
}

//다익스트라 알고리즘 풀이
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
            return this.rear - this.front + 1;
        }
    }
    
    push(value) {
        this.rear +=1;
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
let graph = Array.from(Array(M), () => Array());
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j];
    }
}
let visited = Array.from(Array(M), () => Array(N).fill(Infinity));
BFS();
console.log(visited[M-1][N-1]);

function BFS() {
    //다익스트라 알고리즘 사용 => queue를 이용
    let que = new Queue();
    que.push([0,0]);
    visited[0][0] = 0;
    let vx = [0,1,0,-1];
    let vy = [-1,0,1,0];
    while(que.size() !== 0) {
        let [x,y] = que.pop_left();
        for(let i=0; i<4; i++) {
            let nx = x + vx[i];
            let ny = y + vy[i];
            if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=M-1)) {
                if(graph[ny][nx] === 0) {
                    if(visited[ny][nx] > visited[y][x]) {
                        visited[ny][nx] = visited[y][x];
                        que.push([nx,ny]);
                    }
                } else {
                    if(visited[ny][nx] > visited[y][x] + 1) {
                        visited[ny][nx] = visited[y][x] + 1;
                        que.push([nx,ny]);
                    }
                }
            }
        }
    }
}
