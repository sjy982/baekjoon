import java.util.*;
class Solution {
    public String solution(String p) {
        return convert_par(p);
    }
    
    static String convert_par(String str) {
        if(str.equals("")) return "";
        String[] separ_str = separation_parStr(str);
        StringBuilder sb = new StringBuilder();
        if(right_parStr(separ_str[0])) {
            sb.append(separ_str[0]);
            sb.append(convert_par(separ_str[1]));
        } else {
            sb.append('(');
            sb.append(convert_par(separ_str[1]));
            sb.append(')');
            sb.append(reverse_parStr(separ_str[0]));
        }
        return sb.toString();
    }
    
    static String reverse_parStr(String str) {
        StringBuilder reverse_sb = new StringBuilder();
        for(int i=1; i<str.length()-1; i++) {
            if(str.charAt(i) == '(') reverse_sb.append(')');
            else if(str.charAt(i) == ')') reverse_sb.append('(');
        }
        return reverse_sb.toString();
    }
    
    static boolean right_parStr(String str) {
        int open_par = 0;
        int close_par = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') open_par += 1;
            else if(str.charAt(i) == ')') close_par +=1;
            if(open_par < close_par) return false;
        }
        return true;
    }
    
    static String[] separation_parStr(String str) {
        int open_par = 0;
        int close_par = 0;
        String[] separ_str = new String[2];
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') open_par += 1;
            else if(str.charAt(i) == ')') close_par += 1;
            if(open_par == close_par) {
                if(i+1 == str.length()) {
                    separ_str[0] = str;
                    separ_str[1] = "";
                } else {
                    separ_str[0] = str.substring(0, i+1);
                    separ_str[1] = str.substring(i+1, str.length());
                }
                break;
            }
        }
        return separ_str;
    }
}