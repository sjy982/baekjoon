import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int max_bk;
    static LinkedList < Character > og_ex = new LinkedList < > ();
    static Stack < Integer > result = new Stack < > ();
    static ArrayList < Long > ans = new ArrayList < > ();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max_bk = (N + 1) / 4;
        String str_input = br.readLine();
        for (int i = 0; i < N; i++) {
            og_ex.add(str_input.charAt(i));
        }
        calculate(og_ex);
        for (int i = 0; i <= max_bk; i++) {
            DFS(1, i);
        }
        System.out.println(Collections.max(ans));
    }
    static void DFS(int ind, int len) {
        if (result.size() == len) {
            LinkedList < Character > ex = new LinkedList < > (og_ex);
            for (int i = 0; i < result.size(); i++) {
                int oi = (result.get(i) - 1) * 2 + 1 + i * 2; //연산자 위치
                ex.add(oi - 1, '(');
                ex.add(oi + 3, ')');
            }
            ans.add(calculate(ex));
            return;
        }
        //조합
        for (int i = ind; i <= N / 2; i++) {
            result.push(i);
            DFS(i + 2, len);
            result.pop();
        }
    }

    static long calculate(LinkedList < Character > ex) {
        //중위표기식 -> 후위표기식 변환
        Stack < Character > op = new Stack < > (); //연산자
        ArrayList < Character > post_ex = new ArrayList < > (); //변환된 수식
        for (int i = 0; i < ex.size(); i++) {
            if (ex.get(i) == '+') {
                if (op.size() != 0) {
                    while (op.size() != 0 && op.peek() != '(') {
                        post_ex.add(op.pop());
                    }
                }
                op.push('+');
            } else if (ex.get(i) == '-') {
                if (op.size() != 0) {
                    while (op.size() != 0 && op.peek() != '(') {
                        post_ex.add(op.pop());
                    }
                }
                op.push('-');
            } else if (ex.get(i) == '*') {
                if (op.size() != 0) {
                    if (op.peek() == '*') {
                        post_ex.add(op.pop());
                    }
                }
                op.push('*');
            } else if (ex.get(i) == '(') {
                op.push('(');
            } else if (ex.get(i) == ')') {
                while (op.peek() != '(') {
                    post_ex.add(op.pop());
                }
                op.pop();
            } else {
                post_ex.add(ex.get(i));
            }
        }
        while (op.size() != 0) {
            post_ex.add(op.pop());
        }
        //후위표기식 계산
        Stack<Long> r = new Stack<>();
        for(int i=0; i<post_ex.size(); i++) {
            long right = 0;
            long left = 0;
            if(post_ex.get(i)=='+') {
                right = r.pop();
                left = r.pop();
                r.push(left + right);
            } else if(post_ex.get(i)=='-') {
                right = r.pop();
                left = r.pop();
                r.push(left - right);
            } else if(post_ex.get(i)== '*') {
                right = r.pop();
                left = r.pop();
                r.push(left * right);
            } else {
                r.push((long) post_ex.get(i) - '0');
            }
        }
       return r.pop();
    }
}