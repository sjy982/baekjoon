const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let input = inputData[1].trim().split(' ').map(x=>x*1);
let r_arr = [];
let cur_len = 0;
let answel = '';
let all_desc = true;
for(let i=input.length-1; i>=0; i--) {
    cur_len += 1;
    if(input[i] < input[i+1]){
        all_desc = false;
        for(let j=0; j<r_arr.length; j++) {
            if(r_arr[j] > input[i]) {
                answel = r_arr[j];
                r_arr[j] = input[i];
                break;
            }
        }
        break;
    } else {
        r_arr.push(input[i]);
    }
}
let left_arr = [];
for(let i=0; i<input.length-cur_len; i++) {
    left_arr.push(input[i]);
}
answel = all_desc ? '-1' : `${left_arr.join(' ')} ${answel} ${r_arr.join(' ')}`;
console.log(answel.trim());