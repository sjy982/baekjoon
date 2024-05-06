import java.io.*;
import java.util.*;

class Line implements Comparable<Line> {
    int L, R;
    Line(int L, int R) {
        this.L = L;
        this.R = R;
    }
    
    @Override
    public int compareTo(Line o) {
        if(this.L < o.L) {
            return -1;
        } else if(this.L > o.L) {
            return 1;
        } else {
            if(this.R < o.R) {
                return 1;
            } else if(this.R > o.R) {
                return -1;
            }
        }
        return 0;
    }
    
}

public class Main {
    static int M;
    static ArrayList<Line> list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Line input = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        while(input.L != 0 || input.R != 0) {
            if(input.R > 0) {
                int L = input.L < 0 ? 0 : input.L;
                list.add(new Line(L, input.R));
            }
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            input = new Line(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
        }
        
        Collections.sort(list);
        System.out.println(answer());
    }
    
    static int answer() {
        int cnt = 0;
        int L = 0;
        int lastR = 0;
        int i = 0;
        while(i < list.size()) {
            if(list.get(i).L <= L && list.get(i).R > lastR) {
                lastR = list.get(i).R;
            } 
            
            if(list.get(i).L > L) {
                //업데이트 되었는지 확인
                if(L == lastR) {
                    return 0;
                }
                cnt += 1;
                L = lastR;
            } else {
                i+=1;
            }
            
            if(lastR >= M) {
                cnt += 1;
                return cnt;
            }
        }
        return 0;
    }
}