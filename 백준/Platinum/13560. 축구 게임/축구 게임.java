import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int W = 0;
    static boolean valid = true;
    static int arr[];
    static int sum = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        if (N * (N - 1) / 2 == sum) {
            Arrays.sort(arr);
            for (int i = N - 1; i >= 0; i--) {
                if (arr[i] > i) {
                    if(arr[i] <= i + W) {
                        W -= arr[i] - i;
                    } else {
                        valid = false;
                        break;
                    }
                } else if (arr[i] < i) W += i - arr[i];
            }
            if(valid) System.out.println(1);
            else System.out.println(-1);
        } else {
            System.out.println(-1);
        }
    }
}