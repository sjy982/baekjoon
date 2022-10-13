console.log(selfNumber());

function selfNumber() {
    let selfNumber = [];
    let notSelfNumberArray =[];
    let answer ='';
    for(let i =1; i<=10000; i++) {
        let currentNumber = i.toString().split('');
        let length = currentNumber.length;
        let notSelfNumber = parseInt(i);
        for(let j=0; j<length; j++) {
            notSelfNumber += parseInt(currentNumber[j]);
        }
        notSelfNumberArray.push(notSelfNumber);
    }
    for(let j=1; j<=10000; j++) {
        if(!notSelfNumberArray.includes(j)) {
            answer += `${j}\n`
        }
    }
    return answer;
}