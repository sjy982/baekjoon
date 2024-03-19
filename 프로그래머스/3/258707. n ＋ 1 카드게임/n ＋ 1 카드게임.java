class Node {
    int v, c;
    Node(int v, int c) {
        this.v = v;
        this.c = c;
    }
}

class Solution {
    static Node[] cardsInfo;
    static int bc;
    static int N;
    public int solution(int coin, int[] cards) {
        N = cards.length;
        cardsInfo = new Node[N];
        bc = N/3 - 1;
        makeCardsInfo(cards);
        int zCout = countZero();
        int oCout = 0;
        int tCout = 0;
        
        int answer = 1;
        //라운드 진행
        for(int i=bc + 1; i<N; i+=2) {
            int aInd = i;
            int bInd = i + 1;
            if(cardsInfo[aInd].c <= bc) {
                oCout += 1;
            } else if(cardsInfo[aInd].c < aInd) {
                tCout += 1;
            }
            
            if(cardsInfo[bInd].c <= bc) {
                oCout += 1;
            } else if(cardsInfo[bInd].c < bInd) {
                tCout += 1;
            }
            
            if(zCout > 0) {
                answer += 1;
                zCout -= 1;
            } else if(oCout > 0 && coin > 0) {
                answer += 1;
                oCout -= 1;
                coin -= 1;
            } else if(tCout > 0 && coin >= 2) {
                answer += 1;
                tCout -= 1;
                coin -= 2;
            } else {
                break;
            }
        }
        
        return answer;
    }
    
    static int countZero() {
        int result = 0;
        for(int i=0; i<N; i++) {
            if(cardsInfo[i].c != -1 &&  i < cardsInfo[i].c) {
                if(i <= bc && cardsInfo[i].c <= bc) {
                    result += 1;
                }
            }
        }
        return result;
    }
    
    static void makeCardsInfo(int[] cards) {
        for(int i=0; i<cards.length; i++) {
            cardsInfo[i] = new Node(cards[i], findCombi(cards, i));
        }
    }
    
    static int findCombi(int[] cards, int ind) {
        for(int i=0; i<cards.length; i++) {
            if(i != ind && cards[i] + cards[ind] == N + 1) {
                return i;
            }
        }
        return -1;
    }
}