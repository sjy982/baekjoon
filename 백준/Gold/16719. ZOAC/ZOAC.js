const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('');
let idMap = new Map(); //inputData map
let sortIdMap = new Map(); //sorted inputData map
let outputMap = new Map(); //output
let inputData2 = [];
let output = '';
inputData.map((ele,index) => {
    inputData2.push({id: parseInt(index),content: ele});
});
inputData2.sort((a,b) => {
     if(a.content > b.content) return 1;
     if(a.content < b.content) return -1;
     if(a.content === b.content) return 0;
});
inputData2.map((ele,index) => {
  sortIdMap.set(index,ele); 
});
zoac();
console.log(output.trim());
function zoac() {
    if(sortIdMap.size === 0) {
        //base case
        return;
    } else {
        let outputArr = [];
        if(outputMap.size === 0) {
            outputMap.set(0, sortIdMap.get(0));
            sortIdMap.delete(0);
            outputArr.push(outputMap.get(0));
        } else {
            let nextKey;
            let beforeCount = 0;
            let check = false;
            sortIdMap.forEach((ele, index) => {
                let count = 0;
                outputMap.forEach((ele2,index2) => {
                    if(ele2.id<ele.id) {
                        count += 1;
                    }
                })
                if(count>beforeCount) {
                    beforeCount = count;
                    nextKey = index;
                    check = true;
                }
            });
            if(check === false){
               let leftKeys = Array.from(sortIdMap.keys());
               nextKey = leftKeys[0];
            } 
            outputMap.set(nextKey, sortIdMap.get(nextKey));
            sortIdMap.delete(nextKey);
            //---------------
            outputMap.forEach((ele, index) => {
                outputArr.push(ele);
            });
            outputArr.sort((a,b) => {
               return a.id - b.id; 
            });
        }
        outputArr.map((ele, index) => {
            output += `${ele.content}`
        })
        output += '\n';
        zoac();
    }
}