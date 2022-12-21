import java.io.*;
import java.util.*;

class NumInfo {
    int n,ind;
    NumInfo(int n, int ind) {
        this.n = n;
        this.ind = ind;
    }
}

public class Main {
    static int N;
    static int ans = -1;
    static ArrayList<NumInfo> list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          int n = Integer.parseInt(br.readLine());
          list.add(new NumInfo(n, i));
      }
      Collections.sort(list,(a,b) -> {
         return a.n - b.n; 
      });
      
      for(int i=0; i<N; i++) {
          int mc = list.get(i).ind - i;
          if(ans < mc) ans = mc;
      }
      System.out.println(ans + 1);
    }
}