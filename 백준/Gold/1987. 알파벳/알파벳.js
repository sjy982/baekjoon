const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [R,C] = inputData[0].trim().split(' ').map(x=>x*1);
let board = Array.from(Array(R), () => Array(C));
let visited = Array.from(Array(R), () => Array(C).fill(false));
let ab_check = new Array(26).fill(false);
let size_map = new Map();
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for(let j=0; j<input.length; j++) {
        board[i-1][j] = input[j];
        if(!size_map.has(input[j])) {
            size_map.set(input[j],true);
        }
    }
}
let max_cout = size_map.size;
let wx = [0,0,-1,1]; 
let wy = [-1,1,0,0];
let answel = 1;
let end = false;
visited[0][0] = true;
ab_check[board[0][0].charCodeAt(0) - 65] = true;
DFS(0,0,1);
console.log(answel);

function DFS(x,y,cout) {
    if(!end) {
        if(cout === max_cout) {
            answel = cout;
            end = true;
            return;
        } else {
            for(let i=0; i<4; i++) {              
                let nx = x + wx[i];
                let ny = y + wy[i];
                if((nx>=0 && nx<=C-1) && (ny>=0 && ny<=R-1)) {
                    if(!visited[ny][nx]) {
                        let al_ind = board[ny][nx].charCodeAt(0) - 65
                        if(!ab_check[al_ind]){
                            visited[ny][nx] = true;
                            ab_check[al_ind] = true;
                            DFS(nx,ny,cout+1);
                            visited[ny][nx] = false;
                            ab_check[al_ind] = false;
                        }
                    }
                }
            }
            if(answel < cout) {
              answel = cout;
            }
        }
    }
}
