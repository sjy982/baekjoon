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
let [N, M] = inputData[0].trim().split(' ').map(x => x * 1);
let board = Array.from(Array(N), () => Array(M));
let visited_map = new Map(); //방문 체크를 위한 Map
let coin = [];
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for (let j = 0; j < input.length; j++) {
        if (input[j] === 'o') {
            board[i - 1][j] = '.';
            coin.push([j, i - 1]);
        } else {
            board[i - 1][j] = input[j]
        }
    }
}

if(N===1 && M===1) {
    console.log(-1);
} else {
    console.log(BFS());
}

function BFS() {
    let que = new Queue();
    let [cx1, cy1] = coin[0];
    let [cx2, cy2] = coin[1];
    visited_map.set(`${cx1}${cy1}${cx2}${cy2}`, true);
    que.push({ c1: [cx1, cy1], c2: [cx2, cy2], cut: 0 });
    let wx = [-1, 1, 0, 0]; //왼쪽 오른쪽 위 아래
    let wy = [0, 0, -1, 1]; //왼쪽 오른쪽 위 아래
    while (que.size() !== 0) {
        let value = que.pop_left();
        let [x1, y1] = value.c1; //첫번째 동전에 위치
        let [x2, y2] = value.c2; //두번째 둥전에 위치
        if (((x1 >= 0 && x1 <= M - 1) && (y1 >= 0 && y1 <= N - 1)) && (x2 < 0 || x2 >= M) || (y2 < 0 || y2 >= N)) return value.cut;
        if (((x2 >= 0 && x2 <= M - 1) && (y2 >= 0 && y2 <= N - 1)) && (x1 < 0 || x1 >= M) || (y1 < 0 || y1 >= N)) return value.cut;
        if (value.cut < 10) {
            for (let i = 0; i < 4; i++) {
                let nx1 = x1 + wx[i];
                let ny1 = y1 + wy[i];
                let nx2 = x2 + wx[i];
                let ny2 = y2 + wy[i];
                if (((nx1 >= 0 && nx1 <= M - 1) && (ny1 >= 0 && ny1 <= N - 1))) {
                    if (board[ny1][nx1] === '#') {
                        nx1 = x1;
                        ny1 = y1;
                    }
                }
                if (((nx2 >= 0 && nx2 <= M - 1) && (ny2 >= 0 && ny2 <= N - 1))) {
                    if (board[ny2][nx2] === '#') {
                        nx2 = x2;
                        ny2 = y2;
                    }
                }
                let vn = `${nx1}${ny1}${nx2}${ny2}`; //visited number
                if (visited_map.get(vn) === undefined) {
                    if (!(((nx1 < 0 || nx1 >= M) || (ny1 < 0 || ny1 >= N)) && ((nx2 < 0 || nx2 >= M) || (ny2 < 0 || ny2 >= N)))) {
                        if (!((nx1 === nx2) && (ny1 === ny2))) {
                            visited_map.set(vn, true);
                            que.push({ c1: [nx1, ny1], c2: [nx2, ny2], cut: value.cut + 1 });
                        }
                    }
                }
            }
        }
    }
    return -1;
}
