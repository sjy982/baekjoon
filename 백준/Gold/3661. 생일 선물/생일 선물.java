import java.io.*;
import java.util.*;

class Info implements Comparable<Info> {
    int ind, max, pay;
    Info(int ind, int max, int pay) {
        this.ind = ind;
        this.max = max;
        this.pay = pay;
    }
    
    @Override
    public int compareTo(Info o) {
        if(this.max < o.max) {
            return 1;
        } else if(this.max > o.max) {
            return -1;
        } else {
            if(this.ind < o.ind) {
                return -1;
            } else if(this.ind > o.ind) {
                return 1;
            }
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
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int bestP = p / n;
            
            Info[] fList = new Info[n];
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int totalPay = 0;
            for(int j=0; j<n; j++) {
                int max = Integer.parseInt(st2.nextToken());
                fList[j] = new Info(j, max, max >= bestP ? bestP : max);
                totalPay += fList[j].pay;
            } 
            int leftP = p - totalPay;
            Arrays.sort(fList);
            
            int cursor = 0;
            while(leftP != 0) {
                if(cursor == fList.length || fList[cursor].max - fList[cursor].pay == 0) {
                    if(cursor == 0) {
                        break;
                    }
                    cursor = 0;
                    continue;
                }
                fList[cursor].pay += 1;
                leftP -= 1;
                cursor += 1;
            }
            
            if(leftP != 0) {
                sb.append("IMPOSSIBLE").append("\n");
            } else {
                Arrays.sort(fList, new Comparator<Info>() {
                    @Override
                    public int compare(Info f1, Info f2) {
                        return Integer.compare(f1.ind, f2.ind);
                    }
                });
                
                for(int k=0; k<fList.length; k++) {
                    sb.append(fList[k].pay).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
}