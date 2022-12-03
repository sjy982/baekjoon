import java.io.*;
import java.util.*;

public class Main {
    static String S;
    static StringBuilder T;
    static int ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = new StringBuilder(br.readLine());
        change_pos();
        System.out.println(ans);
    }

    static void change_pos() {
        if(ans == 1) return;
        
        if(T.length() == S.length()) {
            if(T.toString().equals(S)) ans = 1;
            return;
        } else {
            if(T.charAt(T.length()-1) == 'A') {
                T.deleteCharAt(T.length()-1);
                change_pos();
                T.append('A');
            }
            
            if(T.charAt(0) == 'B') {
                T.reverse();
                T.deleteCharAt(T.length()-1);
                change_pos();
                T.append('B');
                T.reverse();
            }
        }
    }
}