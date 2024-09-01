import java.io.*;
import java.util.*;

class Person {
    int v, ind, c, a;
    Person(int ind, int c, int a) {
        this.c = c;
        this.a = a;
        this.ind = ind;
        this.v = c - a;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Person[] arr = new Person[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          arr[i] = new Person(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }
      
      Arrays.sort(arr, new Comparator<Person>() {
         @Override
         public int compare(Person p1, Person p2) {
             return Integer.compare(p2.v, p1.v);
         }
      });
      
      HashMap<Integer, Integer> map = new HashMap<>();
      long min = 0;
      for(int i=0; i<N; i++) {
          if(map.get(arr[i].v) == null) {
              map.put(arr[i].v, 0);
          }
          map.put(arr[i].v, map.get(arr[i].v) + 1);
          min += (long) arr[i].c * i + (long) arr[i].a * (N - (i + 1));
      }
      
      long noc = 1;
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          noc *= factorial(entry.getValue());
          noc %= 998244353;
      }
      
      sb.append(min).append(" ").append(noc).append("\n");
      
      for(int i=0; i<N; i++) {
          sb.append(arr[i].ind).append(" ");
      }
      sb.append("\n");
      
      Arrays.sort(arr, new Comparator<Person>() {
         @Override
         public int compare(Person p1, Person p2) {
             return Integer.compare(p1.v, p2.v);
         }
      });
      long max = 0;
      for(int i=0; i<N; i++) {
          max += (long) arr[i].c * i + (long) arr[i].a * (N - (i + 1));
      }
      
      sb.append(max).append(" ").append(noc).append("\n");
      
      for(int i=0; i<N; i++) {
          sb.append(arr[i].ind).append(" ");
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static long factorial(int n) {
      long result = 1;
      for(int i=2; i<=n; i++) {
         result *= i;
         result %= 998244353;
      }
      return result;
  }
}