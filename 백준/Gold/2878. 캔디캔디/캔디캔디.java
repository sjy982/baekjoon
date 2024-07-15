import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    static int M, N;
    // static int MOD = Math.pow(10, )
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Integer[] list = new Integer[N];
        int[] sum = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(list, Collections.reverseOrder());

        int cursor = 0;
        while (M != 0) {
            cursor += 1;
            if (cursor == N) {
                sum[cursor - 1] -= M / cursor;
                if (M % cursor != 0) {
                    sum[(M % cursor) - 1] -= 1;
                }
                M = 0;
            } else {
                int diff = list[cursor - 1] - list[cursor];

                if (diff * cursor <= M) {
                    M -= diff * cursor;
                    sum[cursor - 1] -= diff;
                } else {
                    sum[cursor - 1] -= M / cursor;
                    if (M % cursor != 0) {
                        sum[(M % cursor) - 1] -= 1;
                    }
                    M = 0;
                }
            }
        }
        
        for(int i=N - 1; i >= 1; i--) {
            sum[i - 1] += sum[i];
        }
        
        BigInteger answer = BigInteger.ZERO;
        BigInteger MOD = BigInteger.ONE.shiftLeft(64); // 2^64
        for(int i=0; i<N; i++) {
            int r = list[i] + sum[i];
            BigInteger remain = BigInteger.valueOf(r);
            answer = answer.add(remain.multiply(remain)).mod(MOD);
        }
        System.out.println(answer);
    }
}