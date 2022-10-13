const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData.shift().split(' ').map(x => x * 1);
let dp = new Array(N + 1);
for (let i = 1; i <= N; i++) {
    dp[i] = new Array(M + 1);
    dp[i].fill(0);
}
for (let i = 1; i <= N; i++) {
    let input = inputData[i - 1].split('').map(x => x * 1);
    let cMax = 0;
    let minArea = 0;
    let kMap = new Map();
    for (let j = 0; j < M; j++) {
        if (i === 1) {
            dp[i][j] = input[j]
            if(input[j] === 1) {
                minArea = 1;
            }
        } else {
            if (input[j] === 1) {
                dp[i][j] = dp[i - 1][j] + input[j];
                kMap.set(dp[i][j], 0);
                minArea = 1;
            }
        }
    }
    if(i !== 1){
        let keys = Array.from(kMap.keys());
        keys.sort((a,b) => {
            return b-a;
        });
        cMax = keys.length !== 0 ? find(dp[i], keys) : 0;
        dp[i][M] = Math.max(cMax, dp[i-1][M])
    } else {
        dp[i][M] = minArea;
    }
}
function find(arr, keys) {
    let bs = keys[0];
    let value = 0;
    let ind = 0;
    let end = false
    while (!end) {
        let cout = 0;
        bs = keys[ind];
        for (let i = 0; i < arr.length-1; i++) {
            if (arr[i] >= bs) {
                cout += 1;
                if (cout === bs) {
                    value = bs;
                    end = true;
                    return value * value;
                    break;
                }
            } else {
                cout = 0;
            }
        }
        if(ind===keys.length-1) {
            return 0;
            end = true;
            break;
        }
        ind += 1;
    }
}
console.log(dp[N][M]);