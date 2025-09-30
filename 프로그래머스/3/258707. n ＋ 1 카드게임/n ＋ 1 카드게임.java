import java.util.*;

class Solution {
    static int N;
    public int solution(int coin, int[] cards) {
        N = cards.length;
        int pass = 0;
        int passC1 = 0;
        int passC2 = 0;
        boolean[] deck = new boolean[N + 1];
        
        for(int i=0; i < N/3; i++) {
            deck[cards[i]] = true;
            int target = N + 1 - cards[i];
            if(deck[target]) {
                pass += 1;
            }
        }
        
        //라운드 진행
        boolean[] dropDeck = new boolean[N + 1]; //코인 하나를 소모해서 가질 수 있는 deck
        int answer = 1;
        for(int i=N/3; i < N; i+=2) {
            int[] draw = {cards[i], cards[i + 1]};
            for(int j=0; j<2; j++) {
                dropDeck[draw[j]] = true;
                int[] result = findPassCoin(draw[j], N, deck, dropDeck);
                passC1 += result[0];
                passC2 += result[1];
            }
            
            //라운드 진행가능한지
            if(pass >= 1) {
                pass -= 1;
                answer += 1;
                continue;
            }
            
            //coin 하나 소모
            if(coin >= 1 && passC1 >= 1) {
                coin -= 1;
                passC1 -= 1;
                answer += 1;
                continue;
            }
            
            //coin 두개 소모
            if(coin >= 2 && passC2 >= 1) {
                coin -= 2;
                passC2 -= 1;
                answer += 1;
                continue;
            }
            
            // 불가능한 경우임 game over
            break;
        }
        
        return answer;
    }
    
    static int[] findPassCoin(int card, int N, boolean[] deck, boolean[] dropDeck) {
        int[] result = new int[2];
        int target = N + 1 - card;
        if(deck[target]) {
            result[0] += 1;
        } else {
            //coin 하나로 불가능하다면
            if(dropDeck[target]) {
                result[1] += 1;
            }
        }
        return result;
    }
    
}