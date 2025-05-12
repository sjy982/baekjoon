import java.io.*;
import java.util.*;

class Solution {
    static int maxMin;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += 10;
        t1 += 10;
        t2 += 10;
        maxMin = onboard.length - 1;
        int[][][] dp = new int[maxMin + 1][51][4]; //0은 에어컨 off, 1은 온도 유지, 2는 온도 높임, 3은 온도 낮춤
        init(dp);
        
        dp[0][temperature][0] = 0;
        dp[0][temperature][1] = 0;
        if(temperature != 50) {
            dp[0][temperature][2] = 0;
        }
        
        if(temperature != 0) {
            dp[0][temperature][3] = 0;
        }
        // 1 ~ maxMin까지 갈거고.
        
        for(int i=1; i<=maxMin; i++) {
            for(int j=0; j<=50; j++) {
                for(int k=0; k<=3; k++) {
                    if(dp[i - 1][j][k] != Integer.MAX_VALUE) {
                        //전에 상태가 있을 때만 들어간다.
                        if(k == 0) {
                            //에어컨 off -> 전력이 들지 않는다.
                            if(j < temperature) {
                                //실내온도가 temperature보다 낮다면 실내온도는 높아진다.
                                if(!posible(onboard[i], j + 1, t1, t2)) {
                                    continue;
                                }
                                dp[i][j + 1][0] = Math.min(dp[i][j + 1][0], dp[i - 1][j][k]);
                                dp[i][j + 1][1] = Math.min(dp[i][j + 1][1], dp[i - 1][j][k]);
                                if((j + 1) != 50) {
                                    dp[i][j + 1][2] = Math.min(dp[i][j + 1][2], dp[i - 1][j][k]);
                                }
                                dp[i][j + 1][3] = Math.min(dp[i][j + 1][3], dp[i - 1][j][k]);
                            } else if(j > temperature) {
                                //실내온도가 temperature보다 높다면 실내온도는 낮아진다.
                                if(!posible(onboard[i], j - 1, t1, t2)) {
                                    continue;
                                }
                                dp[i][j - 1][0] = Math.min(dp[i][j - 1][0], dp[i - 1][j][k]);
                                dp[i][j - 1][1] = Math.min(dp[i][j - 1][1], dp[i - 1][j][k]);
                                dp[i][j - 1][2] = Math.min(dp[i][j - 1][2], dp[i - 1][j][k]);
                                if((j - 1) != 0) {
                                    dp[i][j - 1][3] = Math.min(dp[i][j - 1][3], dp[i - 1][j][k]);
                                }
                            } else {
                                //실내온도가 같다면 유지된다.
                                if(!posible(onboard[i], j, t1, t2)) {
                                    continue;
                                }
                                dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][k]);
                                dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][j][k]);
                                if(j != 50) {
                                    dp[i][j][2] = Math.min(dp[i][j][2], dp[i - 1][j][k]);
                                }
                                if(j != 0) {
                                    dp[i][j][3] = Math.min(dp[i][j][3], dp[i - 1][j][k]);
                                }
                            }
                            
                        } else if(k == 1) {
                            //에어컨 on, 유지
                            if(!posible(onboard[i], j, t1, t2)) {
                                continue;
                            }
                            dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][k] + b);
                            dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][j][k] + b);
                            if(j != 50) {
                                dp[i][j][2] = Math.min(dp[i][j][2], dp[i - 1][j][k] + b);
                            }
                            if(j != 0) {
                                dp[i][j][3] = Math.min(dp[i][j][3], dp[i - 1][j][k] + b);
                            }
                        } else if(k == 2) {
                            if(!posible(onboard[i], j + 1, t1, t2)) {
                                continue;
                            }
                            dp[i][j + 1][0] = Math.min(dp[i][j + 1][0], dp[i - 1][j][k] + a);
                            dp[i][j + 1][1] = Math.min(dp[i][j + 1][1], dp[i - 1][j][k] + a);
                            if((j + 1) != 50) {
                                dp[i][j + 1][2] = Math.min(dp[i][j + 1][2], dp[i - 1][j][k] + a);
                            }
                            dp[i][j + 1][3] = Math.min(dp[i][j + 1][3], dp[i - 1][j][k] + a);
                        } else if(k == 3) {
                            if(!posible(onboard[i], j - 1, t1, t2)) {
                                continue;
                            }
                            dp[i][j - 1][0] = Math.min(dp[i][j - 1][0], dp[i - 1][j][k] + a);
                            dp[i][j - 1][1] = Math.min(dp[i][j - 1][1], dp[i - 1][j][k] + a);
                            dp[i][j - 1][2] = Math.min(dp[i][j - 1][2], dp[i - 1][j][k] + a);
                            if((j - 1) != 0) {
                                dp[i][j - 1][3] = Math.min(dp[i][j - 1][3], dp[i - 1][j][k] + a);
                            }
                        }
                    }
                }
            }
        }
        
        
        int answer = Integer.MAX_VALUE;
        for(int j=0; j<=50; j++) {
            for(int k=0; k<=2; k++) {
                answer = Math.min(answer, dp[maxMin][j][k]);
            }
        }
        return answer;
    }
    
    static boolean posible(int board, int temp, int t1, int t2) {
        if(board == 0) {
            return true;
        }
        
        if((t1 <= temp) && (temp <= t2)) {
            return true;
        }
        
        return false;
    }
    
    static void init(int[][][] arr) {
        for(int i=0; i<=maxMin; i++) {
            for(int j=0; j<=50; j++) {
                for(int k=0; k<=3; k++) {
                    arr[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }
}