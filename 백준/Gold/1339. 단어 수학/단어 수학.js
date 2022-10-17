const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let alp_map = new Map();
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for(let j=0; j<input.length; j++) {
        let pv = input.length - j //자릿수
        if(alp_map.get(input[j])===undefined) {
            alp_map.set(input[j], Number('1'.repeat(pv)));
        } else {
            alp_map.set(input[j], alp_map.get(input[j]) + Number('1'.repeat(pv)));
        }
    }
}
//map을 sort해줄려면 array 바꿔줘야함.
let alp_arr = Array.from(alp_map); //map을 array로 
alp_arr.sort((a,b) => b[1] - a[1]);
let sort_alp_map = new Map(alp_arr); //array를 map으로
let n = 9;
sort_alp_map.forEach((val,key) => {
    alp_map.set(key, n);
    n -=1;
});
let answel = 0;

for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('');
    let value = '';
    for(let j=0; j<input.length; j++) {
        value += alp_map.get(input[j]);
    }
    answel += Number(value);
}
console.log(answel);