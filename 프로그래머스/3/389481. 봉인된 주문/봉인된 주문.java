import java.io.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        // 1) 26^i 테이블 생성
        long[] pow26 = new long[12];
        pow26[0] = 1;
        for (int i = 1; i <= 11; i++) {
            pow26[i] = pow26[i-1] * 26;
        }
        
        // 2) 전체 문자열 길이(len) 결정
        int len = 1;
        long sum = pow26[1]; // 26^1
        while (true) {
            if (n <= sum) break;
            len++;
            sum += pow26[len];
        }
        
        // 3) 고정된 길이(len)에서의 offset k 계산
        long k = n - (sum - pow26[len]);
        
        // 4) k번째 문자열 생성
        int cursor = len - 1;
        int r = 0;
        sum = pow26[cursor];
        ArrayList<Character> list = new ArrayList<>();
        while (cursor >= 0) {
            if (k <= sum) {
                list.add((char) (97 + r));
                k = k - (sum - pow26[cursor]);
                cursor -= 1;
                r = 0;
                sum = (cursor >= 0 ? pow26[cursor] : 1);
            } else {
                sum += pow26[cursor];
                r += 1;
            }
        }
        
        // 5) bans 정렬 (길이-사전순)
        Arrays.sort(bans, (s1, s2) -> {
            if (s1.length() != s2.length()) {
                return Integer.compare(s1.length(), s2.length());
            }
            return s1.compareTo(s2);
        });
        
        // 6) bans 제외 보정 (단순 증가 예시)
        for (int i = 0; i < bans.length; i++) {
            String std = trans(list);
            if (std.length() < bans[i].length()) break;
            if (std.length() == bans[i].length() && std.compareTo(bans[i]) < 0) break;
            
            int cursor2 = list.size() - 1;
            boolean inc = true;
            while (cursor2 >= 0) {
                if (list.get(cursor2) == 'z') {
                    list.set(cursor2, 'a');
                    cursor2--;
                    continue;
                }
                inc = false;
                list.set(cursor2, (char) (list.get(cursor2) + 1));
                break;
            }
            if (inc) {
                list.add(0, 'a');
            }
        }
        return trans(list);
    }
    
    static String trans(ArrayList<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (char c : list) sb.append(c);
        return sb.toString();
    }
}