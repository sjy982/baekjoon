const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x => x * 1);
let repeat = inputData.length - 1;
let end = Math.max(...inputData);
let deciArr = new Array(end + 1).fill(true);
deciArr[0] = false;
deciArr[1] = false;
let output = '';
for (let i = 2; i * i <= end; i++) {
    for (let j = i * i; j <= end; j += i) {
        deciArr[j] = false;
    }
}
let deciMap = new Map();
deciArr.map((ele, index) => {
    if (ele === true && index % 2 === 1) {
        deciMap.set(index, ele);
    }
})

for (let i = 0; i < repeat; i++) {
    let n = inputData[i];
    let check = false;
    try {
        deciMap.forEach((ele, key) => {
            let b = n - key;
            if (b < 0) {
                throw new Error("stop loop");
            } else {
                if (deciMap.get(b) === true) {
                    output += `${n} = ${key} + ${b}\n`;
                    check = true;
                    throw new Error("stop loop");
                }
            }
        });
    } catch (e) {
        if (!check) {
            output += `Goldbach's conjecture is wrong.\n`;
        }
    }
}
console.log(output.trim());