const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim();
let curData = inputData;
const croatia =['c=','c-','dz=','d-','lj','nj','s=','z='];
let count =0;
for(let i=0; i<croatia.length; i++) {
    for(let j=0; j<inputData.length; j++) {
        if(curData.indexOf(croatia[i])>=0) {
        //croatia 문자임
        const startIndex = curData.indexOf(croatia[i]);
        const lastIndex = startIndex + croatia[i].length;
        curData = `${curData.slice(0,startIndex)} ${curData.slice(lastIndex, curData.length)}`
        count += 1;
        }
    }
}
for(let k=0; k<curData.length; k++) {
    if(curData[k] !==' '){
        count +=1;
    }
}
console.log(count);