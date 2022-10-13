const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x => x * 1);
let paper = new Array(N);
for (let i = 0; i < N; i++) {
    paper[i] = new Array(M);
}
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for (let j = 0; j < input.length; j++) {
        paper[i - 1][j] = input[j];
    }
}
let answel = -1;
DFS(0, [], 0, 0);
console.log(answel);
function DFS(sum, index, i, j) {
    if (index.length === N * M) {
        if(answel < sum) {
            answel = sum;
        }
        return;
    } else {
        let repeat = (N - i) + (M - j) - 1;
        if (!index.includes(`${i}${j}`)) {
            for (let k = 0; k < repeat; k++) {
                let ni = i;
                let nj = j;
                let ind = [...index];
                let check = true;
                let cn = '';
                if (j + k >= M) {
                    let ei = i + (j + k - M) + 1;
                    for (let h = i; h <= ei; h++) {
                        if (index.includes(`${h}${j}`)) {
                            check = false;
                            break;
                        }
                        ind.push(`${h}${j}`);
                        cn += paper[h][j];
                    }
                } else if (j + k < M) {
                    let ej = j + k;
                    for (let h = j; h <= ej; h++) {
                        if (index.includes(`${i}${h}`)) {
                            check = false;
                            break;
                        }
                        ind.push(`${i}${h}`);
                        cn += paper[i][h];
                    }
                }
                if (nj + 1 >= M) {
                    ni += 1;
                    nj = 0;
                } else if (nj + 1 < M) {
                    nj += 1;
                }
                if (check) {
                    DFS(sum + parseInt(cn), ind, ni, nj);
                } 
            }
        } else {
            let ni = i;
            let nj = j;
            if(nj + 1 >= M) {
                ni += 1;
                nj = 0;
            } else {
                nj += 1;
            }
            DFS(sum,index, ni,nj);
        }
    }
}