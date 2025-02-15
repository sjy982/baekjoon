import java.io.*;
import java.util.*;

class Gifticon implements Comparable<Gifticon> {
    long A, B;
    Gifticon(long A, long B) {
        this.A = A;
        this.B = B;
    }
    
    @Override
    public int compareTo(Gifticon o) {
        if(this.B < o.B) {
            return -1;
        } else if(this.B > o.B) {
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
      Gifticon[] gc = new Gifticon[N];
      
      StringTokenizer stA = new StringTokenizer(br.readLine());
      StringTokenizer stB = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          gc[i] = new Gifticon(Long.parseLong(stA.nextToken()), Long.parseLong(stB.nextToken()));
      }
      
      Arrays.sort(gc);
      ArrayList<Long> list = new ArrayList<>();
      long answer = 0;
      long beforeDL = 0; //바로 직전에 사용한 기프티콘의 기한
      long curDL = 0;
      for(int i=0; i<N; i++) {
          if(beforeDL - gc[i].A > 0) {
              //beforeDL이 크다면 현재 A 값을 높여줘야 됨.
              long need = beforeDL - gc[i].A;
              curDL = gc[i].A + ((need / 30) * 30);
              answer += need / 30;
              if(need % 30 != 0) {
                  //0이 아니라면 연장 횟수 + 1추가
                  curDL += 30;
                  answer += 1;
              }
          } else {
              //beforeDL이 더 작다면 A를 높여줄 필요가 없음
              curDL = gc[i].A;
          }
          
          //여기부터는 curDL 최소한으로 더 큰 기한을 가지고 있음.
          //이번에는 계획에 맞춰줘야 됨.
          if(gc[i].B - curDL > 0) {
              //기한이 더 작다면 기한을 더 늘려줘야 됨
              //100 - 5 = 95 -> 95 / 30 = 3, 5 + 3 * 30 = 95 + 30 = 125
              long need = gc[i].B - curDL;
              curDL += (need / 30) * 30;
              answer += need / 30;
              if(need % 30 != 0) {
                  curDL += 30;
                  answer += 1;
              }
          }
          list.add(curDL);
          
          if(i != (N - 1)) {
              if(gc[i].B != gc[i + 1].B) {
                  Collections.sort(list);
                  beforeDL = list.get(list.size() - 1);
                  list = new ArrayList<>();
              }
          }
      }
      System.out.println(answer);
  }
}