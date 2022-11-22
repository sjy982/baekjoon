import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> ngtive = new ArrayList<>();
    static ArrayList<Integer> pstive = new ArrayList<>();
    static int N;
    static long ans = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          int n = Integer.parseInt(br.readLine());
          if(n>0) {
              pstive.add(n);
          } else {
              ngtive.add(n);
          }
      }
      Collections.sort(pstive, Collections.reverseOrder());
      Collections.sort(ngtive);
      
      for(int i=0; i<pstive.size(); i++) {
          if(pstive.size()%2 == 1 && i == pstive.size() - 1) {
              ans += pstive.get(i);
          } else if(i % 2 == 1) {
              if(pstive.get(i-1) != 1 && pstive.get(i) != 1) {
                  ans += pstive.get(i-1) * pstive.get(i);
              } else {
                  ans += pstive.get(i-1) + pstive.get(i);
              }
          }
      }
      
      for(int i=0; i<ngtive.size(); i++) {
          if(ngtive.size()%2 == 1 && i == ngtive.size() - 1) {
              ans += ngtive.get(i);
          } else if(i % 2 == 1) {
              ans += ngtive.get(i-1) * ngtive.get(i);
          }
      }
      System.out.println(ans);
    }
}