import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
           
        }

        Arrays.sort(arr);
        Deque < Integer > deque = new LinkedList < > ();
        deque.addFirst(arr[0]);
        int minCursor = 1;
        int maxCursor = N - 1;
        boolean orderMax = true;
        while (minCursor != maxCursor) {
            if (orderMax) {
                deque.addFirst(arr[maxCursor]);
                maxCursor -= 1;
                if(minCursor != maxCursor) {
                    deque.addLast(arr[maxCursor]);
                    maxCursor -=1;
                    orderMax = false;
                }
            } else {
                deque.addFirst(arr[minCursor]);
                minCursor += 1;
                if(minCursor != maxCursor) {
                    deque.addLast(arr[minCursor]);
                    minCursor += 1;
                    orderMax = true;
                }
            }
        }
        //홀수인 경우는 마지막 요소를 앞에 붙일지 뒤에 붙일지 체크해야 됨
        if(Math.abs(deque.peekFirst() - arr[minCursor]) > Math.abs(deque.peekLast() - arr[minCursor])) {
            deque.addFirst(arr[minCursor]);
        } else {
            deque.addLast(arr[minCursor]);
        }
        
        Integer[] answerArr = deque.toArray(new Integer[N]);
        
        long answer = 0;
        for(int i=0; i<N - 1; i++) {
            answer += Math.abs(answerArr[i] - answerArr[i + 1]);
        }
        System.out.println(answer);
    }
    
    static int insert(Deque<Integer> deque, Integer[] arr, int cursor) {
        deque.addFirst(arr[cursor]);
        cursor += 1;
        if(deque.size() < N) {
            deque.addLast(arr[cursor]);
            cursor += 1;
        }
        return cursor;
    }
}