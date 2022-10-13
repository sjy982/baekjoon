const fs = require('fs');
let n = fs.readFileSync('/dev/stdin').toString().trim()*1;
let output = '';

output = `${Math.pow(2, n)-1}\n`; //하노이 일반항.
hanoi(n, 1, 3, 2);
function hanoi(N, start, to, wap) { //N원반 개수, 시작, 목표위치, waypoint 경유지
    if(N===1) {
        output += `${start} ${to}\n`; //1원반을 1에서 3으로 옮겨라 base case 
    } else {
        hanoi(N-1, start, wap, to);
        output += `${start} ${to}\n`;
        hanoi(N-1, wap, to, start);
    }
}
console.log(output.trim());
