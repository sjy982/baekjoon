import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            String strBinary = Long.toBinaryString(numbers[i]);
            int pbLen = findPerfectBinaryNodeCnt(strBinary.length());
            
            int[] pftBinary = new int[pbLen]; //포화이진트리.
            for(int j=pbLen - strBinary.length(); j <= pbLen - 1; j++) {
                pftBinary[j] = strBinary.charAt(j - (pbLen - strBinary.length())) - '0';
            }
            
            int rootInd = pbLen / 2; //루트 인덱스.
            if(checkPosible(0, pbLen - 1, pftBinary, false)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    static boolean checkPosible(int startInd, int endInd, int[] pftBinary, boolean isParentNodeZero) {
        int rootInd = (startInd + endInd) / 2;
        if(isParentNodeZero && pftBinary[rootInd] == 1) {
            //부모 노드가 0인데 현재 rootNode가 1이라면 불가능함.
            return false;
        }
        
        if(startInd == endInd) {
            //하나만 남았을 때, 만약 부모 노드가 0이 아니라면 여기에는 어떠한 값이 와도 상관 없음.
            //부모 노드가 0일 때는 1이 오면 안됨 그 조건은 앞에서 체크했기 때문에 상관 x
            return true;
        }
        
        //중심을 기준으로 좌우 분할.
        boolean nextIsParentNodeZero = false;
        if(pftBinary[rootInd] == 0) {
            //0이라면 이제 어떠한 자식 노드도 1값을 가지면 안됨.
            nextIsParentNodeZero = true;
        }
        
        if(!checkPosible(startInd, rootInd - 1, pftBinary, nextIsParentNodeZero)) return false;
        if(!checkPosible(rootInd + 1, endInd, pftBinary, nextIsParentNodeZero)) return false;
        
        return true;
    }
    
    
    static int findPerfectBinaryNodeCnt(int len) {
        int result = 1;
        int inc = 2;
        while(true) {
            if(len <= result) {
                break;
            }
            result+= inc;
            inc *= 2;
        }
        return result;
    }
}