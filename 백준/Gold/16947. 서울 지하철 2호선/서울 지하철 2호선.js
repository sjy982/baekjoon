class Queue {
    constructor() {
        this.front = 0;
        this.rear = -1;
        this.storage = {};
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
let N = inputData[0].trim() * 1;
let graph = Array.from(Array(N), () => Array());
let graph_list = Array.from(Array(N + 1), () => Array());
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x => x * 1);
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
    }
}
for (let [a, b] of graph) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}
let result = [];
let ring_line = [];
let check = false;
let visited = Array.from(Array(N + 1)).fill(false)
for (let i = 1; i <= N; i++) {
    if(!check) {
        result.push(i);
        visited[i] = true;
        DFS();
        result.pop();
        visited[i] = false;
    } else {
        break;
    }
}
let answel = '';
ring_line.map((ele) => { visited[ele] = true;})
let b_visited = Array.from(Array(N+1)).fill(false);
for(let i = 1; i<=N; i++) {
    if(!visited[i]) {
        answel += `${BFS(i)} `;
        b_visited.fill(false);
    } else {
        answel += '0 ';
    }
}
console.log(answel);
function DFS() {
    if (!check) {
        let n = result[result.length - 1];
        for (let i = 0; i < graph_list[n].length; i++) {
            if (!visited[graph_list[n][i]]) {
                result.push(graph_list[n][i]);
                visited[graph_list[n][i]] = true;
                DFS();
                result.pop();
                visited[graph_list[n][i]] = false;
            } else {
                if (result[0] === graph_list[n][i]) {
                    if (result.length >= 3) {
                        ring_line = [...result];
                        check = true;
                        break;
                    }
                }
            }
        }
    }
}

function BFS(n) {
    let que = new Queue();
    let depth = -1;
    que.push(n);
    b_visited[n] = true;
    while(que.size() !== 0) {
        depth += 1;
        let sz = que.size();
        for(let i=0; i<sz; i++) {
            let v = que.pop_left();
            if(visited[v]) { return depth;}
            for(let j=0; j<graph_list[v].length; j++) {
                if(!b_visited[graph_list[v][j]]) {
                    que.push(graph_list[v][j]);
                    b_visited[graph_list[v][j]] = true;
                }
            }
        }
    }
    return depth;
}