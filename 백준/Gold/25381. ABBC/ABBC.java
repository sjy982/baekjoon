import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        //먼저 B로 C를 최대한 지운다.
        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == 'B') {
                queue.add(i);
            } else if(S.charAt(i) == 'C') {
                queue.poll();
                answer += 1;
            }
        }
        
        //나머지 B를 A로 지운다.
        for(int i=0; i<S.length(); i++) {
            if(queue.size() == 0) {
                break;
            }
            if(S.charAt(i) == 'A') {
                while(queue.size() != 0 && queue.peek() < i) {
                    queue.poll();
                }
                if(queue.size() != 0) {
                    queue.poll();
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}