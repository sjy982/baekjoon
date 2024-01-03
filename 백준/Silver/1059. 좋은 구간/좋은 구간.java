import java.io.*;
import java.util.*;

public class Main {
    static int L, n;
    static int[] S;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        S = new int[L];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<L; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(S);
        n = Integer.parseInt(br.readLine());
        int answer = 0;
        if(!isExistN(S, n)) {
           if(n < S[0]) {
                // answer = S[0] - n - 1;
                answer = findAnswer(0, S[0], n);
            } else {
                int ind = findFirstBiggerNumber(S, n);
                answer = findAnswer(S[ind - 1], S[ind], n);
                // answer = ((S[ind] - n -1) * (n - S[ind - 1])) +  (n - S[ind - 1] - 1);
            }
        } 
        System.out.println(answer);
    }
    
    static boolean isExistN(int[] S, int n) {
        for(int i=0; i<S.length; i++) {
            if(S[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    static int findFirstBiggerNumber(int[] S, int n) {
        for(int i=0; i<S.length; i++) {
            if(n < S[i]) {
                return i;
            }
        }
        return -1;
    }
    
    static int findAnswer(int small, int big, int n) {
        return ((big - n - 1) * (n - small)) + (n - small - 1);
    }
}