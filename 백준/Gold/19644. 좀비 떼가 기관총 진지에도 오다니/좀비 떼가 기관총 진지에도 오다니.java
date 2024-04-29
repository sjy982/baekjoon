import java.io.*;
import java.util.*;

public class Main {
    static int L, Ml, Mk, Cammo; // Ml은 기관총의 유효 사거리, Mk는 1m당 살상력, Cammo 폭탄 개수
    static int[] zombi; //좀비 체력
    static int[] sumCa; //폭탄 누적합
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        zombi = new int[L + 1];
        sumCa = new int[L + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Ml = Integer.parseInt(st.nextToken());
        Mk = Integer.parseInt(st.nextToken());
        Cammo = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=L; i++) {
            zombi[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println(gameStart());
    }
    
    static String gameStart() {
        for(int i=1; i<=L; i++) {
            sumCa[i] += sumCa[i - 1]; //폭탄 개수 누적합
            
            int w = i;
            if(w > Ml) {
                //유호사거리보다 길다면
                w = Ml;
            }
            int damege = w * Mk - (sumCa[i] * Mk); //1m까지 좀비가 왔을 때 받는 총 데미지
            
            int left = zombi[i] - damege;
            if(left > 0) {
                //죽지 않았다면 폭탄을 사용하는 것이 최선의 선택임
                if(Cammo > 0) {
                    //폭탄 있으면 유효사거리까지만 폭탄이 누적되게
                    sumCa[i] += 1;
                    if(i + Ml <= L) {
                        sumCa[i + Ml] -= 1;
                    }
                    Cammo -= 1;
                } else {
                    //폭탄이 없으면 게임 끝
                    return "NO";
                }
            }
        }
        
        return "YES";
    }
}