const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let first = inputData[0].split('').reverse();
let second = inputData[1].split('').reverse();
first = parseInt(first.join(''));
second = parseInt(second.join(''));
first > second ? console.log(first) : console.log(second);