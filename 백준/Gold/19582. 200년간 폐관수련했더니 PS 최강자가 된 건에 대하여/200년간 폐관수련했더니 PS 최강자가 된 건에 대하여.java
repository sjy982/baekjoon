import java.io.*;
import java.util.*;

class Competition {
    int x, p;
    Competition(int x, int p) {
        this.x = x;
        this.p = p;
    }
}

public class Main {
    static int N;
    static Competition[] competitions;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        competitions = new Competition[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            competitions[i] = new Competition(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        System.out.println(start());
    }
    
    static String start() {
        boolean ep = false; //제외했는지
        long sum = 0; //상금 총합
        int max = -1; //상금
        
        for(int i=0; i<N; i++) {
            if(sum <= competitions[i].x) {
                sum += competitions[i].p;
                max = Math.max(competitions[i].p, max);
            } else {
                //x원 이하가 아님
                if(ep) {
                    //이미 제외를 한 경우 || 대회를 제외해도 더 큰 경우
                    return "Zzz";
                }
                
                if((sum - max) <= competitions[i].x) {
                    //이전 대회를 제외했을 때 조건을 만족한다면
                    //현재 대회와 이전 대회 중 상금을 비교해서 더 큰 상금을 제외해준다.
                    
                    if(max > competitions[i].p) {
                        //이전 대회 상금이 더 크다면
                        sum -= max;
                        sum += competitions[i].p;
                    }
                }
                //이전 대회르 제외했을 때도 조건을 만족하지 않는다면 현재 대회를 제외한다.
                ep = true;
            }
        }
        
        return "Kkeo-eok";
    }
}