import java.io.*;
import java.util.*;

class Stats implements Comparable<Stats> {
    int A,B;
    int diff;
    Stats(int A, int B) {
        this.A = A;
        this.B = B;
        this.diff = Math.abs(A - B);
    }
    
    @Override
    public int compareTo(Stats o) {
        if(this.diff < o.diff) {
            return 1;
        } else if(this.diff > o.diff) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static int T;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int j=0; j<T; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ArrayList<Stats> aList = new ArrayList<>();
            ArrayList<Stats> bList = new ArrayList<>();
            
            StringTokenizer stA = new StringTokenizer(br.readLine());
            StringTokenizer stB = new StringTokenizer(br.readLine());
            
            for(int i=0; i<n; i++) {
                int A = Integer.parseInt(stA.nextToken());
                int B = Integer.parseInt(stB.nextToken());
                if(A >= B) {
                    aList.add(new Stats(A, B));
                } else {
                    bList.add(new Stats(A, B));
                }
            }
            
            Collections.sort(aList);
            Collections.sort(bList);
            sb.append(answer(aList, bList, k)).append("\n");
        }
        
        System.out.println(sb.toString().trim());
    }
    
    static long answer(ArrayList<Stats> aList, ArrayList<Stats> bList, int k) {
        int listDiff = Math.abs(aList.size() - bList.size());
        //k 이하로 맞춰줘야 됨
        while(listDiff > k) {
            if(aList.size() > bList.size()) {
                //a가 더 많은 경우
                bList.add(aList.get(aList.size() - 1));
                aList.remove(aList.size() - 1);
                    
            } else {
                //b가 더 많은 경우
                aList.add(bList.get(bList.size() - 1));
                bList.remove(bList.size() - 1);
            }
            listDiff -= 2;
        }
        
        long result = 0;
        for(int i=0; i<aList.size(); i++) {
            result += aList.get(i).A;
        }
        
        for(int i=0; i<bList.size(); i++) {
            result += bList.get(i).B;
        }
        return result;
    }
}