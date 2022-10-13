const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0]*1;
let M = inputData[2]*1;
let inputN = inputData[1].split(' ').map(x=>x*1);
let inputM = inputData[3].split(' ').map(x=>x*1);
let map = new Map();
let output = '';
inputN.map((ele,index) => {
    if(!map.has(ele)) {
        map.set(ele, 1);
    } else {
        map.set(ele, map.get(ele) + 1);
    }
});
inputM.map((ele,index) => {
   map.get(ele) !== undefined ? output += `${map.get(ele)} ` : output +=`0 `; 
});
console.log(output.trim());