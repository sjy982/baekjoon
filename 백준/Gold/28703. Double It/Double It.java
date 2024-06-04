import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxV = -1;
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            maxV = Math.max(maxV, A[i]);
        }
        if(N == 1) {
            System.out.println(0);
        } else {
            System.out.println(answer(A, maxV));
        }
    }
    
    static int answer(int[] A, int maxV) {
        int result = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            A[i] = init(A[i], maxV);
        }
        Arrays.sort(A);
        int max = A[N - 1];
        for(int i=0; i<N - 1; i++) {
            result = Math.min(result, Math.min(max - A[i], A[i] * 2 - A[i + 1]));
            max = A[i] * 2;
        }
        return result;
    }
    
    static int init(int v, int max) {
        while(v <= max) {
            v *= 2;
        }
        return v/2;
    }
}