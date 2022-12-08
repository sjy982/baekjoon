import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int post[], in[], in_ind[];
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        post = new int[N];
        in = new int[N];
        in_ind = new int[N+1];
        for(int i=0; i<2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(i==0) {
                for(int j=0; j<N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    in[j] = v;
                    in_ind[v] = j;
                }
            } else if(i==1) {
                for(int j=0; j<N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    post[j] = v;
                }
            }
        }
        get_pre(0, N-1, 0, N-1);
        System.out.println(ans.toString());
    }
    
    static void get_pre(int in_s, int in_e, int post_s, int post_e) {
        int rt_v = post[post_e];
        ans.append(rt_v + " ");
        
        int rt_ind = in_ind[rt_v];
        if(rt_ind != in_s) {
            get_pre(in_s, rt_ind - 1, post_s, post_s + (rt_ind - 1 - in_s)); //왼쪽
        }
        if(rt_ind != in_e) {
            get_pre(rt_ind + 1, in_e, post_e - (in_e - rt_ind), post_e - 1); //오른쪽
        }
    }
}