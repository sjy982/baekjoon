import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    static int[] bulbs;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bulbs = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            bulbs[i] = Integer.parseInt(st2.nextToken());
        }
        
        // int si = 0; //통일된 범위
        int ei = K - 1; //통일된 범위
        int cnt = 0;
        boolean toggle = false;
        for(int i=0; i<=(N - K); i++) {
            if(i<= ei) {
                if(toggle && bulbs[i] == 0) {
                    //true인 경우에 스위치를 조작하면 기존 범위는 false, 새로 추가되는 범위는 true가됨 그렇기 때문에 
                    //새로 추가되는 범위도 false로 통일해야됨 그래서 그 부분 toggle시키면 false 범위로 통일 가능
                    cnt += 1;
                    toggle = false;
                    for(int j = ei + 1; j <= i + K - 1; j++) {
                        bulbs[j] = bulbs[j] == 1 ? 0 : 1;
                    }
                    ei = i + K - 1; //통일된 범위 업데이트
                } else if(!toggle && bulbs[i] == 1) {
                    //false인 경우 스위치를 누르면 이때는 새로 추가하는 범위도 true로 통일되기 때문에 ei만 업데이트
                    cnt += 1;
                    toggle = true;
                    ei = i + K - 1;
                }
            } else {
                //ei를 벗어나는 경우는 이미 전구가 꺼져있기 때문에 업데이트를 하지 않은거임
                if(bulbs[i] == 1) {
                    cnt += 1;
                    toggle = true;
                    ei = i + K - 1;
                }
            }
        }
        if(posible(ei, toggle)) {
            System.out.println(cnt);
        } else {
            System.out.println("Insomnia");
        }
    }
    
    static boolean posible(int ei, boolean toggle) {
        for(int i=N-K + 1; i<N; i++) {
            if(i <= ei) {
                if(toggle && bulbs[i] == 0) {
                    return false;
                } else if(!toggle && bulbs[i] == 1) {
                    return false;
                }
            } else {
                if(bulbs[i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}