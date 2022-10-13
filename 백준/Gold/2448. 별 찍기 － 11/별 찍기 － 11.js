const fs = require('fs');
let H = fs.readFileSync('/dev/stdin').toString().trim() * 1; //높이
let outputArr = new Array(H);
let output = '';
for (let i = 0; i < H; i++) {
    outputArr[i] = new Array(H*2-1);
    outputArr[i].fill(' ');
};
markStar11((H*2)/2-1,0, H);
function markStar11(x, y, h) {
    //x,y => 꼭지점 좌표 
    //h => 높이
    if(h===3) {
        //그려주면됨.
        outputArr[y][x] = '*';
        outputArr[y+1][x-1] = '*';
        outputArr[y+1][x+1] = '*';
        for(let i=x-2; i<=x+2; i++) {
            outputArr[y+2][i] = '*';
        }
    } else {
        markStar11(x,y,h/2); //상단
        markStar11(x-3*h/2/3,y+h/2,h/2);
        markStar11(x+3*h/2/3,y+h/2,h/2);
    }
}

for (let i = 0; i < H; i++) {
    if(i === H-1) {
        output += `${outputArr[i].join('')}`;
    } else {
        output += `${outputArr[i].join('')}\n`;
    }
}
console.log(output);