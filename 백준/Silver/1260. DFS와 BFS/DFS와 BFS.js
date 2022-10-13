const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N,M,V] = inputData[0].trim().split(' ').map(x=>x*1);
let graph = Array.from(Array(M),() => Array());
let graph_list = Array.from(Array(N+1), () => Array());
let max_len = 0;
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        graph[i-1][j] = input[j]
    } 
}
for(let [a,b] of graph) {
    graph_list[a].push(b);
    graph_list[b].push(a);
}
graph_list.map((ele) => {
    ele.sort((a,b) => {
        return a-b;
    })
    if(ele.length !==0) {
        max_len += 1;
    }
});
let result = [V];
let que = [];
let visited = Array.from(Array(N+1)).fill(false);
visited[V] = true;
if(graph_list[V].length === 0) {
    //시작점이 연결이 안 돼 있다면 
    answel = `${V}\n${V}`;
} else {
    DFS();
    visited.fill(false);
    visited[V] = true;
    result = [V];
    que = [V]
    BFS();
}
console.log(answel);

function DFS() {
    if(result.length === max_len) {
        answel = `${result.join(' ')}\n`;
        return
    } else {
        let n = result[result.length-1];
        for(let i=0; i<graph_list[n].length; i++) {
            if(!visited[graph_list[n][i]]) {
                result.push(graph_list[n][i]);
                visited[graph_list[n][i]] = true;
                DFS();
            }
        }
    }
}

function BFS() {
    if(result.length === max_len) {
        answel += `${result.join(' ')}`;
    } else {
        if(que.length !== 0) {
            let n = que.shift();
            for(let i=0; i<graph_list[n].length; i++) {
                if(!visited[graph_list[n][i]]) {
                    result.push(graph_list[n][i]);
                    visited[graph_list[n][i]] = true;
                    que.push(graph_list[n][i]);
                    if(result.length === max_len) {
                        BFS();
                    }
                }
            }
            if(result.length !== max_len) {
                BFS();
            }
        }
    }
}

