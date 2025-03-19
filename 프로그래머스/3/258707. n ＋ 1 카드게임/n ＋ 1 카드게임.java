import java.io.*;
import java.util.*;

class Solution {
    static int N, tSum; //target sum
    public int solution(int coin, int[] cards) {
        N = cards.length;
        tSum = N + 1;
        HashMap<Integer, Boolean> deck = new HashMap<>(); //현재 덱
        int pCnt = 0; //진행할 수 있는 횟수
        
        HashMap<Integer, Boolean> fDeck = new HashMap<>(); //미래 덱
        int fPCnt = 0; //미래 진행할 수 있는 횟수.
        
        for(int i=0; i<N/3; i++) {
            int pair = tSum - cards[i];
            if(deck.get(pair) == null) {
                //내 짝이 아직 없다면.
                deck.put(cards[i], true);
            } else {
                //짝이 있다면.
                deck.remove(pair); //삭제
                pCnt += 1; //진행 횟수 증가.
            }
        }
        
        //이제 라운드 진행.
        int curRound = 1; //현재 라운드.
        int cursor = N/3;
        while(true) {
            if(cursor < N) {
                //cursor가 N보다 작다면
                //두 장을 뽑는다.
                ArrayList<Integer> list = new ArrayList<>();
                list.add(cards[cursor]);
                list.add(cards[cursor + 1]);
                cursor += 2;
                
                for(int i=0; i<2; i++) {
                    int num = list.get(i);
                    int pair = tSum - num; //짝 넘버.
                    if(deck.get(pair) != null && 1 <= coin) {
                        //짝이 있으면서, 코인이 하나 이상 있다면.
                        deck.remove(pair); //짝 지워주고.
                        coin -= 1; //코인 하나 줄여주고,
                        pCnt += 1; //진행 횟수 증가.
                    } else if(deck.get(pair) == null) {
                        //짝이 현재 덱에 없다면. 미래 덱에서 찾아본다.
                        if(fDeck.get(pair) != null) {
                            //미래 덱에 있다면.
                            fDeck.remove(pair); //짝 지워주고.
                            fPCnt += 1; //나중에 사용될 수 있는 진행 횟수를 증가시킨다. 이 횟수를 사용하려면 coin 2개 필요.
                        } else {
                            //미래 덱에 없다면. 넣어준다.
                            fDeck.put(num, true);
                        }
                    }
                }
            } else {
                break;
            }
            
            //카드 두 개를 뽑고, 처리가 되면 이제 라운드를 진행할 수 있는 지를 체크한다.
            if(pCnt >= 1) {
                //진행 가능.
                pCnt -= 1; //감소
                curRound += 1; //다음 라운드
            } else {
                //pCnt가 0이라면.
                //fPCnt로 가능한지 봐야됨.
                if(fPCnt >= 1) {
                    //fPCnt를 사용하기 위해서는 2개의 코인이 필요하다.
                    if(coin >= 2) {
                        fPCnt -= 1;
                        coin -= 2;
                        curRound += 1;
                    } else {
                        //코인이 두 개 이상 없다면. 더 이상 진행 불가.
                        break;
                    }
                } else {
                    //fPCnt도 없다면. 진행 불가.
                    break;
                }
            }
        }
        
        curRound = curRound > N/2 ? N/2 : curRound;
        return curRound;
    }
}