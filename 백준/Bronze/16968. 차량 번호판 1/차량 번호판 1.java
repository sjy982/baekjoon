import java.io.*;
import java.util.*;

public class Main {
    static String format;
    static int ans = 1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        format = br.readLine();
        for(int i=0; i<format.length(); i++) {
            if(i==0) {
                if(format.charAt(i) == 'd') ans *= 10;
                else ans *= 26;
            } else {
                if((format.charAt(i-1) == 'd') && (format.charAt(i) == 'd')) {
                    ans *= 9;
                } else if((format.charAt(i-1) == 'd') && (format.charAt(i) == 'c')) {
                    ans *= 26;
                } else if((format.charAt(i-1) == 'c') && (format.charAt(i) == 'c')) {
                    ans *= 25;
                } else if((format.charAt(i-1) == 'c') && (format.charAt(i) == 'd')) {
                    ans *= 10;
                }
            }
        }
        System.out.println(ans);
    }
}