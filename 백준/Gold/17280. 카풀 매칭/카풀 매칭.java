import java.io.*;
import java.util.*;

class Driver implements Comparable<Driver> {
    int y, z;
    Driver(int y, int z) {
        this.y = y;
        this.z = z;
    }
    
    @Override
    public int compareTo(Driver o) {
        if(this.z < o.z) {
            return -1;
        } else if(this.z > o.z) {
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
      for(int t=0; t<T; t++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken()); //승객의 수
          int M = Integer.parseInt(st.nextToken()); //운전사의 수
          
          int[] pArr = new int[N]; //승객 배열
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int i=0; i<N; i++) {
              pArr[i] = Integer.parseInt(st2.nextToken());
          }
          
          Arrays.sort(pArr); //승객을 오름차순으로 정렬한다. x기준
          
          Driver[] dArr = new Driver[M];
          for(int i=0; i<M; i++) {
              StringTokenizer st3 = new StringTokenizer(br.readLine());
              int y = Integer.parseInt(st3.nextToken());
              int z = Integer.parseInt(st3.nextToken());
              dArr[i] = new Driver(y, z);
          }
          
          Arrays.sort(dArr, new Comparator<Driver>() {
              //y를 기준으로 오름차순한다.
              @Override
              public int compare(Driver d1, Driver d2) {
                  if(d1.y < d2.y) {
                      return -1;
                  } else if(d1.y > d2.y) {
                      return 1;
                  }
                  return 0;
              }
          });
          
          int answer = 0;
          PriorityQueue<Driver> pq = new PriorityQueue<>();
          int dArrCursor = 0;
          
          for(int i=0; i<N; i++) {
              while(dArrCursor < M) {
                  if(pArr[i] < dArr[dArrCursor].y) {
                      break;
                  }
                  
                  pq.add(dArr[dArrCursor]);
                  dArrCursor += 1;
              }
              
              // pq에는 승객을 태울 수 있는 운전자 객체가 들어가있다.
              //그런데 z를 추가적으로 확인해야한다.
              
              //최적의 운전자를 하나 매칭해 준다.
              while(!pq.isEmpty()) {
                  Driver d = pq.poll();
                  if(pArr[i] <= d.z) {
                      //매칭 지점
                      answer += 1;
                      break;
                  }
              }
          }
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}