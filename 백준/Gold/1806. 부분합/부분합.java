import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long S;
    static long sum[];
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken());
        sum = new long[N];
        StringTokenizer n_st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            if(i==0) sum[i] = Long.parseLong(n_st.nextToken());
            else sum[i] = sum[i-1] + Long.parseLong(n_st.nextToken());
        }
        for(int i=0; i<N; i++) {
            if(sum[i]>S) {
                int ind = binary_search(i);
                if(ind != -1) ans.add(i - ind);
                else ans.add(i+1);
            } else if(sum[i]==S) ans.add(i+1);
        }
        if(ans.size()==0) System.out.println(0);
        else System.out.println(Collections.min(ans));
    }
    static int binary_search(int end) {
        int min = 0;
        int max = end;
        long std = sum[end] - S;
        boolean success = false;
        while(min<=max) {
            int mid = (min + max) / 2;
            if(sum[mid] <= std) {
                min = mid + 1;
                success = true;
            } else {
                max = mid - 1;
            }
        }
        if(success) return max;
        else return -1;
    }
}