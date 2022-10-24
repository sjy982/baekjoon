const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim() * 1;
let qm_col = new Array(N).fill(false);
let qm_rc = [];
let answel = 0;
DFS(0);
console.log(answel);

function DFS(r) {
    if (r === N) {
        answel += 1;
        return;
    } else {
        for (let c = 0; c < N; c++) {
            if (q_check(r, c)) {
                qm_col[c] = true;
                qm_rc.push([r, c]);
                DFS(r + 1);
                qm_col[c] = false;
                qm_rc.pop();
            }
        }
    }
}

function q_check(x, y) {
    //행 체크
    if (qm_col[y]) {
        return false;
    }
    //대각선 체크
    for (let i = 0; i < qm_rc.length; i++) {
        let [qx, qy] = qm_rc[i];
        if(Math.abs(qx-x) === Math.abs(qy-y)) {
            return false;
        }
    }
    return true;
}