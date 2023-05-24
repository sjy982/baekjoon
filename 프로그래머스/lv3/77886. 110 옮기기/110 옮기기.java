import java.util.*;
import java.io.*;
class Solution {
    static StringBuilder sb_fix = new StringBuilder();
    static StringBuilder sb_110 = new StringBuilder();
    static Stack<Character> stack = new Stack<>();
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i=0; i<s.length; i++) {
            String str = s[i];
            for(int j=0; j<str.length(); j++) {
                if(str.charAt(j) == '1') stack.push('1');
                else if(str.charAt(j) == '0') {
                    if(stack.size() == 0) sb_fix.append('0');
                    else if(stack.size() == 1) {
                        stack.pop();
                        sb_fix.append("10");
                    } else {
                        //stack에 요소 1의 개수가 2보다 클 때
                        stack.pop();
                        stack.pop();
                        sb_110.append("110");
                    }
                }
            }
            while(stack.size() != 0) sb_110.append(stack.pop());
            answer[i] = sb_fix.toString() + sb_110.toString();
            sb_fix = new StringBuilder();
            sb_110 = new StringBuilder();
        }
        return answer;
    }
}