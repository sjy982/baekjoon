const fs =require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let H = 0;
let up = false;
let output = ''
for(let i=1; i<=N; i++) {
    H = H * 2 + 1;
    up = !up;
}
let middle = H-1;
let outputArr = new Array(H);
let xl = H * 2 - 1;
let xl2 = H;
for(let i=0; i<H; i++) {
    if(up) {
        outputArr[i] = new Array(xl2);
        outputArr[i].fill(' ');
        xl2 += 1;
    } else {
        outputArr[i] = new Array(xl);
        outputArr[i].fill(' ');
        xl -= 1;
    }
}
up ? markStar18(middle, 0, H, up) : markStar18(middle, H-1, H, up);
function markStar18(x, y, h, up) {
    if(h===0) {
        return;
    } else {
        if(up) {
            //삼각형이 위를 바라봄.
           let xl = x;
           let xr = x;
           let yn = y;
           while(yn<y+h) {
             if(yn === y) {
                 outputArr[yn][x] = '*';
                 xl -= 1;
                 xr += 1;
                 yn += 1
             } else if(yn ===y+(h-1)) {
                 for(let i=xl; i<=xr; i++) {
                     outputArr[yn][i] = '*';
                 }
                 yn += 1;
             } else {
                 outputArr[yn][xl] = '*';
                 outputArr[yn][xr] = '*';
                 xl -= 1;
                 xr += 1;
                 yn += 1;
                 
             }
           }
           let nextY = y+(h-1)/2 + ((h-1)/2-1); //다음 삼각형의 높이 - 1 값을 더해줌.
          markStar18(x, nextY, (h-1)/2, !up);
        } else {
            let xl = x;
            let xr = x;
            let yn = y;
            while(yn>y-h) {
                if(yn === y) {
                    outputArr[yn][x] = '*';
                    xl -= 1;
                    xr += 1;
                    yn -= 1;
                } else if(yn === y-(h-1)) {
                    for(let i=xl; i<=xr; i++) {
                        outputArr[yn][i] = '*';
                    }
                    yn -=1;
                } else {
                    outputArr[yn][xl] = '*';
                    outputArr[yn][xr] = '*';
                    xl -= 1;
                    xr += 1;
                    yn -= 1;
                }
            }
             let nextY = y-(h-1)/2 - ((h-1)/2-1); //다음 삼각형의 높이 - 1 값을 빼줌.
            markStar18(x, nextY, (h-1)/2, !up);
        }
    }
}
for(let i=0; i<H; i++) {
    if(i===H-1) {
        output += `${outputArr[i].join('')}`;
    } else {
        output += `${outputArr[i].join('')}\n`;
    }
}
console.log(output);