const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let obArr = [];
let xArr = [];
let answel = [];
let output = '';
inputData.map((ele,index) => {
    let x = ele.split(' ')[0] * 1;
    let y = ele.split(' ')[1] * 1;
    obArr[index] = {x: x, y: y};
});

obArr.sort((a,b) => {
    return a.x - b.x;
})

for(let i=0; i<N; i++) {
        if(i !== N-1 && obArr[i].x === obArr[i+1].x) {
           xArr.push(obArr[i]);
        } else {
        //같지 않다면.
            xArr.push(obArr[i]);
            xArr.sort((a,b) => {
               return a.y - b.y;
           })
           answel.push(xArr);
           xArr = [];
        }
}
answel.map((ele, index) => {
    for(let i =0; i<ele.length; i++) {
        output += `${ele[i].x} ${ele[i].y}\n`;
    }
})
console.log(output.trim());
