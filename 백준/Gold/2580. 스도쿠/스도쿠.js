const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let board = Array.from(Array(9), () => Array(9));
let blank = [];
let blank_visited = Array.from(Array(9), () => Array(9).fill(false));
for(let i=0; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        board[i][j] = input[j];
        if(input[j] === 0) {
            blank.push([j,i]);
        }
    }
}
if(blank.length !==0) {
    DFS(0);
}
let answel = '';
for(let i=0; i<board.length; i++) {
    for(let j=0; j<board[i].length; j++) {
        answel += `${board[i][j]} `
    }
    answel.trim();
    answel += '\n';
}
console.log(answel.trim());

function DFS(index) {
    let [x,y] = blank[index];
    let nums = find_nums(x,y);
    for(let i=0; i<nums.length; i++) {
        board[y][x] = nums[i];
        if(index+1 === blank.length) return; // 다음 탐색이 없다면 리턴.
        let next_ind = index + 1; //다음 탐색 인덱스
        DFS(next_ind); //하위 탐색
        let [nx, ny] = blank[next_ind]; //하위 탐색에 좌표값.
        if(board[ny][nx] !== 0) return; // 하위 탐색에서 성공했다면 리턴.
        board[y][x] = 0; //그게 아니라면 0값 넣어줌.
    }
}

function find_nums(x,y) {
    let num_arr = [];
    let num_check = new Array(10).fill(false);
    for(let i=0; i<9; i++) {
        if(board[y][i] !== 0) {
            num_check[board[y][i]] = true;
        }
        if(board[i][x] !== 0) {
            num_check[board[i][x]] = true;
        }
    }
    let sx = parseInt(x/3) * 3;
    let sy = parseInt(y/3) * 3;
    for(let i=sy; i<sy+3; i++) {
        for(let j=sx; j<sx+3; j++) {
            if(board[i][j] !== 0) {
                num_check[board[i][j]] = true;
            }
        }
    }
    for(let i=1; i<num_check.length; i++) {
        if(!num_check[i]) {
            num_arr.push(i);
        }
    }
    return num_arr;
}