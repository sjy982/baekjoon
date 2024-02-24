import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = -1;
        long sum = 0;
        for(int i=0; i<N; i++) {
            int ride = Integer.parseInt(st.nextToken());
            sum += ride;
            max = Math.max(max, ride);
        }
        long otherSum = sum - max;
        if(N == 1) {
            System.out.println(1);
        } else if(max > otherSum) {
            long answer = otherSum * 2 + 1;
            System.out.println(answer);
        } else {
            System.out.println(sum);
        }
        
    }
}