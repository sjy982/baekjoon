// class Queue {
//     constructor() {
//         this.storage = {},
//             this.front = 0,
//             this.rear = -1
//     }

//     size() {
//         if (this.front === 0 && this.rear === -1) {
//             return 0;
//         } else {
//             return this.rear - this.front + 1;
//         }
//     }

//     add(value) {
//         this.rear += 1;
//         this.storage[this.rear] = value;
//     }

//     popLeft() {
//         let value = this.storage[this.front];
//         if (this.front === this.rear || (this.front === 0 && this.rear === -1)) {
//             delete this.storage[this.front];
//             this.front = 0;
//             this.rear = -1;
//         } else {
//             delete this.storage[this.front];
//             this.front += 1;
//         }
//         return value;
//     }
// }
const fs = require('fs');
let [N, K] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(x => x * 1);
let que = [];
for (let i = 1; i <= N; i++) {
    que.push(i);
}
let answel = '<';
while (que.length !== 0) {
    if (que.length === 1) {
        answel += `${que.shift()}>`
    } else {
        for (let i = 1; i <= K; i++) {
            if (i === K) {
                answel += `${que.shift()}, `;
            } else {
                que.push(que.shift());
            }
        }
    }
}
console.log(answel);