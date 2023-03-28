import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> heap = new PriorityQueue<>(); //최소힙
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int op = Integer.parseInt(br.readLine());
            if(op == 0) {
                if(heap.size() == 0) sb.append("0\n");
                else sb.append(heap.poll() + "\n");
            } else {
                heap.add(op);
            }
        }
        System.out.println(sb.toString().trim());
    }
}