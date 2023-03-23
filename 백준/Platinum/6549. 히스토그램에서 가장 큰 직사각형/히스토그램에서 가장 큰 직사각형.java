import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long height[];
    static ArrayList<Integer> list;
    static long ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N==0) break;
            height = new long[N];
            list = new ArrayList<>();
            ans = 0;
            for(int i=0; i<N; i++) height[i] = Long.parseLong(st.nextToken());
            for(int i=0; i<N; i++) {
                if(list.size()==0) list.add(i);
                else {
                    if(height[i] <= height[list.get(list.size()-1)]) {
                        renewal_area(list.get(list.size()-1) + 1, i);
                    }
                    list.add(i);
                }
            }
            if(list.size()!=0) {
                int max_width = list.get(list.size()-1)+1;
                while(true) {
                    int ind = list.get(list.size()-1);
                    list.remove(list.get(list.size()-1));
                    if(list.size()==0) {
                        ans = Math.max(ans, height[ind] * (long) max_width);
                        break;
                    } else {
                        int peek = list.get(list.size()-1) + 1;
                        ans = Math.max(ans, height[ind] * (long) (max_width - peek));
                    }
                }
            }
            sb.append(ans + "\n");
        } 
        System.out.println(sb.toString().trim());
    }
    
    static void renewal_area(int max_width, int cur_ind) {
        while(true) {
            int ck_ind = list.get(list.size()-1);
            if(height[cur_ind] > height[ck_ind]) return;
            list.remove(list.size()-1);
            if(list.size()==0) {
                ans = Math.max(ans, height[ck_ind] * (long)max_width);
                return;
            } else {
                int peek = list.get(list.size()-1) + 1;
                ans = Math.max(ans, height[ck_ind] * (long)(max_width - peek));
            }
        }
    }
}