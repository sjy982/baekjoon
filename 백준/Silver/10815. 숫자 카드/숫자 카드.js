const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let haveNumber = inputData[1].split(' ').map(x=>x*1);
let M = inputData[2] * 1;
let searchNumber = inputData[3].split(' ').map(x=>x*1);
let output = '';
haveNumber.sort((a,b) => {
    return a-b;
})

searchNumber.map((ele,index) => {
    output += `${binarySearch(haveNumber,ele)} `;
})

console.log(output.trim());

function binarySearch(HN,n) {
    let left = 0; //left 값
    let right = HN.length-1//right 값
    let mid = Math.round((left+right)/2);
    let target = n;
    while(left<right-1) {
        if(n<HN[mid]) {
            right = mid;
            mid = Math.round((left+right)/2);
        } else if(n>HN[mid]) {
            left = mid;
            mid = Math.round((left+right)/2);
        } else if(HN[mid] === n) {
            return 1;
        }
    }
    if(HN[left]===n || HN[right]===n) {
        return 1;
    } else {
        return 0;
    }
}