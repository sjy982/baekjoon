const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let k_cl = 3; //current length
let k_bl = 0; //before length
let k = 0;
let nN;
let output;
let end = false;
while (!end) {
    if (k_cl < N) {
        k_bl = k_cl;
        k_cl = k_bl * 2 + k + 4;
        k += 1;
    } else {
        end = true;
    }
}
findMoo(k, k_cl, N);
console.log(output.trim());
function findMoo(k, kcl, n) {
    if (n >= k_bl + 1 && n <= k_bl + k + 3) {
        if(k_bl + 1 === n) {
            output = 'm'
        } else {
            output = 'o'
        }
    } else {
        if (n > k_bl + k + 3) {
            //오른쪽
            nN = n - k_bl - (k + 3);
        } else {
            //왼쪽
            nN = n;
        }
        k_cl = (k_cl - (k + 3)) / 2;
        k -= 1;
        k_bl = Math.floor((k_cl - (k + 3)) / 2);
        findMoo(k, k_cl, nN);
    }
}

