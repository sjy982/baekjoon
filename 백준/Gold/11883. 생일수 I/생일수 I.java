import java.util.*;
import java.io.*;

class BirthNum {
    int thr, fiv, eig;
    BirthNum(int thr, int fiv, int eig) {
        this.thr = thr;
        this.fiv = fiv;
        this.eig = eig;
    }
    
    int getLength() {
        return this.thr + this.fiv + this.eig;
    }
    
    void addNum(int num) {
        if(num == 3) {
            this.thr += 1;
        } else if(num == 5) {
            this.fiv += 1;
        } else {
            this.eig += 1;
        }
    }
}

public class Main {
    static BirthNum[] dp = new BirthNum[1000001];
    static int T;
    public static void main(String args[]) throws IOException {
        makeDp();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            if(dp[n] == null) {
                sb.append(-1).append("\n");
            } else {
                sb.append(paste(dp[n])).append("\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
    
    static void makeDp() {
        dp[0] = new BirthNum(0, 0, 0);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(8);
        for(int i=3; i<=1000000; i++) {
            int minLen = Integer.MAX_VALUE;
            int minBn = Integer.MAX_VALUE;
            for(int j=0; j<list.size(); j++) {
                int bn = i - list.get(j);
                if(bn >= 0 && dp[bn] != null) {
                    int len = dp[bn].getLength();
                    if(minLen > len) {
                        minLen = len;
                        minBn = bn;
                    }
                }
            }
            if(minLen != Integer.MAX_VALUE) {
                dp[i] = new BirthNum(dp[minBn].thr, dp[minBn].fiv, dp[minBn].eig);
                dp[i].addNum(i - minBn);
            }
        }
    }
    
    static String paste(BirthNum bn) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            if(i == 0) {
                for(int j=0; j<bn.thr; j++) {
                    sb.append(3);
                }
            } else if(i == 1) {
                for(int j=0; j<bn.fiv; j++) {
                    sb.append(5);
                }
            } else {
                for(int j=0; j<bn.eig; j++) {
                    sb.append(8);
                }
            }
        }
        return sb.toString();
    }
}