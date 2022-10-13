const fs = require('fs');
let inputData = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let NM = inputData.shift().split(' ').map(x=>x*1);
let N = NM[0];
let M = NM[1];
let pokemonMapA = new Map(); //alpa
let pokemonMapN = new Map(); //number
let searchPokemon = [];
let output = '';
for(let i=0; i<inputData.length; i++) {
    if(i<N) {
        pokemonMapA.set(inputData[i],i+1);
        pokemonMapN.set(i+1, inputData[i]);
    } else {
        searchPokemon.push(inputData[i]);
    }
}

searchPokemon.map((ele,index) => {
    if(isNaN(ele)) {
        //string
        output += `${pokemonMapA.get(ele)}\n`;
    } else {
        //number
        output += `${pokemonMapN.get(ele*1)}\n`;
    }
})

console.log(output.trim());