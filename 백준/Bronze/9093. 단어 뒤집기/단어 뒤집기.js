const fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let output = '';
for(let i=0; i<N; i++) {
    const inputData2 = inputData[i].split(' ');
    for(let j=0; j<inputData2.length; j++) {
        let reversedString = inputData2[j].split('').reverse().join('');
        output += `${reversedString} `;
    }
    output += '\n';
}
console.log(output.trim());