import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean n_decimal[];
    static long sum[];
    static int ans = 0;
    static ArrayList<Long> decimal_list = new ArrayList<>(); //소수 리스트
    public static void main(String args[])throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      n_decimal = new boolean[N+1];
      for(int i=2; i*i<=N; i++) {
          for(int j=i*i; j<=N; j+=i) {
              n_decimal[j] = true;
          }
      }
      for(int i=2; i<=N; i++) {
          if(!n_decimal[i]) decimal_list.add((long) i);
      }
      sum = new long[decimal_list.size()];
      for(int i=0; i<decimal_list.size(); i++) {
          if(i==0) sum[i] = decimal_list.get(i);
          else sum[i] = sum[i-1] + decimal_list.get(i);
      }
      
      for(int i=0; i<sum.length; i++) {
          if(sum[i] > N) {
              if(binary_search(i)) ans += 1;
          } else if(sum[i] == N) ans += 1;
      }
      System.out.println(ans);
    }
    
    static boolean binary_search(int e) {
        int min = 0;
        int max = e;
        long search_v = sum[e] - N;
        while(min<=max) {
            int mid = (min+max)/2;
            if(sum[mid] == search_v) return true;
            else if(sum[mid] > search_v) max = mid - 1;
            else if(sum[mid] < search_v) min = mid + 1;
        }
        return false;
    }
}