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
let N = inputData[0].trim() * 1;
let graph = Array.from(Array(N - 1), () => Array());
let graph_list = Array.from(Array(N + 1), () => Array());
for (let i = 1; i < inputData.length - 1; i++) {
    let input = inputData[i].trim().split(' ').map(x => x * 1);
    for (let j = 0; j < input.length; j++) {
        graph[i - 1][j] = input[j];
    }
}
for (let [a, b] of graph) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}
let nCheck = inputData[inputData.length - 1].trim().split(' ').map(x => x * 1);
let visited = new Array(N + 1).fill(false);
let si = 1; //start index
let output = '';
if(nCheck[0] !== 1) {
    output = 0;
} else {
    output = BFS(1);
}
console.log(output);
function BFS(n) {
    let que = new Queue();
    que.push(n);
    visited[n] = true;
    while (que.size() !== 0) {
        let sz = que.size();
        for (let i = 0; i < sz; i++) {
            let v = que.pop_left();
            let map = new Map();
            let ei = si;
            for(let i=0; i<graph_list[v].length; i++) {
                if(!visited[graph_list[v][i]]) {
                    map.set(nCheck[ei], true);
                    ei += 1;
                }
            }
            for (let i = 0; i < graph_list[v].length; i++) {
                if (!visited[graph_list[v][i]]) {
                    if (map.get(graph_list[v][i]) === undefined) {
                        return 0;
                    } 
                }
            }
            for(let i=si; i<ei; i++) {
                que.push(nCheck[i]);
                visited[nCheck[i]] = true;
            }
            si = ei;
        }
    }
    return 1;
}