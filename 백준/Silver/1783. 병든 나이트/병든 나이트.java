import java.io.*;
import java.util.*;

public class Main {
    static long N,M;
    static long ans = 1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()) - 1;
        M = Long.parseLong(st.nextToken()) - 1;
        if(N>=2) {
            if(M >= 5) {
                M -= 4;
                ans += 2;
                ans += M;
            } else {
                ans += M;
                if(ans > 4) ans = 4;
            }
        } else if(N == 1) {
            if(M/2 > 3) {
                ans += 3;
            } else {
                ans += M/2;
            }
        }
        System.out.println(ans);
    }
}