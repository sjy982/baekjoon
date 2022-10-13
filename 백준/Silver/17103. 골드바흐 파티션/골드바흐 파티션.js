const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let repeat = inputData.shift();
let end = Math.max(...inputData);
let deciArr = new Array(end+1).fill(true);
deciArr[0] = false;
deciArr[1] = false;
for(let i=2; i*i<=end; i++) {
    for(let j=i*i; j<=end; j+=i) {
        deciArr[j] = false;
    }
}
let deciMap = new Map();
let output = '';
deciArr.map((ele, index) => {
    if(ele) {
        deciMap.set(index, ele);
    }
})
for(let i=0; i<repeat; i++) {
    let n = inputData[i];
    let count = 0;
    try {
        deciMap.forEach((ele,key) => {
            let b = n-key;
            let a = key;
            if(b>=a) {
                if(deciMap.get(b) === true) {
                    count += 1;
                }
            } else {
                throw new Error('stop loop');
            }
        })
    } catch(e) {
        output += `${count}\n`;
    }
}
console.log(output.trim());