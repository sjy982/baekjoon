import java.io.*;
import java.util.*;

public class Main {
    static int N, a, b;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        if(N < (a + b - 1)) {
            //최소 개수보다 적은 경우는 불가능함.
            System.out.println(-1);
        } else {
            System.out.println(answer());
        }
    }
    
    static String answer() {
        int[] bulidings = new int[a + b - 1];
        int rmCnt = N - (a + b - 1);
        if(a >= b) {
            //a가 더 큰 경우 a를 기준으로 최대 높이를 설정
            for(int i=0; i<a; i++) {
                bulidings[i] = i + 1;
            }
            
            for(int i=bulidings.length - 1; i >=a; i--) {
                bulidings[i] = (bulidings.length) - i;
            }
        } else {
            //b가 더 큰 경우 b를 기준으로 최대 높이를 설정
            for(int i=bulidings.length - 1; i>(bulidings.length - 1) - b; i--) {
                bulidings[i] = (bulidings.length) - i;
            }
            
            for(int i=0; i<=(bulidings.length - 1) - b; i++) {
                bulidings[i] = i + 1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<bulidings.length; i++) {
            sb.append(bulidings[i]).append(" ");
            if(i == 0) {
                //잉여 건물 처리
                for(int j=0; j<rmCnt; j++) {
                    sb.append("1").append(" ");
                }
            }
        }
        return sb.toString().trim();
    }
}