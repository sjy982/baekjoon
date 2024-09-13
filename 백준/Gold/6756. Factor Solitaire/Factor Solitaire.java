import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int cnt = 0;
        while (N != 1) {
            int c = N % 2 == 0 ? N / 2 : N / 2 + 1;
            int a = N / 2;
            while (c % a != 0) {
                c += 1;
                a -= 1;
            }
            N = c;
            cnt += c / a;
        }
        
        System.out.println(cnt);
    }
}