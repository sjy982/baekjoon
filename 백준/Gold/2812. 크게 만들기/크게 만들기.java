import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        int len = N - K;
        String strNum = br.readLine();
        int cursor = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(strNum.charAt(0) - '0');
        while(cursor < N) {
            int num = strNum.charAt(cursor) - '0';
            if(K != 0) {
                while(!stack.isEmpty() && stack.peek() < num) {
                    stack.pop();
                    K -= 1;
                    if(K == 0) {
                        break;
                    }
                }
            }
            stack.push(num);
            cursor += 1;
        }
        
        StringBuilder sb = new StringBuilder();
        int cout = 0;
        for (Integer element: stack) {
            sb.append(element);
            cout += 1;
            if(cout == len) {
                break;
            }
        }
        System.out.println(sb.toString());
    }
}