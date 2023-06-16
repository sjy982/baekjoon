import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=s.length(); i++) {
            StringBuilder before_sb = new StringBuilder();
            StringBuilder cur_sb = new StringBuilder();
            int str_cnt = 0;
            int same_cnt = 0;
            for(int j=0; j<s.length(); j++) {
                cur_sb.append(s.charAt(j));
                if((j + 1) % i == 0) {
                    if(before_sb.toString().equals(cur_sb.toString())) {
                        same_cnt += 1;
                        if(j == s.length() - 1) str_cnt += String.valueOf(same_cnt).length() + i;
                    } else {
                        if((j + 1) / i != 1) {
                            if(same_cnt == 0) str_cnt += i;
                            else str_cnt += String.valueOf(same_cnt + 1).length() + i;
                        }
                        before_sb = new StringBuilder(cur_sb.toString());
                        same_cnt = 0;
                        if(j == s.length() - 1) str_cnt += i;
                    }
                    cur_sb = new StringBuilder();
                }
            }
            if(cur_sb.toString().length() != 0) {
                if(same_cnt == 0) str_cnt += i + cur_sb.toString().length();
                else str_cnt += String.valueOf(same_cnt + 1).length() + i + cur_sb.toString().length();
            }
            answer = Math.min(answer, str_cnt);
        }
        return answer;
    }
}