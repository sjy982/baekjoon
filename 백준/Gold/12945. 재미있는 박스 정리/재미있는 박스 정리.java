import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] V = new int[N];
        for(int i=0; i<N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println(answer(V));
    }
    
    static int answer(int[] V) {
        int cnt = 0;
        Arrays.sort(V);
        int start = V.length/2;
        for(int i=0; i<V.length/2; i++) {
            cnt += 1;
            while(start < V.length) {
                if(V[i] * 2 <= V[start]) {
                    start += 1;
                    break;
                }
                cnt += 1;
                start += 1;
            }
        }
        
        if(start != V.length) {
            cnt += 1;
        }
        return cnt;
    } 
}