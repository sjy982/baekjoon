const fs = require('fs');
const inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let channel = inputData[0].split('').map(x => x * 1); //목표 채널
let brokenOfNumber = parseInt(inputData[1]); //고장난 개수
let answel;

if (brokenOfNumber === 0) {
    //버튼이 고장나지 않았다면 length 길이만큼 출력. length보다 + -로 접근하는게 더 빠른지 비교
    answel = channel.length;
    console.log(answel >= Math.abs(channel.join('') - 100) ? Math.abs(channel.join('') - 100) : answel);

} else if (brokenOfNumber === 10) {
    //모든 버튼이 고장났다면. + or -로만 접근해야됨.
    answel = Math.abs(100 - channel.join(''));
    console.log(answel);
} else {
    //버튼이 하나 이상 고장난 경우.
    let brokenNumber = inputData[2].split(' ').map(x => x * 1); //고장난 버튼의 배열
    let notBrokenNumber = []; //고장나지 않은 버튼 배열
    let button = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]; //모든 버튼
    button.map((number) => !brokenNumber.includes(number) && notBrokenNumber.push(number)); //notBrokenNumber을 채워줌
    //이제 channel과 notBrokenNumber을 비교하며 같은 값은 넣어주다가 같은 값이 없는 부분에서 channel보다 작으면서 근사값과 channel보다 크면서 근사값을 구할거임.
    let answelMinus = []; //channel 보다 작은 값.
    let answelPlus = []; //channel 보다 큰값.
    let differ = 1;
    let aml = channel.length; //answelMinus length
    let apl = channel.length; //answelPlus length
    for (let i = 0; i < channel.length; i++) {
        if (notBrokenNumber.includes(channel[i])) {
            //같은 값은 넣어줌.
            answelMinus[i] = channel[i];
            answelPlus[i] = channel[i];
        } else {
            //같지 않은 부분 
            pCheck = false; //플러스 구간 체크
            mCheck = false // 마이너스 구간 체크
            while (!pCheck || !mCheck) {
                if (!mCheck) {
                    let target = channel[i] - differ;
                    if (target <= 0 && i === 0 && aml > 1) {
                        aml -= 1;
                        for (let j = 0; j < aml; j++) {
                            answelMinus[j] = Math.max(...notBrokenNumber);
                        }
                        mCheck = true;
                    } else if (target < 0 && i !== 0) {
                        if (notBrokenNumber.includes(10 + target)) {
                            answelMinus[i] = 10 + target;
                            for (let j = i - 1; j >= 0; j--) {
                                let target2 = notBrokenNumber.filter(number => number < channel[j]);
                                if (target2.length !== 0) {
                                    if (Math.max(...target2) === 0 && j === 0) {
                                        aml -= 1;
                                        answelMinus = [];
                                        mCheck = true;
                                    } else {
                                        answelMinus[j] = Math.max(...target2);
                                        mCheck = true;
                                        break;
                                    }
                                } else if (target2.length === 0 && j === 0) {
                                    aml -= 1;
                                    answelMinus = [];
                                    mCheck = true;
                                } else if (target2.length === 0 && j !== 0) {
                                    answelMinus[j] = Math.max(...notBrokenNumber);
                                }
                            }
                        }
                    } else if (target < 0 && channel.length === 1) {
                        answelMinus = [];
                        aml = 0;
                        mCheck = true;
                    } else {
                        if (notBrokenNumber.includes(target)) {
                            answelMinus[i] = target;
                            mCheck = true;
                        }
                    }
                }
                if (!pCheck) {
                    let target = channel[i] + differ;
                    if (target >= 10 && differ !== 10) {
                        if (i === 0 && notBrokenNumber.filter(number => number !== 0).length !== 0) {
                            apl += 1;
                            pCheck = true;
                        } else {
                            if (notBrokenNumber.includes(target - 10)) {
                                answelPlus[i] = target - 10
                                for (let j = i - 1; j >= 0; j--) {
                                    let target2 = notBrokenNumber.filter(number => number > channel[j]);
                                    if (target2.length !== 0) {
                                        if (Math.min(...target2) === 0 && j === 0) {
                                            apl += 1;
                                            answelPlus = [];
                                            pCheck = true;
                                        } else {
                                            answelPlus[j] = Math.min(...target2);
                                            pCheck = true;
                                            break;
                                        }
                                    } else if (target2.length === 0 && j === 0) {
                                        apl += 1;
                                        answelPlus = [];
                                        pCheck = true;
                                    } else if (target2.length === 0 && j !== 0) {
                                        answelPlus[j] = Math.min(...notBrokenNumber);
                                    }
                                }
                            }
                        }
                    } else if (differ === 10) {
                        //10차이가 날수가 없음.
                        answelPlus = [];
                        apl = 0;
                        pCheck = true;
                    } else {
                        if (notBrokenNumber.includes(target)) {
                            answelPlus[i] = target;
                            pCheck = true;
                        }
                    }
                }
                differ += 1;
            }
            break;
        }
    }
    if (aml !== 0 && apl !== 0) {
        if (aml < channel.length) {
            for (let k = 0; k < aml; k++) {
                answelMinus[k] = Math.max(...notBrokenNumber);
            }
        } else {
            for (let k = answelMinus.length; k < channel.length; k++) {
                answelMinus[k] = Math.max(...notBrokenNumber);
            }
        }

        if (apl > channel.length) {
            for (let k = 0; k < apl; k++) {
                if (k !== 0) {
                    answelPlus[k] = Math.min(...notBrokenNumber);
                } else {
                    answelPlus[k] = Math.min(...notBrokenNumber.filter(number => number !== 0));
                }
            }
        } else {
            for (let k = answelPlus.length; k < channel.length; k++) {
                answelPlus[k] = Math.min(...notBrokenNumber);
            }
        }
    }
    if (aml === 0) {
        answel = apl + Math.abs(answelPlus.join('') - channel.join(''));
    } else if(apl === 0) {
        answel = aml + Math.abs(answelMinus.join('') - channel.join(''));
    } else if (answelPlus[0] === Infinity) {
        answel = aml + Math.abs(answelMinus.join('') - channel.join(''));
    } else {
        let pressM = aml + Math.abs(channel.join('') - answelMinus.join(''));
        let pressP = apl + Math.abs(channel.join('') - answelPlus.join(''));
        pressM >= pressP ? answel = pressP : answel = pressM;
    }
    console.log(answel >= Math.abs(100 - channel.join('')) ? Math.abs(100 - channel.join('')) : answel);
}
