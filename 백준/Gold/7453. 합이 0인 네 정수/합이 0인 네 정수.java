import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int arr[][];
    static long sum_1[]; // A, B의 모든 합
    static long sum_2[]; //C, D의 모든 합
    static long ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[4][N];
        sum_1 = new long[N*N+1];
        sum_2 = new long[N*N+1];
        sum_1[N*N] = 1000000000000L;
        sum_2[N*N] = 1000000000000L;
        for(int i=0; i<N; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                arr[j][i] = Integer.parseInt(n_st.nextToken());
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sum_1[i*N+j] = (long) arr[0][i] + (long) arr[1][j];
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sum_2[i*N+j] = (long) arr[2][i] + (long) arr[3][j];
            }
        }
        Arrays.sort(sum_1);
        Arrays.sort(sum_2);
        //합이 0이 되는 쌍의 개수 찾기
        int start_index = 0;
        int end_index = 0;
        while(end_index != sum_1.length-1) {
            end_index = upper_bound(sum_1[start_index], sum_1);
            long v = 0 - sum_1[start_index];
            ans += (long)(end_index - start_index) * ((long)upper_bound(v, sum_2) - (long)lower_bound(v, sum_2));
            start_index = end_index;
        }
        System.out.println(ans);
    }
    static int lower_bound(long search_value, long sum[]) {
        int min = 0;
        int max = sum.length-1;
        while(min<max) {
            int mid = (min + max)/2;
            if(sum[mid]>=search_value) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
    
    static int upper_bound(long search_value, long sum[]) {
        int min = 0;
        int max = sum.length-1;
        while(min<max) {
            int mid = (min + max)/2;
            if(sum[mid]<=search_value) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
}