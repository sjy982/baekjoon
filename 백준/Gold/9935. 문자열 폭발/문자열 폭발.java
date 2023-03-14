import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static String str, str_bomb;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        str_bomb = br.readLine();
        for(int i=0; i<str.length(); i++) {
            sb.append(str.charAt(i));
            if(sb.length()>=str_bomb.length()) {
                if(str_check()) {
                    sb.delete(sb.length()-str_bomb.length(), sb.length());
                }
            }
        }
        if(sb.length()==0) System.out.println("FRULA");
        else System.out.println(sb.toString());
    }
    static boolean str_check() {
        for(int i=0; i<str_bomb.length(); i++) {
            char str_c = sb.charAt(sb.length()-1-i);
            char bomb_c = str_bomb.charAt(str_bomb.length()-1-i);
            if(str_c != bomb_c) return false;
        }
        return true;
    }
}