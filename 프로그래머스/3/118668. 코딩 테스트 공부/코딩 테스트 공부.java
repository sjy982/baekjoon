import java.io.*;
import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int[][] dp = new int[151][151]; //Integer.MAX_VALUE는 없는 경우라고 생각하면 됨.
        init(dp);
        dp[alp][cop] = 0;
        for(int i=alp; i<=150; i++) {
            for(int j=cop; j<=150; j++) {
                if(dp[i][j] != Integer.MAX_VALUE) {
                    //경우가 존재한다면.
                    if(i < 150) {
                        //알고력을 높이는 경우.
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                    }
                    if(j < 150) {
                        //코딩력을 높이는 경우
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                    }
                    
                    //문제를 푸는 경우
                    for(int k=0; k<problems.length; k++) {
                        if((problems[k][0] <= i) && (problems[k][1] <= j)) {
                            //문제를 풀 수 있다면.
                            int nextAlgo = i + problems[k][2] >= 150 ? 150 : i + problems[k][2];
                            int nextCodi = j + problems[k][3] >= 150 ? 150 : j + problems[k][3];
                            int time = problems[k][4];
                            dp[nextAlgo][nextCodi] = Math.min(dp[nextAlgo][nextCodi], dp[i][j] + time);
                        }
                    }
                }
            }
        }
        
        int maxAlgo = -1;
        int maxCodi = -1;
        for(int i=0; i<problems.length; i++) {
            maxAlgo = Math.max(maxAlgo, problems[i][0]);
            maxCodi = Math.max(maxCodi, problems[i][1]);
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i=maxAlgo; i<=150; i++) {
            for(int j=maxCodi; j<=150; j++) {
                answer = Math.min(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
    
    static void init(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}