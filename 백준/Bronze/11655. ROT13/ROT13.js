const fs= require('fs');
let inputData =  fs.readFileSync('/dev/stdin').toString().split('');
let output = '';
for(let i=0; i<inputData.length; i++) {
    if(inputData[i].charCodeAt(0)>=65 && inputData[i].charCodeAt(0)<=90) {
        if(inputData[i].charCodeAt(0)+13 > 90) {
            output += String.fromCharCode((inputData[i].charCodeAt(0) + 13) - 90 +64);
        } else {
            output += String.fromCharCode(inputData[i].charCodeAt(0) + 13);
        }
    } else if(inputData[i].charCodeAt(0)>=97 && inputData[i].charCodeAt(0)<=122) {
        if(inputData[i].charCodeAt(0)+13 > 122) {
            output += String.fromCharCode((inputData[i].charCodeAt(0) + 13) - 122 +96);
        } else {
            output += String.fromCharCode(inputData[i].charCodeAt(0) + 13);
        }
    } else {
        output += inputData[i];
    }
}
console.log(output);