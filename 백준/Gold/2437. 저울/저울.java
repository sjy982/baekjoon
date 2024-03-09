import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] list;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        int k = 0;
        for(int i=0; i<N; i++) {
            if(list[i] > k + 1) {
                break;
            }
            k = list[i] + k;
        }
        System.out.println(k + 1);
        
    }
}