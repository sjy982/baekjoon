const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let sn = inputData[1].trim().split(' ').map(x=>x*1);
let input_oper = inputData[2].trim().split(' ').map(x=>x*1);
// 0 => 플러스, 1 => 마이너스, 2 => 곱, 3 => 나누기
let oper = [];
input_oper.map((ele,index) => {
    for(let i=0; i<ele; i++) {
        oper.push(index);
    }
});
let visited = new Array(oper.length).fill(false);
let answel = [];
let result = [];
DFS();
console.log(`${Math.max(...answel)}\n${Math.min(...answel)}`);

function DFS() {
    if(result.length === oper.length) {
        let value = sn[0];
        for(let i=1; i<sn.length; i++) {
            value = calcul(value, sn[i], result[i-1]);
        }
        answel.push(value);
    } else {
        let over = [];
        for(let i=0; i<oper.length; i++) {
            let over_top = over.length === 0 ? -1 : over[over.length-1];
            if(!visited[i] && over_top !== oper[i]) {
                visited[i] = true;
                result.push(oper[i]);
                DFS();
                visited[i] = false;
                result.pop(oper[i]);
                over.push(oper[i]);
            }
        }
    }
}

function calcul(num1, num2, oper) {
    let value = 0;
    if(oper === 0) {
       value = num1 + num2
    } else if(oper === 1) {
        value = num1 - num2;
    } else if(oper === 2) {
        value = num1 * num2;
    } else if(oper === 3) {
        value = parseInt(num1 / num2);
    }
    return value;
}