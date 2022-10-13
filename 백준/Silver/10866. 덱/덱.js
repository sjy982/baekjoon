class Deque {
    constructor() {
        this.storage = {},
        this.front = 0,
        this.rear = -1
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
        this.rear += 1;
        this.storage[this.rear] = value;
    }
    
    pop_front() {
        if(this.front > this.rear) {
            return -1;
        } else {
            let value = this.storage[this.front];
            delete this.storage[this.front];
            this.front += 1;
            return value;
        }
    }
    
    pop_back() {
        if(this.front > this.rear) {
            return -1;
        } else {
            let value = this.storage[this.rear];
            delete this.storage[this.rear];
            this.rear -= 1;
            return value;
        }
    }
    
    empty() {
        if(this.front > this.rear) {
            return 1;
        } else {
            return 0;
        }
    }
    
    front_object() {
        if(this.front > this.rear) {
            return -1
        } else {
            return this.storage[this.front];
        }
    }
    
    back_object() {
        if(this.front > this.rear) {
            return -1
        } else {
            return this.storage[this.rear];
        }
    }
}
const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let deq = new Deque();
let answel = '';
for(let i=0; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ');
    if(input[0] === 'push_front') {
        deq.push_front(input[1] * 1);
    } else if(input[0] === 'push_back') {
        deq.push_back(input[1] * 1);
    } else if(input[0] === 'pop_front') {
        answel += `${deq.pop_front()}\n`;
    } else if(input[0] === 'pop_back') {
        answel += `${deq.pop_back()}\n`;
    } else if(input[0] === 'size') {
        answel += `${deq.size()}\n`;
    } else if(input[0] === 'empty') {
        answel += `${deq.empty()}\n`;
    } else if(input[0] === 'front') {
        answel += `${deq.front_object()}\n`;
    } else if(input[0] === 'back') {
        answel += `${deq.back_object()}\n`;
    } 
}
console.log(answel.trim());
