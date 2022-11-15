import java.io.*;
import java.util.*;

public class Main {
    static final int dx[] = {-1,0,1};
    static int N,ans,cout;
    static int o_line[];
    static int c_line[];
    static int compare_line[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        o_line = new int[N];
        compare_line = new int[N];
        cout = 0;
        for(int i=0; i<2; i++) {
            String s = br.readLine();
            for(int j=0; j<N; j++) {
                if(i==0) {
                    o_line[j] = s.charAt(j) - '0';
                } else {
                    compare_line[j] = s.charAt(j) - '0';
                }
            }
        }
        c_line = o_line.clone();
        for(int i=0; i<2; i++) {
            for(int j=1; j<N; j++) {
                if(c_line[j-1] != compare_line[j-1]) {
                    change(j);
                    cout += 1;
                }
            }
            if(compare()) {
                ans = cout;
                break;
            } else {
                c_line = o_line.clone();
                change(0);
                cout = 1;
                ans = -1;
            }
        }
        System.out.println(ans);
    }
    
    static void change(int x) {
        for(int i=0; i<3; i++) {
            int nx = x + dx[i];
            if(nx>=0 && nx<=N-1) {
                if(c_line[nx] == 0) {
                    c_line[nx] = 1;
                } else {
                    c_line[nx] = 0;
                }
            }
        }
    }
    
    static boolean compare() {
        for(int i=0; i<N; i++) {
            if(c_line[i] != compare_line[i]) {
                return false;
            }
        }
        return true;
    }
}