const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString();
const A = parseInt(inputData);
if(A>=90 && A<=100) {
    console.log('A');
} else if(A>=80 && A<90){
    console.log('B');
} else if(A>=70 && A<80){
    console.log('C');
} else if(A>=60 && A<70){
    console.log('D');
} else {
    console.log("F");
}