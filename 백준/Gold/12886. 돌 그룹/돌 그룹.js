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
let ol_check = new Map(); //중복 체크
console.log(BFS());

function BFS() {
    let que = new Queue();
    que.push([A,B,C]);
    combiCheckTrue(A,B,C);
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
                if(ol_check.get(`${a}${b}${c}`) === undefined) {
                    combiCheckTrue(a,b,c);
                    que.push([a,b,c]);
                }
            }
        }
    }
    return 0;
}


function combiCheckTrue(a, b, c) {
    ol_check.set(`${a}${b}${c}`, true);
    ol_check.set(`${a}${c}${b}`, true);
    ol_check.set(`${b}${a}${c}`, true);
    ol_check.set(`${b}${c}${a}`, true);
    ol_check.set(`${c}${a}${b}`, true);
    ol_check.set(`${c}${b}${a}`, true);
}
