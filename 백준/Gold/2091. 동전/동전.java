import java.io.*;
import java.util.*;

public class Main {
    static int X, A, B, C, D;
    static int dp[][];
    static final int[] coinCnt = new int[4];
    static final int[] cw = {1, 5, 10, 25}; //가중치
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        for(int i=0; i<4; i++) {
            coinCnt[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[X + 1][5]; //0 -> A, 1 -> B, 2 -> C, 3 -> D, 4 -> 총 개수;
        
        for(int i=1; i<=X; i++) {
            dp[i][4] = -1;
        }
        
        for(int i=1; i<=X; i++) {
            dp[i][4] = 0;
            for(int j=0; j<4; j++) {
                if(i - cw[j] < 0 || dp[i - cw[j]][4] == -1) {
                    continue;
                }
                if(dp[i - cw[j]][j] + 1 <= coinCnt[j]) {
                    //coin을 추가해도 정해진 개수를 초과하지 않는다면
                    if(dp[i - cw[j]][4] + 1 > dp[i][4]) {
                        //현재 코인 총 개수보다 크다면 업데이트
                        dpUpdate(j, dp[i - cw[j]], i);
                    }
                }
            }
            if(dp[i][4] == 0) {
                //update 안됨 만족하는 값이 없는거임
                dp[i][4] = -1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++) {
            sb.append(dp[X][i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    
    static void dpUpdate(int coinInd, int[] beforeDp, int updateInd) {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            if(i == coinInd) {
                dp[updateInd][i] = beforeDp[i] + 1;
            } else {
                dp[updateInd][i] = beforeDp[i];
            }
            cnt += dp[updateInd][i];
        }
        dp[updateInd][4] = cnt;
    }
}