import java.io.*;
import java.util.*;

class Meat implements Comparable<Meat>{
    int p, m;
    Meat(int m, int p) {
        this.m = m;
        this.p = p;
    }
    
    @Override
    public int compareTo(Meat o) {
        if(this.p < o.p) {
            return -1;
        } else if(this.p > o.p) {
            return 1;
        } else {
            if(this.m < o.m) {
                return 1;
            } else if(this.m > o.m) {
                return -1;
            }
        }
        return 0;
    }
}

class Section {
    int p;
    ArrayList<Integer> mList;
    Section(int p, ArrayList<Integer> list) {
        this.p = p;
        this.mList = list;
    }
}

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      Meat[] arr = new Meat[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          arr[i] = new Meat(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
      }
      
      Arrays.sort(arr);
      
      ArrayList<Section> sList = new ArrayList<>();
      for(int i=0; i<N; i++) {
          if(sList.isEmpty() || (sList.get(sList.size() - 1).p != arr[i].p)) {
              sList.add(new Section(arr[i].p, new ArrayList<>(Arrays.asList(arr[i].m))));
          } else {
              sList.get(sList.size() - 1).mList.add(arr[i].m);
          }
      }
      
      int mSum = 0;
      int pSum = 0;
      for(int i=0; i<sList.size(); i++) {
          ArrayList<Integer> mList = sList.get(i).mList;
          for(int j=0; j<mList.size(); j++) {
              mSum += mList.get(j);
              pSum += sList.get(i).p;
              if(mSum >= M) {
                  break;
              }
          }
          if(mSum >= M) {
              //충족했다면.
              if(i < sList.size() - 1 && (sList.get(i + 1).p < pSum)) {
                  //다음 섹션이 있고, 다음 섹션의 초깃값이 더 작다면 업데이트
                  pSum = sList.get(i + 1).p;
              }
              break;
          } else {
              //충족 못했으면 pSum은 초기화
              pSum = 0;
          }
          
      }
      if(pSum == 0) {
          System.out.println(-1);
      } else {
          System.out.println(pSum);
      }
      
  }
}