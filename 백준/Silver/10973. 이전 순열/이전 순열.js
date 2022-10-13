const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let input = inputData[1].trim().split(' ').map(x=>x*1);
let right_arr = [];
let left_arr = '';
let answel = '';
let all_asc = true;
for(let i=input.length - 1; i>=0; i--) {
    if(input[i] > input[i+1]) {
        all_asc = false;
        for(let j=0; j<right_arr.length; j++) {
            if(input[i]>right_arr[j]) {
                answel = right_arr[j];
                right_arr[j] = input[i];
                break;
            }
        }
        break;
    } else {
        right_arr.push(input[i]);
    }
}
for(let i=0; i<input.length - (right_arr.length + 1); i++) {
    left_arr += ` ${input[i]}`;
}
answel = all_asc ? -1 : `${left_arr} ${answel} ${right_arr.join(' ')}`.trim();
console.log(answel);