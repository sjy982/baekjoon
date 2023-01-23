import java.io.*;
import java.util.*;

public class Main {
    static int N; //식의 길이
    static int c_op; //연산자 개수
    static int max_pt; //최대 괄호 개수
    static Stack<Integer> result = new Stack<>();
    static LinkedList<Character> expression = new LinkedList<>();
    static ArrayList<Long> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        c_op = (N-1)/2;
        max_pt = (N - c_op)/2;
        String str_ex = br.readLine();
        for(int i=0; i<str_ex.length(); i++) {
            expression.add(str_ex.charAt(i));
        }
        //괄호 추가하지 않았을 때 값
        ans.add(calculate(expression));
        //괄호 추가했을 때 모든 경우의 수
        for(int i=1; i<=max_pt; i++) {
            DFS(i,1);
        }
        System.out.println(Collections.max(ans));
    }
    static void DFS(int len, int s) {
        if(result.size() == len) {
            LinkedList<Character> ex = new LinkedList<>();
            ex.addAll(expression);
            for(int i=0; i<result.size(); i++) {
                int left_ind = result.get(i) * 2 + i*2; //연산자에 왼쪽 피연산자 위치
                ex.add(left_ind, '(');
                ex.add(left_ind+4, ')');
            }
            ans.add(calculate(ex));
            return;
        }
        for(int i=s; i<c_op; i++) {
            result.push(i);
            DFS(len, i+2);
            result.pop();
        }
        
    }
    static long calculate(LinkedList<Character> e) {
        //중위 표기식 -> 후위 표기식
        LinkedList<Character> rear_e = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<e.size(); i++) {
            if(e.get(i)=='+' || e.get(i)=='-' || e.get(i)=='*' || e.get(i)=='(') {
                if(stack.size()==0) stack.push(e.get(i));
                else {
                    if(e.get(i)=='(' || stack.peek()=='(') stack.push(e.get(i));
                    else {
                        rear_e.add(stack.pop());
                        stack.push(e.get(i));
                    }
                }
            } else if(e.get(i)==')') {
                rear_e.add(stack.pop());
                stack.pop();
            } else {
                rear_e.add(e.get(i));
            }
        }
        while(stack.size()!=0) {
            rear_e.add(stack.pop());
        }
        //후위표기식 계산
        Stack<Long> cal_stack = new Stack<>();
        for(int i=0; i<rear_e.size(); i++) {
            if(rear_e.get(i)=='+' || rear_e.get(i)=='-' || rear_e.get(i)=='*') {
                long right = cal_stack.pop();
                long left = cal_stack.pop();
                if(rear_e.get(i)=='+') {
                    cal_stack.push(left + right);
                } else if(rear_e.get(i)=='-') {
                    cal_stack.push(left - right);
                } else if(rear_e.get(i)=='*') {
                    cal_stack.push(left * right);
                }
            } else {
                cal_stack.push((long) rear_e.get(i) - '0');
            }
        }
        return cal_stack.pop();
    }
}