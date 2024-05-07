import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> plusS = new ArrayList<>();
    static ArrayList<Integer> minusS = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int v = Integer.parseInt(st.nextToken());
            if(v > 0) {
                plusS.add(v);
            } else {
                minusS.add(v);
            }
        }
        Collections.sort(plusS, Collections.reverseOrder());
        Collections.sort(minusS);
        
        Deque<Integer> plusDeque = new ArrayDeque<>();
        Deque<Integer> minusDeque = new ArrayDeque<>();
        
        if(plusS.size() != 0) {
            plusDeque.addFirst(plusS.get(0));
            for(int i=1; i<plusS.size(); i+= 2) {
                if(i < plusS.size()) {
                    plusDeque.addFirst(plusS.get(i));
                }
                if(i + 1 < plusS.size()) {
                    plusDeque.addLast(plusS.get(i + 1));
                }
            }
        }
        
        if(minusS.size() != 0) {
            minusDeque.addFirst(minusS.get(0));
            for(int i=1; i<minusS.size(); i+=2) {
                if(i < minusS.size()) {
                    minusDeque.addFirst(minusS.get(i));
                }
                if(i + 1 < minusS.size()) {
                    minusDeque.addLast(minusS.get(i + 1));
                }
            }
            
        }
        
        boolean pFront = false;;
        if(plusS.size() % 2 == 0) {
            pFront = true;
        } 
        
        boolean mFront = false;
        if(minusS.size() % 2 == 0) {
            mFront = true;
        }
        
        Deque deque = new ArrayDeque<>();
        while(plusDeque.peekFirst() != null) {
            deque.addLast(plusDeque.pollFirst());
        }
        
        while(minusDeque.peekFirst() != null) {
            int v = 0;
            if(mFront) {
                v = minusDeque.pollFirst();
            } else {
                v = minusDeque.pollLast();
            }
            
            if(pFront) {
                deque.addFirst(v);
            } else {
                deque.addLast(v);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(deque.peekFirst() != null) {
            sb.append(deque.pollFirst()).append(" ");
        }
        
        System.out.println(sb.toString().trim());
        
    }
}