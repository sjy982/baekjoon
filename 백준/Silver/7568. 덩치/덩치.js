const fs =require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().split('\n');
let N = inputData[0] * 1;
let answel= '';

for(let i = 0; i<N; i++) {
    let person1 = inputData[i+1].split(' ').map(x => x*1);
    let k = 0;
    for(let j = 0; j<N; j++) {
        if(i !== j) {
            let person2 = inputData[j+1].split(' ').map(x => x*1);
            if(person1[0]<person2[0] && person1[1]<person2[1]) {
                k += 1;
            }
        }
    }
    answel +=`${k+1} `;
}
console.log(answel.trim());