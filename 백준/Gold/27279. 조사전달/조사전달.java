import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] soldiers, employment;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        soldiers = new int[N];
        employment = new int[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            soldiers[i] = Integer.parseInt(st2.nextToken());
        }
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            employment[i] = Integer.parseInt(st3.nextToken());
        }
        Arrays.sort(soldiers);
        Arrays.sort(employment);
        
        String answer = "NO";
        int eCursor = 0;
        for(int i=0; i<N; i++) {
            if(eCursor < soldiers[i]) {
                employment[eCursor] -= 1;
                if(employment[eCursor] == 0) {
                    eCursor += 1;
                }
            }
            if(eCursor == M) {
                answer = "YES";
                break;
            }
        }
        System.out.println(answer);
    }
}