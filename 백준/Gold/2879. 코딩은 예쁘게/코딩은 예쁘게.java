import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tap, cTap;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tap = new int[N];
        cTap = new int[N];
    
        for(int i=0; i<2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                if(i == 0) {
                    tap[j] = Integer.parseInt(st.nextToken());
                } else {
                    cTap[j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        int answer = 0;
        for(int i=0; i<N; i++) {
            if(tap[i] != cTap[i]) {
                answer += edit(i);
            }
        }
        System.out.println(answer);
    }
    
    static int edit(int ind) {
        int cout = 0;
        //우선 + 연산인지, - 연산인지 확인한다.
        if(tap[ind] - cTap[ind] > 0) {
            // - 연산
            ArrayList<Integer> group = new ArrayList<>();
            for(int i=ind; i < N; i++) {
                if(tap[i] - cTap[i] <= 0) {
                    break;
                }
                group.add(i);
            }
            cout += cal(group, -1);
        } else if(tap[ind] - cTap[ind] < 0) {
            // + 연산
            ArrayList<Integer> group = new ArrayList<>();
            for(int i=ind; i < N; i++) {
                if(tap[i] - cTap[i] >= 0) {
                    break;
                }
                group.add(i);
            }
            cout += cal(group, 1);
        }
        return cout;
    }
    
    static int findMinGap(ArrayList<Integer> g) {
        int min = Integer.MAX_VALUE;
        int minInd = -1;
        for(int i=0; i<g.size(); i++) {
            int ind = g.get(i);
            if(Math.abs(tap[ind] - cTap[ind]) < min) {
                min = Math.abs(tap[ind] - cTap[ind]);
                minInd = ind;
            }
        }
        return minInd;
    }
    
    static int cal(ArrayList<Integer> g, int op) {
        int cout = 0;
        while(g.size() != 0) {
            int minInd = findMinGap(g);
            int value = op * Math.abs(tap[minInd] - cTap[minInd]);
            cout += Math.abs(value);
            for(int i=0; i<g.size(); i++) {
                tap[g.get(i)] += value;
            }
            while(true) {
                if(tap[g.get(g.size() - 1)] - cTap[g.get(g.size() - 1)] == 0) {
                    g.remove(g.size() - 1);
                    break;
                }
                g.remove(g.size() - 1);
            }
        }
        return cout;
    }
}