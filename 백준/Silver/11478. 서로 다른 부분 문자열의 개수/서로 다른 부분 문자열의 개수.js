const fs = require('fs');
let S = fs.readFileSync('/dev/stdin').toString().trim();
let map = new Map();
for(let i=0; i<S.length; i++) {
    for(let j=0; j<S.length-i; j++) {
        if(!map.has(S.substr(j,i+1))) {
            map.set(S.substr(j,i+1),1);
        } else {
            map.set(S.substr(j,i+1),map.get(S.substr(j,i+1)) + 1);
        }
    }
}
console.log(map.size);