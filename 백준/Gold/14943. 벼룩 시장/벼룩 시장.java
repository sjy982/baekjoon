import java.io.*;
import java.util.*;

class Info {
    int d, l;
    Info(int d, int l) {
        this.d = d;
        this.l = l;
    }
}

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Info> seller = new ArrayList<>();
        ArrayList<Info> buyer = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int l = Integer.parseInt(st.nextToken());
            if(l >= 0) {
                //벼륙을 파는 사람
                seller.add(new Info(i, Math.abs(l)));
            } else {
                //벼룩을 사는 사람
                buyer.add(new Info(i, Math.abs(l)));
            }
        }
        
        System.out.println(answer(seller, buyer));
    }
    
    static long answer(ArrayList<Info> seller, ArrayList<Info> buyer) {
        long result = 0;
        Stack<Info> stack = new Stack<>();
        for(int i=buyer.size() - 1; i>=0; i--) {
            stack.push(buyer.get(i));
        }
        
        for(int i=0; i<seller.size(); i++) {
            if(seller.get(i).l == 0) {
                continue;
            }
            
            Info byuerInfo = stack.pop();
            while(seller.get(i).l != 0) {
                if(byuerInfo.l >= seller.get(i).l) {
                    //사고도 남는 경우
                    result += (long) Math.abs(byuerInfo.d - seller.get(i).d) * (long) (seller.get(i).l);
                    byuerInfo.l -= seller.get(i).l;
                    seller.get(i).l = 0;
                    stack.push(byuerInfo);
                } else {
                    //다 못사는 경우
                    result += (long) Math.abs(byuerInfo.d - seller.get(i).d) * (long) (byuerInfo.l);
                    seller.get(i).l -= byuerInfo.l;
                    byuerInfo = stack.pop();
                }
            }
        }

        return result;
    }
}