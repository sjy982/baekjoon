import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int[] gas = new int[1000001];
        
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            gas[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        int L = Integer.parseInt(st2.nextToken());
        int P = Integer.parseInt(st2.nextToken());
        
        int coutGas = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=1; i<=L; i++) {
            if(gas[i] != 0) {
                //주유소가 있음
                pq.add(gas[i]);
            }
            P -= 1;
            if(i == L) {
                break;
            }
            if(P == 0) {
                
                if(pq.size() == 0) {
                    coutGas = -1;
                    break;
                }
                coutGas += 1;
                P += pq.poll();
            }
        }
        System.out.println(coutGas);
        
    }
}