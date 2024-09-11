import java.io.*;
import java.util.*;

class Info {
    int v; //총 몇 명
    HashMap<Integer, Integer> map;
    
    Info(int v) {
        this.v = v;
        map = new HashMap<>();
    }
}

class PeriodInfo {
    int p; //주기
    int v; //몇 명인지?
    PeriodInfo(int p, int v) {
        this.p = p;
        this.v = v;
    }
}

public class Main {
    static int n,m;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      Info[] info = new Info[200001];
      
      int[] num = new int[100001];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
          num[Integer.parseInt(st2.nextToken())] += 1;
      }
      
      for(int i=1; i<=100000; i++) {
          if(num[i] > 0) {
              for(int j=i; j<=200000; j+=i) {
                  if(info[j] == null) {
                      info[j] = new Info(0);
                  }
                  info[j].v += num[i];
                  info[j].map.put(i, num[i]);
              }
          }
      }
      
      StringTokenizer st3 = new StringTokenizer(br.readLine());
      int[] priPass = new int[200001]; //우선 승객
      for(int i=0; i<m; i++) {
          priPass[Integer.parseInt(st3.nextToken())] += 1; 
      }
      
      int vCnt = 0; //빈자리
      StringBuilder sb = new StringBuilder();
      for(int i=1; i<=200000; i++) {
          if(priPass[i] > 0) {
              if(vCnt > 0) {
                  //빈자리가 있으면
                  int len = priPass[i];
                  for(int j=1; j<=len; j++) {
                      if(vCnt == 0 || priPass[i] == 0) {
                          break;
                      }
                      priPass[i] -= 1;
                      vCnt -= 1;
                      sb.append(i).append(" ");
                  }
              } 
              
              if(priPass[i] > 0) {
                  //아직도 앉지 못한 우선 승객이 있다면 주기를 확인한다.
                  if(info[i] != null && info[i].v > 0) {
                      //주기가 있다면
                      vCnt += info[i].v;
                      int len = priPass[i];
                      for(int j=1; j<=len; j++) {
                          if(vCnt == 0 || priPass[i] == 0) {
                              break;
                          }
                          priPass[i] -= 1;
                          vCnt -= 1;
                          sb.append(i).append(" ");
                      }
                      
                      //주기를 삭제해 준다.
                      for(Map.Entry<Integer, Integer> entry : info[i].map.entrySet()) {
                          for(int j=i + entry.getKey(); j<=200000; j+=entry.getKey()) {
                              info[j].v -= entry.getValue();
                              info[j].map.remove(entry.getKey());
                          }
                      }
                  }
              }
              
              if(priPass[i] > 0) {
                  //우선권 승객이 남아있다면 다음으로 이월
                  priPass[i + 1] += priPass[i]; 
              }
          }
      }
      System.out.println(sb.toString().trim());
  }
}