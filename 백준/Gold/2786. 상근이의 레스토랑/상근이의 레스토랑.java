import java.io.*;
import java.util.*;

class Food implements Comparable<Food> {
    long a, b;
    int ind;
    Food(int ind, long a, long b) {
        this.ind = ind;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public int compareTo(Food o) {
        if(this.a < o.a) {
            return -1;
        } else if(this.a > o.a) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      N = Integer.parseInt(br.readLine());
      Food foodsB[] = new Food[N];
      Food foodsA[] = new Food[N];
      long beforeBSum = 0;
      PriorityQueue<Long> a_b = new PriorityQueue<>();
      boolean visited[] = new boolean[N];
      StringBuilder sb = new StringBuilder();
      
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          Food food = new Food(i, Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
          foodsB[i] = food;
          foodsA[i] = food;
      }
      Arrays.sort(foodsA);
      Arrays.sort(foodsB, new Comparator<Food>() {
          @Override
          public int compare(Food o1, Food o2) {
              if(o1.b < o2.b) {
                  return -1;
              } else if(o1.b > o2.b) {
                  return 1;
              }
              return 0;
          }
      });
      
      int cursorA = 0;
      for(int i=0; i<N; i++) {
          beforeBSum += foodsB[i].b;
          visited[foodsB[i].ind] = true;
          a_b.add(foodsB[i].a - foodsB[i].b);
          
          long v1 = beforeBSum + a_b.peek();
          
          if(i == N - 1) {
              sb.append(v1);
              break;
          }
          
          while(visited[foodsA[cursorA].ind]) {
              cursorA += 1;
          }
          
          long v2 = beforeBSum - foodsB[i].b + foodsA[cursorA].a;
          
          sb.append(Math.min(v1, v2)).append("\n");
      }
      
      System.out.println(sb.toString());
  }
}