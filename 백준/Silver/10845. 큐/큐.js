class Queue {
    constructor() {
        this.storage = {};
        this.front = 0;
        this.rear = -1;
    }
    size() {
        if(this.front === 0 && this.rear === -1) {
            return 0;
        } else {
            return this.rear - this.front + 1;
        }
    }
    //뒤에 들어감
    add(value) {
        this.rear += 1;
        this.storage[this.rear] = value;
    }
    //선입 선출!!
    popLeft() {
        let value = this.storage[this.front];
        if(this.front === this.rear || (this.front === 0 && this.rear === -1)) {
            delete this.storage[this.front];
            this.front = 0;
            this.rear = -1;
        } else {
            delete this.storage[this.front];
            this.front += 1;
        }
        return value;
    }
    
    frontNumber() {
        return this.storage[this.front];
    }
    
    rearNumber() {
        return this.storage[this.rear];
    }
    
}
const fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let que = new Queue();
let answel = '';
for(let i=0; i<inputData.length; i++) {
    let input = inputData[i].split(' ');
    if(input[0] === 'push') {
        que.add(input[1] * 1);
    } else if(input[0] === 'pop') {
        que.size() === 0 ? answel += `-1\n` : answel += `${que.popLeft()}\n`;
    } else if(input[0] === 'size') {
        answel += `${que.size()}\n`;
    } else if(input[0] === 'empty') {
        que.size() === 0 ? answel += `1\n` : answel += `0\n`;
    } else if(input[0] === 'front') {
        que.frontNumber() === undefined ? answel += `-1\n` : answel += `${que.frontNumber()}\n`;
    } else if(input[0] === 'back') {
        que.rearNumber() === undefined ? answel += `-1\n` : answel += `${que.rearNumber()}\n`;
    }
}
console.log(answel.trim());