const fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let N = inputData.shift();
let answel = '';
inputData.sort((a,b)=>{return a-b});
let sum = inputData.reduce((a,b) => a+b);
let average = Math.round(sum/N);
let mode = 0;
let center = inputData[(N-1)/2];
let range = inputData[inputData.length-1] - inputData[0];

let map = new Map();
inputData.map((element,index) => {
    if(!map.has(element)) {
        map.set(element,1);
    } else {
        map.set(element, map.get(element) + 1);
    }
})

let modeArray = [];
let max = 0;
map.forEach((element, key)=> {
    if(element>max) {
        modeArray = [];
        modeArray.push(key);
        max = element;
    } else if(element === max) {
        modeArray.push(key);
    }
});
if(modeArray.length === 1) {
    mode = modeArray[0];
} else {
    mode = modeArray[1];
}

console.log(`${average}\n${center}\n${mode}\n${range}`);
