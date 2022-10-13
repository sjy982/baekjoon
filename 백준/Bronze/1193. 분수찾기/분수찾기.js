const fs = require('fs');
let N = fs.readFileSync('/dev/stdin').toString().trim()*1;
let repeat = 1;
let mole = 1; //분자
let deno = 2; //분모
let denoUp = false; //분모는 내려가고 분자는 올라감
let cout = 2; //count
let end = false;
if(N === 1) {
    console.log(`1/1`);
} else {
    while(!end) {
       for(let j=0; j<repeat; j++) {
           if(cout === N) {
               end = true;
               break;
           } else {
               if(denoUp) {
                  //분모는 올라가고 분자는 내려감.
                  deno += 1;
                  mole -= 1;
               } else {
                  //분모는 내려가고 분자는 올라감.
                  deno -= 1;
                  mole += 1;
               }
               
           }
           cout += 1;
       }
       if(cout === N) {
           end = true;
           break;
       } else {
           if(denoUp) {
               //분모만 올리고
               deno += 1;
           } else {
               //분자만 올림.
               mole += 1;
           }
           cout += 1;
           repeat += 1;
           denoUp === false ? denoUp = true : denoUp = false;
       }
    };
    console.log(`${mole}/${deno}`)
}
