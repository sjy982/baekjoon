const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let inputData2 = inputData[0].split(' ').map(x=>x*1);
let arrInput = inputData[0].split(' ').map(x=>x*1);
let map = new Map();
let output ='';
inputData2.sort((a,b) => {
    return a-b;
});
let beforeIndex = -1;
inputData2.map((ele, index) => {
    if(inputData2[index-1] !== ele) {
        map.set(ele, beforeIndex + 1);
        beforeIndex += 1;
    }
});
arrInput.map((ele,index) => {
    output += `${map.get(ele)} `;
})
console.log(output.trim());


