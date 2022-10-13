const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift() * 1;
let objArr = [];
let newArr = [];
let answel = [];
let output = '';
inputData.map((ele, index) => {
    let X = ele.split(' ')[0] * 1;
    let Y = ele.split(' ')[1] * 1;
    objArr[index] = {x: X, y: Y};
})
objArr.sort((a,b) => {
    return a.y - b.y;
})
for(let i=0; i<N; i++) {
    if(i !== N-1 && objArr[i].y === objArr[i+1].y) {
        newArr.push(objArr[i]);
    } else {
        newArr.push(objArr[i]);
        newArr.sort((a,b) => {
            return a.x - b.x;
        })
        answel.push(newArr);
        newArr = [];
    }
}

answel.map((ele,index) => {
    ele.map((ele, index) => {
        output += `${ele.x} ${ele.y}\n`;
    });
});
console.log(output.trim());
