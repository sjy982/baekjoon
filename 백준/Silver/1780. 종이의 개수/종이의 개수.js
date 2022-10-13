const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData.shift()*1;
let square = new Array(N);
let count = [0,0,0]; //-1,0,1
for(let i=0; i<N; i++) {
    square[i] = new Array(N);
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
    let sSquare = []; //small square
    for(let i=0; i<n; i++) {
        for(let j=0; j<n; j++) {
            total += square[i+y][j+x];
            sSquare.push(square[i+y][j+x]);
        }
    }
    if(sSquare.every(number => number === sSquare[0])) {
        //모두 같은 값이라면.
        let num = sSquare[0];
        if(num === -1) {
            count[0] += 1;
        }else if(num === 0) {
            count[1] += 1;
        }else if(num === 1) {
            count[2] += 1;
        }
    } else {
        let n3 = n/3;
        partSquare(x,y,n3);
        partSquare(x+n3,y,n3);
        partSquare(x+(2*n3),y,n3);
        
        partSquare(x,y+n3,n3);
        partSquare(x+n3,y+n3,n3);
        partSquare(x+(2*n3),y+n3,n3);
        
        partSquare(x,y+(2*n3),n3);
        partSquare(x+n3,y+(2*n3),n3);
        partSquare(x+(2*n3),y+(2*n3),n3);
    }
}
console.log(`${count[0]}\n${count[1]}\n${count[2]}`);
