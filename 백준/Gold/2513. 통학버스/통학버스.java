import java.io.*;
import java.util.*;

class Aprtment implements Comparable<Aprtment> {
    int p, n;
    Aprtment(int p, int n) {
        this.p = p;
        this.n = n;
    }
    
    @Override
    public int compareTo(Aprtment o) {
        if(this.p < o.p) {
            return -1;
        } else if(this.p > o.p) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, K, S;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      S = Integer.parseInt(st.nextToken());
      ArrayList<Aprtment> sList = new ArrayList<>();
      ArrayList<Aprtment> bList = new ArrayList<>();
      
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int p = Integer.parseInt(st2.nextToken());
          int n = Integer.parseInt(st2.nextToken());
          if(p < S) {
              sList.add(new Aprtment(p, n));
          } else {
              bList.add(new Aprtment(p, n));
          }
      }
      
      Collections.sort(sList);
      Collections.sort(bList, new Comparator<Aprtment>() {
          @Override
          public int compare(Aprtment a1, Aprtment a2) {
              if(a1.p < a2.p) {
                  return 1;
              } else if(a1.p > a2.p) {
                  return -1;
              }
              return 0;
          }
      });
      
      int answer = (start(sList) + start(bList));
      System.out.println(answer);
  }
  
  static int start(ArrayList<Aprtment> list) {
      int answer = 0;
      if(list.size() != 0) {
          int cursor = 0;
          int start = 0;
          int curK = 0;
          while(cursor < list.size()) {
              Aprtment a = list.get(cursor);
              if(curK != 0) {
                  //0이 아니라면 curK를 채워야됨.
                  int left = K - curK;
                  if(left <= a.n) {
                      a.n -= left;
                      answer += Math.abs(S - start);
                      curK = 0;
                  } else {
                      //left > a.n
                      curK += a.n; //현재 단지의 학생들을 전부 태운다.
                      //그리고 다음으로 넘어감.
                      cursor += 1;
                  }
                  continue;
              }
              
              start = a.p;
              int r = a.n / K;
              
              answer += (Math.abs(S - start) * 2 * r);
              
              if(a.n % K != 0) {
                  answer += Math.abs(S - start);
                  curK += a.n % K;
              }
              
              cursor += 1;
          }
          
          if(curK != 0) {
              //K를 다 채우지 못하고, 한쪽 구역의 모든 학생을 태웠음.
              answer += (Math.abs(S - start)); //학교 도착.
          }
      }
      //마지막은 언제나 학교임.
      return answer;
  }
}