import java.io.*;
import java.util.*;

public class Main {
    static int N; //식의 길이
    static int c_op; //연산자 개수
    static int max_pt; //최대 괄호 개수
    static Stack<Integer> result = new Stack<>();
    static LinkedList<String> expression = new LinkedList<>();
    static ArrayList<Long> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        c_op = (N-1)/2;
        max_pt = (N - c_op)/2;
        String str_ex = br.readLine();
        for(int i=0; i<str_ex.length(); i++) {
            expression.add(String.valueOf(str_ex.charAt(i)));
        }
        //괄호 추가하지 않았을 때 값
        LinkedList<String> copy = new LinkedList<>();
        copy.addAll(expression);
        ans.add(all_calculate(copy));
        //괄호 추가했을 때 모든 경우의 수
        for(int i=1; i<=max_pt; i++) {
            DFS(i,1);
        }
        System.out.println(Collections.max(ans));
    }
    static void DFS(int len, int s) {
        if(result.size() == len) {
            LinkedList<String> ex = new LinkedList<>();
            ex.addAll(expression);
            for(int i=0; i<result.size(); i++) {
                int left_ind = result.get(i) * 2 - i*2; //연산자에 왼쪽 피연산자 위치
                long v = calculate(Long.parseLong(ex.get(left_ind)), ex.get(left_ind+1).charAt(0), Long.parseLong(ex.get(left_ind+2)));
                ex.set(left_ind+2 ,String.valueOf(v));
                ex.remove(left_ind+1);
                ex.remove(left_ind);
            }
            ans.add(all_calculate(ex));
            return;
        }
        for(int i=s; i<c_op; i++) {
            result.push(i);
            DFS(len, i+2);
            result.pop();
        }
        
    }
    
    static long all_calculate(LinkedList<String> e) {
        while(e.size() != 1) {
            long v = calculate(Long.parseLong(e.get(0)), e.get(1).charAt(0), Long.parseLong(e.get(2)));
            e.set(2, String.valueOf(v));
            e.remove(1);
            e.remove(0);
        }
        return Long.parseLong(e.get(0));
    }
    
    static long calculate(long left, char op, long right) {
        long result = 0;
        if(op == '+') {
            result = left + right;
        } else if(op == '-') {
            result = left - right;
        } else if(op =='*') {
            result = left * right;
        }
        return result;
    }
}