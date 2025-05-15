import java.io.*;
import java.util.*;

class State {
    int len, lastInd;
    boolean isComeOut; //공통된 수가 나왔는지?
    State(int len, int lastInd, boolean isComeOut) {
        this.len = len;
        this.lastInd = lastInd;
        this.isComeOut = isComeOut;
    }
}

class Solution {
    public int solution(int[] a) {
        if(a.length == 0) {
            return 0;
        }
        State[] dp = new State[500000];
        
        int nextStartIndex = 1;
        for(int i=1; i<a.length; i++) {
            if(a[i] != a[0]) {
                dp[a[0]] = new State(2, i, false);
                dp[a[i]] = new State(2, i, false);
                nextStartIndex = i + 1;
                break;
            }
        }
        if(nextStartIndex == 1) {
            return 0;
        }
        
        for(int i=nextStartIndex; i<a.length; i++) {
            if(dp[a[i]] == null) {
                //없다면. 처음 나온 수임.
                dp[a[i]] = new State(2, i, false);
            } else {
                //있다면. 이미 나온 수임.
                if(dp[a[i]].isComeOut) {
                    //이미 공통된 수가 포함되어 있다면.
                    int cnt = i - dp[a[i]].lastInd - 1; //3 x x x x 3 -> 3 사이에 수의 개수를 의미함.
                    if(cnt == 1) {
                        //하나인 경우는 +2만하고 여전히 true를 유지한다.
                        dp[a[i]].len += 2;
                    } else if(cnt >= 2) {
                        //두개인 경우는 +4하고, false로 변환.
                        dp[a[i]].len += 4;
                        dp[a[i]].isComeOut = false;
                    }
                } else {
                    //공통된 수가 포함되어 있지 않다면
                    if(dp[a[i]].lastInd + 1 == i) {
                        //lastInd 바로 다음으로 나왔으면 여전히 홀수 개수를 유지함.
                        dp[a[i]].isComeOut = true;
                    } else {
                        //lastInd 다음에 같지 않은 값을 포함할 수 있음. 짝수 개수 유지 가능.
                        dp[a[i]].len += 2; //2개 증가.
                    }
                }
                dp[a[i]].lastInd = i;
            }
        }
        
        int answer = -1;
        for(int i=0; i<500000; i++) {
            if(dp[i] == null) {
                continue;
            }
            
            if(dp[i].isComeOut) {
                //공통된 수가 이미 나온경우 lastIndex로 len를 늘려줄지 판단해 줄 수 있음.
                if(dp[i].lastInd < a.length - 1) {
                    // lastInd가 a의 마지막 인덱스보다 작은 값을 가져야됨. 그래야 짝을 지어줄 수 있음.
                    dp[i].len += 2;
                }
            }
            answer = Math.max(answer, dp[i].len);
        }
        
        return answer;
    }
}