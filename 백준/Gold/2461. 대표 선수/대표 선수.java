import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    int cls, abl; //반, 능력치
    
    Student(int cls, int abl) {
        this.cls = cls;
        this.abl = abl;
    }
    
    @Override
    public int compareTo(Student o) {
        if(this.abl < o.abl) {
            return -1;
        } else if(this.abl > o.abl) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      if(N == 1) {
          System.out.println(0);
          return;
      }
      
      M = Integer.parseInt(st.nextToken());
      ArrayList<Student> list = new ArrayList<>();
      
      for(int i=1; i<=N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              list.add(new Student(i, Integer.parseInt(st2.nextToken())));
          }
      }
      
      Collections.sort(list);
      
      int[] exptClass = new int[N + 1]; //각 반 별 제외한 학생의 수
      
      int left = 0;
      int right = N * M - 1;
      
      //left가 0일 때 right를 최대한 왼쪽으로 땡겨줄거임.
      while(true) {
          Student s = list.get(right); //제외했을 때 가능한지.
          if(exptClass[s.cls] + 1 >= M) {
              //현재 s를 제외했을 때 M보다 같거나 크다면 제외할 수 없음.
              //right는 그대로
              break;
          }
          
          //제외할 수 있음.
          exptClass[s.cls] += 1;
          right -= 1;
      }
      
      int answer = list.get(right).abl - list.get(left).abl;
      boolean end = false;
      //이제는 left를 오른쪽으로 전진시킨다.
      while(true) {
          left += 1; //left 커서를 오른쪽으로 전진. 그러면 바로 왼쪽은 제외된 것임.
          Student s = list.get(left - 1); //제외된 학생
          exptClass[s.cls] += 1;
          
          while(true) {
              if(exptClass[s.cls] < M) {
                  break;
              }
              //제외했을 때 M보다 크거나 같다면 결국 right를 조정해줘야 됨.
              right += 1; //right 커서를 오른쪽으로 전진.
              if(right == (N * M)) {
                  //right 커서가 범위를 넘어갔다면
                  end = true;
                  break;
              }
              
              Student rs = list.get(right); //포함될 학생.
              exptClass[rs.cls] -= 1;
          }
          if(end) {
              break;
          }
          
          answer = Math.min(answer, list.get(right).abl - list.get(left).abl);
      }
      System.out.println(answer);
  }
}