const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let hop = 0;
for(let i=0; i<inputData.length; i++) {
    if(inputData[i]==="A"||inputData[i]==="B"||inputData[i]==="C") {
        hop += 3;
    } else if(inputData[i]==="D"||inputData[i]==="E"||inputData[i]==="F") {
        hop += 4;
    } else if(inputData[i]==="G"||inputData[i]==="H"||inputData[i]==="I") {
        hop += 5;
    } else if(inputData[i]==="J"||inputData[i]==="K"||inputData[i]==="L") {
        hop += 6;
    } else if(inputData[i]==="M"||inputData[i]==="N"||inputData[i]==="O") {
        hop += 7;
    } else if(inputData[i]==="P"||inputData[i]==="Q"||inputData[i]==="R"||inputData[i]==="S") {
        hop += 8;
    } else if(inputData[i]==="T"||inputData[i]==="U"||inputData[i]==="V") {
        hop += 9;
    } else if(inputData[i]==="W"||inputData[i]==="X"||inputData[i]==="Y"||inputData[i]==="Z") {
        hop += 10;
    } 
}
console.log(hop);