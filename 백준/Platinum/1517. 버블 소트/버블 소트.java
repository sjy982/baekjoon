import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long sq[];
    static long swap = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      sq = new long[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          sq[i] = Long.parseLong(st.nextToken());
      }
      merge_sort(sq);
      System.out.println(swap);
    }
    
    static long[] merge_sort(long p_sq[]) {
        if(p_sq.length == 1) return p_sq;
        else {
            //분할과정
            long left[] = new long[p_sq.length/2];
            long right[] = new long[p_sq.length - p_sq.length/2];
            left = merge_sort(Arrays.copyOfRange(p_sq, 0, p_sq.length/2));
            right = merge_sort(Arrays.copyOfRange(p_sq, p_sq.length/2, p_sq.length));
            //병합 과정
            int l_ind = 0;
            int r_ind = 0;
            int p_ind = 0;
            while(l_ind != left.length || r_ind != right.length) {
                if(left[l_ind] <= right[r_ind]) {
                    p_sq[p_ind] = left[l_ind];
                    l_ind += 1;
                } else {
                    p_sq[p_ind] = right[r_ind];
                    r_ind += 1;
                    swap += left.length - l_ind;
                }
                p_ind +=1;
                if(l_ind == left.length) {
                    for(int i=r_ind; i<right.length; i++) {
                        p_sq[p_ind] = right[i];
                        p_ind += 1;
                    }
                    r_ind = right.length;
                } else if(r_ind == right.length) {
                    for(int i=l_ind; i<left.length; i++) {
                        p_sq[p_ind] = left[i];
                        p_ind += 1;
                    }
                    l_ind = left.length;
                }
            }
            return p_sq;
        }
    }
}