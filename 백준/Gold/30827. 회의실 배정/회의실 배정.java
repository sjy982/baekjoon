import java.util.*;
import java.io.*;

class Metting implements Comparable<Metting> {
    int s, e;
    Metting(int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Metting other) {
        if(this.e < other.e) {
            return -1;
        } else if(this.e > other.e) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, K;
    static ArrayList<Metting> list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st2.nextToken());
            int e = Integer.parseInt(st2.nextToken());
            list.add(new Metting(s, e));
        }
        if(N <= K) {
            System.out.println(N);
            return;
        }
        Collections.sort(list);
        ArrayList<Integer> endList = new ArrayList<>();
        endList.add(list.get(0).e);
        int cursor = 1;
        int answer = 1;
        while(cursor < N) {
            for(int i=0; i<endList.size(); i++) {
                if(endList.get(i) < list.get(cursor).s) {
                    endList.remove(i);
                    endList.add(list.get(cursor).e);
                    answer += 1;
                    break;
                } else {
                    if(endList.size() < K) {
                        endList.add(list.get(cursor).e);
                        answer += 1;
                        break;
                    }
                }
            }
            Collections.sort(endList, Comparator.reverseOrder());
            cursor += 1;
        }
        System.out.println(answer);
    }
}