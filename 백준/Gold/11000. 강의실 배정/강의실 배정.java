import java.io.*;
import java.util.*;

class Lesson {
    int S, T;
    Lesson(int S, int T) {
        this.S = S;
        this.T = T;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Lesson[] list = new Lesson[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          list[i] = new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }
      
      Arrays.sort(list, new Comparator<Lesson>() {
          @Override
          public int compare(Lesson l1, Lesson l2) {
              if(l1.S < l2.S) {
                  return -1;
              } else if(l1.S > l2.S) {
                  return 1;
              }
              return 0;
          }
      });
      
      PriorityQueue<Integer> rooms = new PriorityQueue<>();
      
      for(int i=0; i<N; i++) {
          if(rooms.isEmpty()) {
              rooms.add(list[i].T);
          } else {
              if(rooms.peek() <= list[i].S) {
                  rooms.poll();
              }
              rooms.add(list[i].T);
          }
      }
      
      System.out.println(rooms.size());
  }
}