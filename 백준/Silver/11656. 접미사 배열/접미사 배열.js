const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let repeat = inputData.length;
let inputData2 = [];
for(let i=0; i<repeat; i++) {
    if(i===0) {
        inputData2.push(inputData.join(''));
    } else {
        inputData.splice(0,1);
        inputData2.push(inputData.join(''));
    }
}
inputData2.sort();
let output ='';
inputData2.map((ele,index) => {
    output += `${ele}\n`;
})
console.log(output.trim());