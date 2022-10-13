const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N,M] = inputData[0].trim().split(' ').map(x=>x*1);
let paper = new Array(N);
for(let i=0; i<N; i++) {
    paper[i] = new Array(M);
}
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        paper[i-1][j] = input[j];
    }
}
let answel = 0;
for(let i=0; i<N; i++) {
    for(let j=0; j<M; j++) {
        let cordi_max = find_max(j,i); //좌표 최대값
        if(answel < cordi_max) {
            answel = cordi_max;
        }
    }
}

console.log(answel);

function find_max(x, y) {
    let max = [];
    //테트로미노 1번째 모양
    if((0<=x+3 && x+3<M)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y][x+2] + paper[y][x+3]);
    }
    if((0<=y+3 && y+3<N)) {
        max.push(paper[y][x] + paper[y+1][x] + paper[y+2][x] + paper[y+3][x]);
    }
    //테트로미노 2번째 모양
    if((0<=x+1 && x+1<M) && (0<=y+1 && y+1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y+1][x] + paper[y+1][x+1]);
    }
    //테트로미노 3번째 모양
    if((0<=x+1 && x+1<M) && (0<=y+2 && y+2<N)) {
        max.push(paper[y][x] + paper[y+1][x] + paper[y+2][x] + paper[y+2][x+1]);
    }
    if((0<=x+2 && x+2<M) && (0<=y-1 && y-1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y][x+2] + paper[y-1][x+2]);
    }
    if((0<=x-1 && x-1<M) && (0<=y-2 && y-2<N)) {
        max.push(paper[y][x] + paper[y-1][x] + paper[y-2][x] + paper[y-2][x-1]);
    }
    if((0<=x-2 && x-2<M) && (0<=y+1 && y+1<N)) {
        max.push(paper[y][x] + paper[y][x-1] + paper[y][x-2] + paper[y+1][x-2]);
    }
    //3 모양 대칭
    if((0<=x-1 && x-1<M) && (0<=y+2 && y+2<N)) {
        max.push(paper[y][x] + paper[y+1][x] + paper[y+2][x] + paper[y+2][x-1]);
    }
    if((0<=x+2 && x+2<M) && (0<=y+1 && y+1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y][x+2] + paper[y+1][x+2]);
    }
    if((0<=x+1 && x+1<M) && (0<=y-2 && y-2<N)) {
        max.push(paper[y][x] + paper[y-1][x] + paper[y-2][x] + paper[y-2][x+1]);
    }
    if((0<=x-2 && x-2<M) && (0<=y-1 && y-1<N)) {
        max.push(paper[y][x] + paper[y][x-1] + paper[y][x-2] + paper[y-1][x-2]);
    }
    //테트로미노 4번째 모양
    if((0<=x+1 && x+1<M) && (0<=y+2 && y+2<N)) {
        max.push(paper[y][x] + paper[y+1][x] + paper[y+1][x+1] + paper[y+2][x+1]);
    }
    if((0<=x+2 && x+2<M) && (0<=y-1 && y-1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y-1][x+1] + paper[y-1][x+2]);
    }
    if((0<=x-1 && x-1<M) && (0<=y+2 && y+2<N)) {
        max.push(paper[y][x] + paper[y+1][x] + paper[y+1][x-1] + paper[y+2][x-1]);
    }
    if((0<=x+2 && x+2<M) && (0<=y+1 && y+1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y+1][x+1] + paper[y+1][x+2]);
    }
    //테트로미노 5번째 모양
    if((0<=x+2 && x+2<M) && (0<=y+1 && y+1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y][x+2] + paper[y+1][x+1]);
    }
    if((0<=x+1 && x+1<M) && (0<=y-2 && y-2<N)) {
        max.push(paper[y][x] + paper[y-1][x] + paper[y-2][x] + paper[y-1][x+1]);
    }
    if((0<=x+2 && x+2<M) && (0<=y-1 && y-1<N)) {
        max.push(paper[y][x] + paper[y][x+1] + paper[y][x+2] + paper[y-1][x+1]);
    }
    if((0<=x-1 && x-1<M) && (0<=y-2 && y-2<N)) {
        max.push(paper[y][x] + paper[y-1][x] + paper[y-2][x] + paper[y-1][x-1]);
    }
    return Math.max(...max);
}