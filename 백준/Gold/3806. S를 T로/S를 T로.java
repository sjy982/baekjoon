import java.io.*;
import java.util.*;

public class Main {
    static int C;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        for(int i=1; i<=C; i++) {
            String s = br.readLine();
            String t = br.readLine();
            System.out.println("Case " + i + ": " +answer(s, t));
        }
    }
    
    
    static int answer(String s, String t) {
        int oC = 0;
        int lC = 0;
        int qC = 0;
        int qOc = 0;
        int tOc = 0;
        int sOc = 0;
        
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '?') {
                qC += 1;
                if(t.charAt(i) == '0') {
                    qOc += 1;
                }
            } else {
                if(s.charAt(i) != t.charAt(i)) {
                    if(s.charAt(i) == '0') {
                        oC += 1;
                    } else if(s.charAt(i) == '1') {
                        lC += 1;
                    }
                }
            }
            
            if(t.charAt(i) == '0') {
                tOc += 1;
            }
            if(s.charAt(i) == '0') {
                sOc += 1;
            }
        }
        
        //불가능한 경우
        if(sOc + qC < tOc) {
            return -1;
        }
        
        //나머지는 가능한 경우
        int answer = 0;
        if(oC == lC) {
            return oC + qC;
        } else if(oC > lC) {
            answer = lC;
            int left = oC - lC;
            if(left <= qOc) {
                answer += 2 * left + (qC - left);
            } else {
                answer += 2 * qOc + (left - qOc) + (qC - qOc);
            }
            
        } else if(oC < lC) {
            answer = oC;
            int left = lC - oC;
            answer += 2 * left + (qC - left);
        }
        
        return answer;
    }
}