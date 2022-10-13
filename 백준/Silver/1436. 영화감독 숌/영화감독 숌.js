const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let count = 0;
for(let i=0; i<10000000; i++) {
    let check666 = i.toString();
    if(check666.includes('666')) {
        count += 1;
    }
    if(N===count) {
        console.log(i);
        break;
    }
    
}