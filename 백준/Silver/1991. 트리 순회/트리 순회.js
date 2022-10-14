const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let tree = {};
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ');
    tree[input[0]] = [input[1],input[2]]; //부모 노드를 key 값으로 자식 노드를 value 값으로 저장.
}
let answel = '';

preOrder('A'); //항상 A는 루트 노드가 됨.
answel += '\n';

inOrder('A');
answel += '\n';

postOrder('A');

function preOrder(pn) {
    //전위 순회
    if(pn === '.') { 
        //재귀의 종료 조건
        return;
    }
    let [lcn, rcn] = tree[pn]; //왼쪽 자식 노드, 오른쪽 자식 노드
    answel += pn;
    preOrder(lcn);
    preOrder(rcn);
}

function inOrder(pn) {
    //중위 순회
    if(pn === '.') {
        //재귀의 종료 조건
        return;
    }
    let [lcn, rcn] = tree[pn]; //왼쪽 자식 노드, 오른쪽 자식 노드
    inOrder(lcn);
    answel += pn;
    inOrder(rcn);
}

function postOrder(pn) {
    //후위 순회
    if(pn === '.') {
        //재귀의 종료 조건
        return;
    }
    let [lcn, rcn] = tree[pn]; //왼쪽 자식 노드, 오른쪽 자식 노드
    postOrder(lcn);
    postOrder(rcn);
    answel += pn;
}

console.log(answel);