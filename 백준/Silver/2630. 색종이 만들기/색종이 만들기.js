const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let square = new Array(N);
let white = 0;
let blue = 0;
for(let i=0; i<N; i++) {
    square[i]= new Array(N);
}
for(let i=0; i<N; i++) {
    let inputData2 = inputData[i].split(' ').map(x=>x*1);
    for(let j=0; j<N; j++) {
        square[i][j] = inputData2[j];
    }
}
partSquare(0,0,N);
function partSquare(x, y, n) {
    let total = 0;
    for(let i=0; i<n; i++) {
        for(let j=0; j<n; j++) {
            total += square[y+i][x+j];
        }
    }
    if(total === 0) {
        white += 1;
    } else if(total === n*n) {
        blue += 1;
    } else {
        let n2 = n/2
        partSquare(x, y, n2);
        partSquare(x+n2, y, n2);
        partSquare(x, y+n2, n2);
        partSquare(x+n2, y+n2, n2);
    }
}
console.log(`${white}\n${blue}`);