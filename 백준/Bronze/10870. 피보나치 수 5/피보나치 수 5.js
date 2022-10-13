const fs =require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let fN = new Array(N);
fN[0] = 0;
fN[1] = 1;
if(N>=2) {
    for(let i=2; i<N; i++) {
      fN[i] = fN[i-1] + fN[i-2];
    }
    console.log(fN[N-1]+fN[N-2]);
} else {
    console.log(fN[N]);
}

