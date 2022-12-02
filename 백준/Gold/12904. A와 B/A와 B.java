import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Character> S = new ArrayList<>();
    static ArrayList<Character> T = new ArrayList<>();
    static boolean same = true;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str_s = br.readLine();
        String str_t = br.readLine();
        for(int i=0; i<str_s.length(); i++) {
            S.add(str_s.charAt(i));
        }
        for(int i=0; i<str_t.length(); i++) {
            T.add(str_t.charAt(i));
        }
        
        for(int i=T.size()-1; i>=0; i--) {
            if(T.get(i) == 'A') T.remove(i);
            else {
                T.remove(i);
                Collections.reverse(T);
            }
            if(S.size() == T.size()) break;
        } 
        
        for(int i=0; i<S.size(); i++) {
            if(S.get(i) != T.get(i)) {
                same = false;
                break;
            }
        }
        if(same) System.out.println(1);
        else System.out.println(0);
    }
}