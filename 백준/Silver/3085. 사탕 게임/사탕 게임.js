const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0] * 1;
let board_col = new Array(N);
let board_row = new Array(N);
let max_candy = 0;
for (let i = 0; i < N; i++) {
    board_col[i] = new Array(N);
    board_row[i] = new Array(N);
}
for (let i = 1; i < inputData.length; i++) {
    let input = inputData[i].trim().split('');
    for (let j = 0; j < input.length; j++) {
        board_col[i - 1][j] = input[j];
        board_row[j][i - 1] = input[j];
    }
}

for (let i = 0; i < board_col.length; i++) {
    for (let j = 0; j < board_col[i].length; j++) {
        if (i === 0) {
            if (j === 0) {
                candy_cout(board_col[i]);
            }
            candy_cout(board_row[j]);
        } else {
            candy_cout(board_col[i]);
        }
        //위치를 달리했을때
        if (j !== N - 1) {
            if (board_col[i][j] !== board_col[i][j + 1]) {
                let cur = board_col[i][j];
                let nxt = board_col[i][j + 1];
                let new_col = [...board_col[i]];
                let new_row1 = [...board_row[j]];
                let new_row2 = [...board_row[j + 1]];
                new_row1[i] = nxt;
                new_row2[i] = cur;
                new_col[j] = nxt;
                new_col[j + 1] = cur;
                candy_cout(new_col);
                candy_cout(new_row1);
                candy_cout(new_row2);
            }
        }
        if (i !== N - 1) {
            if (board_col[i][j] !== board_col[i + 1][j]) {
                let cur = board_col[i][j];
                let nxt = board_col[i + 1][j];
                let new_row = [...board_row[j]];
                let new_col1 = [...board_col[i]];
                let new_col2 = [...board_col[i + 1]];
                new_row[i] = nxt;
                new_row[i + 1] = cur;
                new_col1[j] = nxt;
                new_col2[j] = cur;
                candy_cout(new_row);
                candy_cout(new_col1);
                candy_cout(new_col2);
            }
        }
    }
}

function candy_cout(arr) {
    let cout = 1;
    for (let i = 0; i < arr.length; i++) {
        if (i !== 0) {
            if (arr[i - 1] === arr[i]) {
                cout += 1;
                if (i === arr.length - 1) {
                    if (max_candy < cout) {
                        max_candy = cout;
                    }
                }
            } else {
                if (max_candy < cout) {
                    max_candy = cout;
                }
                cout = 1;
            }
        }
    }
}
console.log(max_candy);