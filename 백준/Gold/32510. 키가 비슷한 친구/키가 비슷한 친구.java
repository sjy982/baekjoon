import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    int A, ind;
    Person(int ind, int A) {
        this.ind = ind;
        this.A = A;
    }
    
    @Override
    public int compareTo(Person o) {
        if(this.ind < o.ind) {
            return -1;
        } else if(this.ind > o.ind) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      
      StringBuilder sb = new StringBuilder();
      for(int t=1; t<=T; t++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken());
          int K = Integer.parseInt(st.nextToken());
          
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          ArrayList<Person> list = new ArrayList<>();
          for(int i=1; i<=N; i++) {
              list.add(new Person(i, Integer.parseInt(st2.nextToken())));
          }
          
          Collections.sort(list, new Comparator<Person>() {
              @Override
              public int compare(Person p1, Person p2) {
                  if(p1.A < p2.A) {
                      return -1;
                  } else if(p1.A == p2.A) {
                      if(p1.ind < p2.ind) {
                          return -1;
                      } else if(p1.ind > p2.ind) {
                          return 1;
                      }
                  } else {
                      return 1;
                  }
                  return 0;
              }
          });
          
          PriorityQueue<Person> pq = new PriorityQueue<>();
          
          long answer = 0;
          for(int i=0; i<list.size(); i++) {
              pq.add(list.get(i)); //먼저 현재 값을 넣어줌.
              int min = list.get(i).A - K; //최소 조건 이 값보단 크거나 같아야 됨.
              
              while(!pq.isEmpty()) {
                  //pq poll을 일단 조건에 맞게 조정
                  if(pq.peek().A >= min) {
                      answer += list.get(i).ind - pq.peek().ind;
                      break;
                  }
                  pq.poll();
              }
          }
          sb.append("Case #" + t).append("\n");
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}