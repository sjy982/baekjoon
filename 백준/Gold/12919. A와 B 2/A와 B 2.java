import java.io.*;
import java.util.*;

public class Main {
    static ArrayList < Character > S = new ArrayList < > ();
    static ArrayList < Character > T = new ArrayList < > ();
    static boolean same = false;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str_s = br.readLine();
        String str_t = br.readLine();
        for (int i = 0; i < str_s.length(); i++) {
            S.add(str_s.charAt(i));
        }
        for (int i = 0; i < str_t.length(); i++) {
            T.add(str_t.charAt(i));
        }
        change_pos(T);
        if(same) System.out.println(1);
        else System.out.println(0);
    }

    static void change_pos(ArrayList < Character > ct) {
        if (!same) {
            if (ct.size() == S.size()) {
                for (int i = 0; i < S.size(); i++) {
                    if (ct.get(i) != S.get(i)) return;
                }
                same = true;
                return;
            } else {
                ArrayList<Character> t = new ArrayList<>();
                t.addAll(ct);
                if(t.get(0) == 'A') {
                    if(t.get(t.size()-1) == 'B') return;
                    else {
                        t.remove(t.size()-1);
                        change_pos(t);
                    }
                } else if(t.get(0) == 'B') {
                    if(t.get(t.size()-1) == 'B') {
                        Collections.reverse(t);
                        t.remove(t.size()-1);
                        change_pos(t);
                    } else if(t.get(t.size()-1) == 'A') {
                        ArrayList<Character> t2 = new ArrayList<>();
                        t2.addAll(t);
                        t2.remove(t2.size()-1);
                        change_pos(t2);
                        Collections.reverse(t);
                        t.remove(t.size()-1);
                        change_pos(t);
                    }
                }
            }
        }
    }
}