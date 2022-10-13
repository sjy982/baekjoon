const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(x=>x*1);
let remain = [];
let map = new Map();
inputData.map((ele, index) => {
    map.set(ele%42,ele%42);
});
console.log(map.size);