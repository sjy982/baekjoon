const fs =require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let output = '';
const starArr = new Array(N);
let CN = 1;
for (var i = 0; i < N; i++) {
    starArr[i] = new Array(N);
}

markStar(N);
function markStar(n) {
    if(n===3) {
        for(let i =0; i<N; i++) {
            for(let j=0; j<N; j++) {
                if((i+1)%3===2) {
                  if((j+1)%3===2) {
                      starArr[i][j] = ' ';
                  }else {
                      starArr[i][j] = '*';
                  }
                }else {
                  starArr[i][j] = '*';
                }
            }
        }
    } else {
        markStar(n/3);
        for(let i=0; i<N; i++) {
            for(let j=0; j<N; j++) {
                if((i+1)%n>=n/3+1 && (i+1)%n<=(n/3)*2) {
                    if((j+1)%n>=n/3+1 && (j+1)%n<=(n/3)*2) {
                        starArr[i][j] = ' ';
                    }
                }
            }
        }
    }
}


for(let i=0; i<N; i++) {
    output += '\n';
    for(let j=0; j<N; j++) {
        output += starArr[i][j];
    }
}
console.log(output.trim());