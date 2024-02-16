import java.util.*;

class Solution {
    static int N,M;
    static int answer = 0;
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        int[][] sum = new int[N][M];
        makeSum(sum, skill);
        cal(board, sum);
        return answer;
    }
    
    static void cal(int[][] board, int[][] sum) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                board[i][j] += sum[i][j];
                if(board[i][j] >= 1) {
                    answer += 1;
                }
            }
        }
    }
    
    static void makeSum(int[][] sum, int[][] skill) {
        for(int i=0; i<skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if(type == 1) {
                degree *= -1;
            }
            sum[r1][c1] += degree;
            if(checkRange(r1, c2 + 1)) {
                sum[r1][c2 + 1] += degree * -1;
            }
            if(checkRange(r2 + 1, c1)) {
                sum[r2 + 1][c1] += degree * -1;
            }
            if(checkRange(r2 + 1, c2 + 1)) {
                sum[r2 + 1][c2 + 1] += degree;
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(j == 0) {
                    continue;
                }
                sum[i][j] += sum[i][j - 1];
            }
        }
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(j == 0) {
                    continue;
                }
                sum[j][i] += sum[j - 1][i];
            }
        }
    }
    
    static boolean checkRange(int r, int c) {
        if((0<= r && r <= N - 1) && (0<= c && c <= M - 1)) {
            return true;
        }
        return false;
    }
}