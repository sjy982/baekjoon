import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static Integer[] cranes;
    static int[] ansewr;
    static Integer[] boxs;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cranes = new Integer[N];
        ansewr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        boxs = new Integer[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            boxs[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(boxs, Collections.reverseOrder());
        int cout = -1;
        while(cout != 0) {
            cout = 0;
            for(int i=0; i<cranes.length; i++) {
                boolean isMove = moveBox(i);
                if(isMove) {
                    cout += 1;
                } else {
                    break;
                }
            }
        }
        if(!isPosible()) {
            System.out.println(-1);
        } else {
            System.out.println(findMaxValue());
        }
    }
    
    static int findMaxValue() {
        int max = -1;
        for(int i=0; i<ansewr.length; i++) {
            max = Math.max(max, ansewr[i]);
        }
        return max;
    }
    
    static boolean moveBox(int craneInd) {
        for(int i=0; i<boxs.length; i++) {
            if(boxs[i] != 0 && cranes[craneInd] >= boxs[i]) {
                ansewr[craneInd] += 1;
                boxs[i] = 0;
                return true;
            }
        }
        return false;
    }
    
    static boolean isPosible() {
        for(int i=0; i<boxs.length; i++) {
            if(boxs[i] != 0) {
                return false;
            }
        }
        return true;
    }
}