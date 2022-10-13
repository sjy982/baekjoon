const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let start = [];
let start_team = [];
let link_team = [];
let start_ablity = [];
let link_ablity = [];
let ablity = new Array(N);
for (let i = 0; i < N; i++) {
    ablity[i] = new Array(N);
}
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x => x * 1);
    for (let j = 0; j < input.length; j++) {
        ablity[i - 1][j] = input[j];
    }
}

let x = 2;
let y = N - x;
while (x <= y) {
    if (x === N / 2) {
        start.push(1);
    }
    DFS(x);
    team_ablity(start_team, 'start');
    team_ablity(link_team, 'link');
    x += 1;
    y = N - x;
    start_team = [];
    link_team = [];
}
function DFS(n) {
    if (start.length === n) {
        let link = [];
        for (let i = 1; i <= N; i++) {
            if (!start.includes(i)) {
                link.push(i);
            }
        }
        start_team.push([...start]);
        link_team.push([...link]);
        return;
    } else {
        for (let i = 1; i <= N; i++) {
            let top = start.length === 0 ? -1 : start[start.length - 1];
            if (!start.includes(i)) {
                if (top < i) {
                    start.push(i);
                    DFS(n);
                    start.pop();
                }
            }
        }
    }
}
function team_ablity(arr, team) {
    for (let k = 0; k < arr.length; k++) {
        let sum = 0;
        for (let i = 0; i < arr[k].length; i++) {
            for (let j = 0; j < arr[k].length; j++) {
                if (i !== j) {
                    sum += ablity[arr[k][i] - 1][arr[k][j] - 1];
                }
            }
        }
        if (team === 'start') {
            start_ablity.push(sum);
        } else {
            link_ablity.push(sum);
        }
    }
}

let answel = [];
for(let i=0; i<start_ablity.length; i++) {
    answel.push(Math.abs(start_ablity[i] - link_ablity[i]));
}
console.log(Math.min(...answel));