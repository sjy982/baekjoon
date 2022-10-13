let fs =require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [N, M] = inputData[0].trim().split(' ').map(x=>x*1);
let office = new Array(N);
for(let i=0; i<N; i++) {
    office[i] = new Array(M);
}
for(let i=1; i<inputData.length; i++) {
    let input = inputData[i].trim().split(' ').map(x=>x*1);
    for(let j=0; j<input.length; j++) {
        office[i-1][j] = input[j];
    }
}
let cctv_arr = [];
for(let i=0; i<office.length; i++) {
    for(let j=0; j<office[i].length; j++) {
        if(1<=office[i][j] && office[i][j]<=5) {
            cctv_arr.push({cctv:office[i][j], x: j, y:i});
        }
    }
}
let bc_arr = []; //blind count arr;
if(cctv_arr.length >=1){
    bs_find(cctv_arr.length - 1, office);
} else {
    bc_arr.push(zero_cout(office));
}
function bs_find(index, of_arr) {
    let cctv = cctv_arr[index].cctv;
    let x = cctv_arr[index].x;
    let y = cctv_arr[index].y;
    if(index === 0) {
      if(cctv === 1) {
          for(let i=0; i<4; i++) {
              let copy = of_arr.map(v => [...v]);
              bc_arr.push(zero_cout(blind_check(cctv, x, y, i, copy)));
          }
      } else if(cctv === 2) {
          for(let i=0; i<2; i++) {
              let copy = of_arr.map(v=>[...v]);
              bc_arr.push(zero_cout(blind_check(cctv, x, y, i, copy)));
          }
           
      } else if(cctv === 3) {
          for(let i=0; i<4; i++) {
              let copy = of_arr.map(v => [...v]);
              bc_arr.push(zero_cout(blind_check(cctv, x, y, i, copy)));
          }
      } else if(cctv === 4) {
          for(let i=0; i<4; i++) {
              let copy = of_arr.map(v => [...v]);
              bc_arr.push(zero_cout(blind_check(cctv, x, y, i, copy)));
          }
      } else if(cctv === 5) {
              let copy = of_arr.map(v => [...v]);
              bc_arr.push(zero_cout(blind_check(cctv, x, y, 0, copy)));
      } 
    } else {
        if(cctv === 1) {
            for(let i=0; i<4; i++) {
                let copy = of_arr.map(v => [...v]);
                bs_find(index-1, blind_check(cctv, x, y, i, copy));
            }
        } else if(cctv === 2) {
            for(let i=0; i<2; i++) {
                let copy = of_arr.map(v => [...v]);
                bs_find(index-1, blind_check(cctv, x, y, i, copy));
            }
        } else if (cctv === 3) {
            for(let i=0; i<4; i++) {
                let copy = of_arr.map(v => [...v]);
                bs_find(index-1, blind_check(cctv, x, y, i, copy));
            }
        } else if(cctv === 4) {
            for(let i=0; i<4; i++) {
                let copy = of_arr.map(v => [...v]);
                bs_find(index-1, blind_check(cctv, x, y, i, copy));
            }
        } else if(cctv === 5) {
            let copy = of_arr.map(v => [...v]);
            bs_find(index-1, blind_check(cctv, x, y, 0, copy));
        }
    }
}
function blind_check(cctv,x,y,dir,of_arr) {
    if(cctv === 1) {
        if(dir === 0) {
            paint(x,y, 0, of_arr);
        } else if(dir === 1) {
            paint(x,y, 1, of_arr);
        } else if(dir === 2) {
            paint(x,y, 2, of_arr);
        } else if(dir === 3) {
            paint(x,y, 3, of_arr);
        }
    } else if(cctv === 2) {
        if(dir === 0) {
            //왼쪽 오른쪽
            paint(x,y,1,of_arr);
            paint(x,y,3,of_arr);
        } else if(dir === 1) {
            //위족 아래쪽
            paint(x,y,0,of_arr);
            paint(x,y,2,of_arr);
        }
    } else if(cctv === 3) {
        if(dir === 0) {
            //위쪽 오른쪽
            paint(x,y,0,of_arr);
            paint(x,y,1,of_arr);
        } else if(dir === 1) {
            //위쪽 왼쪽
            paint(x,y,0,of_arr);
            paint(x,y,3,of_arr);
        } else if(dir === 2) {
            //왼쪽 아래쪽
            paint(x,y, 3, of_arr);
            paint(x,y, 2, of_arr);
        } else if(dir === 3) {
            //아래쪽 오른쪽
            paint(x,y, 1, of_arr);
            paint(x,y, 2, of_arr);
        }
        
    } else if(cctv === 4) {
        if(dir === 0) {
            //위,오,왼
            paint(x,y,0,of_arr);
            paint(x,y,1,of_arr);
            paint(x,y,3,of_arr);
        } else if(dir === 1) {
            //위,왼,아
            paint(x,y,0,of_arr);
            paint(x,y,3,of_arr);
            paint(x,y,2,of_arr);
        } else if(dir === 2) {
            //왼,아,오
            paint(x,y,3,of_arr);
            paint(x,y,2,of_arr);
            paint(x,y,1,of_arr);
        } else if(dir === 3) {
            //위, 오, 아
            paint(x,y,0,of_arr);
            paint(x,y,1,of_arr);
            paint(x,y,2,of_arr);
        }
    } else if(cctv === 5) {
        //위,오,아,왼
        if(dir === 0) {
            paint(x,y,0,of_arr);
            paint(x,y,1,of_arr);
            paint(x,y,2,of_arr);
            paint(x,y,3,of_arr);
        }
    } 
    
    return of_arr;
}

function paint(x, y, dir, of_arr) {
    //dir 0 위쪽, dir 1 오른쪽, dir 2 아래, dir 3 왼쪽
    if(dir === 0) {
        for(let i=y-1; i>=0; i--) {
            if(of_arr[i][x] === 6) {
                break;
            } else if(of_arr[i][x] === 0) {
                of_arr[i][x] = '#';
            }
        }
    } else if(dir === 1) {
        for(let i=x+1; i<M; i++) {
            if(of_arr[y][i] === 6) {
                break;
            } else if(of_arr[y][i] === 0) {
                of_arr[y][i] = '#';
            }
        }
    } else if(dir === 2) {
        for(let i=y+1; i<N; i++) {
            if(of_arr[i][x] === 6) {
                break;
            } else if(of_arr[i][x] === 0) {
                of_arr[i][x] = '#';
            }
        }
    } else if(dir === 3) {
        for(let i=x-1; i>=0; i--) {
            if(of_arr[y][i] === 6) {
                break;
            } else if(of_arr[y][i] === 0) {
                of_arr[y][i] = '#';
            }
        }
    }
}

function zero_cout(arr) {
    let cout = 0;
    for(let j=0; j<N; j++) {
        for(let k=0; k<M; k++) {
            if(arr[j][k] === 0) {
                cout += 1;
            }
        }
    }
    return cout;
}
console.log(Math.min(...bc_arr));