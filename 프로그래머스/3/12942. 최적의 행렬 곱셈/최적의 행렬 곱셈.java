import java.io.*;
import java.util.*;

class Solution {
    static int N;
    public int solution(int[][] matrix_sizes) {
        N = matrix_sizes.length;
        int[][] dp = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i == j) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=0; i<=N-2; i++) {
            dp[i][i + 1] = matrix_sizes[i][0] * matrix_sizes[i][1] * matrix_sizes[i + 1][1];
        }
        
        for(int len=3; len<=N; len++) {
            //len 3부터 N까지 반복.
            for(int i=0; i<=N - len; i++) {
                int right = i + len - 1;
                for(int k=i; k<=right-1; k++) {
                    //k는 분할지점.
                    dp[i][right] = Math.min(dp[i][right],
                                           matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[right][1] + dp[i][k] + dp[k + 1][right]);
                }
                
            }
        }
        
        int answer = dp[0][N - 1];
        return answer;
    }
}