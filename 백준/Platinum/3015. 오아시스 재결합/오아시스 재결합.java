import java.io.*;
import java.util.*;

class Node {
    int h,c;
    Node(int h, int c) {
        this.h = h;
        this.c = c;
    }
}

public class Main {
    static int N;
    static Stack<Node> stack = new Stack<>();
    static long ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int height = Integer.parseInt(br.readLine());
            if(stack.empty()) stack.push(new Node(height, 1));
            else {
                if(stack.peek().h > height) stack.push(new Node(height, 1));
                else if(stack.peek().h == height) stack.peek().c += 1;
                else {
                    while(!stack.empty()) {
                        if(stack.peek().h > height) {
                            stack.push(new Node(height, 1));
                            break;
                        } else if(stack.peek().h == height) {
                            stack.peek().c += 1;
                            break;
                        } else {
                            Node n = stack.pop();
                            ans += n.c;
                            if(stack.empty()) n.c -= 1;
                            for(int j=n.c; j>0; j--) ans += j;
                        }
                    }
                    if(stack.empty()) stack.push(new Node(height, 1));
                }
            }
        }
        //stack이 비어 있지 않다면
        while(!stack.empty()) {
            Node n = stack.pop();
            if(stack.empty()) n.c -= 1;
            for(int i=n.c; i>0; i--) ans += i;
        }
        System.out.println(ans);
    }
}