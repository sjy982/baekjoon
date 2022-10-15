const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = inputData[0].trim() * 1;
let tree = {};
let cut_node = new Array(N+1).fill(0);
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    tree[input[0]] = [input[1],input[2]];
    for(let j=0; j<input.length; j++) {
        //count node
        if(input[j] !== -1) {
            cut_node[input[j]] += 1;
        }
    }
}

let root_node = find_root(cut_node);
let lv_tree = Array.from(Array(N+1), () => Array());//레벨별 트리
let row = 1;//열번호
DFS(root_node, 1);

let max_obj = {lv: 0, w: 0};
for(let i=1; i<lv_tree.length; i++) {
    if(lv_tree[i].length === 0) break;
    let cur_w = lv_tree[i][lv_tree[i].length-1] - lv_tree[i][0] + 1;
    if(max_obj.w < cur_w) {
        //낮은 레벨 부터 반복하기 때문에 max_obj값에는 같은 max width을 갖는 레벨중에서도 가장 낮은 레벨이 저장됨.
        max_obj = {lv: i, w: cur_w};
    }
}
console.log(`${max_obj.lv} ${max_obj.w}`);

function DFS(node, lv) {
    if(node === -1) {
        return;
    }
    //중위 순회 방법 왼-루-오
    let[ln, rn] = tree[node];
    DFS(ln, lv + 1);
    lv_tree[lv].push(row);
    row+=1;
    DFS(rn, lv + 1);
}

function find_root(arr) {
    let rn;
    for(let i=1; i<arr.length; i++) {
        if(arr[i] === 1) {
           rn = i;
           break;
        }
    }
    return rn;
}