import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] stations = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            stations[i][0] = Integer.parseInt(st2.nextToken()); //내리는 사람
            stations[i][1] = Integer.parseInt(st2.nextToken()); //탑승하는 사람
        }
        
        System.out.println(answerMin(stations) + " " + answerMax(stations));
    }
    
    static int answerMin(int[][] stations) {
        int cnt =0; //답
        int cp = stations[0][1]; //체크한 사람
        int np = 0; //체크하지 않은 사람
        int stand = 0;
        
        for(int i=1; i<N; i++) {
            int getOff = stations[i][0]; //내리는 사람들 
            if(getOff <= cp) {
                cp -= getOff;
            } else {
                getOff -= cp;
                cp = 0;
                np -= getOff;
                cnt += getOff;
            }
            
            np += stations[i][1];
            if(stand + K == i) {
                //티켓 검사
                stand = i;
                cp += np;
                np = 0;
            }
        }
        
        return cnt;
    }
    
    static int answerMax(int[][] stations) {
        int cnt = 0;
        int cp = stations[0][1];
        int np = 0;
        int stand = 0;
        
        for(int i=1; i<N; i++) {
            int getOff = stations[i][0];
            if(getOff <= np) {
                np -= getOff;
                cnt += getOff;
            } else {
                getOff -= np;
                cnt += np;
                np = 0;
                cp -= getOff;
            }
            
            np += stations[i][1];
            if(stand + K == i) {
                stand = i;
                cp += np;
                np = 0;
            }
        }
        return cnt;
    }
}