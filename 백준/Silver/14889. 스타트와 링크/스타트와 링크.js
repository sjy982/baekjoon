const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let start = [1]; //1값은 고정
let start_team = [];
let link_team = [];
let answel = [];
let ablity = new Array(N);
for(let i=0; i<N; i++) {
    ablity[i] = new Array(N);
}
for( let i =1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        ablity[i-1][j] = input[j];
    }
}
DFS();
function DFS() {
    if(start.length === N/2) {
        let link = [];
        for(let i=1; i<=N; i++) {
            if(!start.includes(i)) {
                link.push(i);
            }
        }
        start_team.push([...start]);
        link_team.push([...link]);
        return;
    } else {
        for(let i=1; i<=N; i++) {
            if(!start.includes(i)) {
                if(start[start.length - 1] < i) {
                    start.push(i);
                    DFS();
                    start.pop();
                }
            }
        }
    }
}
for(let i=0; i<start_team.length; i++) {
    let sum_start = 0;
    let sum_link = 0;
    for(let j=0; j<start_team[i].length; j++) {
        for(let k=0; k<start_team[i].length; k++) {
            if(j!==k) {
                sum_start += ablity[start_team[i][j]-1][start_team[i][k]-1];
                sum_link += ablity[link_team[i][j]-1][link_team[i][k]-1];
            }
        }
    }
    answel.push(Math.abs(sum_start - sum_link));
}
console.log(Math.min(...answel));