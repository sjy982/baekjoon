import java.io.*;
import java.util.*;

public class Main {
    static int N, mid;
    static PriorityQueue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> min_heap = new PriorityQueue<>();
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(i==0) max_heap.add(num);
            else {
                if(mid <= num) min_heap.add(num);
                else max_heap.add(num);
            }
            //밸런스 조정
            if(max_heap.size() > min_heap.size()) {
                int top = max_heap.poll();
                min_heap.add(top);
                mid = top;
            } else if(max_heap.size() < min_heap.size()) {
                int top = min_heap.poll();
                max_heap.add(top);
                mid = top;
            }
            if((max_heap.size() + min_heap.size()) % 2 == 0) {
                //짝수일때는 더 작은값
                int n1 = max_heap.peek();
                int n2 = min_heap.peek();
                if(n1 <= n2) {
                    ans.append(n1 + "\n");
                } else {
                    ans.append(n2 + "\n");
                }
            } else {
                ans.append(mid + "\n");
            }
        }
        System.out.println(ans.toString().trim());
    }
}