const fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let stack = [];
let output = '';
for(let i=0; i<N; i++) {
    if(inputData[i] === 'pop') {
        stack.length === 0 ? output += '-1\n' : output += `${stack.pop()}\n`;
    } else if(inputData[i] === 'size') {
        output += `${stack.length}\n`;
    } else if(inputData[i] === 'empty') {
        stack.length === 0 ? output += '1\n' : output +='0\n';
    } else if(inputData[i] === 'top') {
        stack.length === 0 ? output += '-1\n' : output += `${stack[stack.length-1]}\n`;
    } else {
        //push
        let pn = inputData[i].split(' ');
        stack.push(pn[1]*1);
    }
}
console.log(output.trim());
