import java.io.*;
import java.util.*;

class Info {
    int num, cnt;
    Info(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[N];
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Info> list = new ArrayList<>();
      
      int cnt = 1;
      for(int i=1; i<N; i++) {
          if(arr[i] == arr[i - 1]) {
              cnt += 1;
          } else {
              list.add(new Info(arr[i - 1], cnt));
              cnt = 1;
          }
      }
      
      list.add(new Info(arr[N - 1], cnt));
      
      ArrayList<Info> newList = new ArrayList<>();
      for(int i=0; i<list.size(); i++) {
          if(list.get(i).num == 1 && (i == 0 || i == list.size() - 1 || list.get(i).cnt % 2 == 0)) {
            list.get(i).num = 2;
            list.get(i).cnt = list.get(i).cnt / 2;
          }
          
          if(i == 0) {
              cnt = list.get(i).cnt;
          } else {
              if(list.get(i).num == list.get(i - 1).num) {
                  cnt += list.get(i).cnt;
              } else {
                  newList.add(new Info(list.get(i - 1).num, cnt));
                  cnt = list.get(i).cnt;
              }
          }
          
          if(i == list.size() - 1) {
              newList.add(new Info(list.get(list.size() - 1).num, cnt));
          }
      }
      
      int max = 0;
      for(int i=0; i<newList.size(); i++) {
          if(newList.get(i).num == 1) {
              continue;
          }
          int len = newList.get(i).cnt;
          if(i == 0) {
              if(i + 1 < newList.size()) {
                  len += newList.get(i + 1).cnt / 2;
              }
          } else if(i == newList.size() - 1) {
              if(i - 1 >= 0) {
                  len += newList.get(i - 1).cnt / 2;
              }
          } else {
              if(newList.get(i).num == 2) {
                  len += newList.get(i - 1).cnt / 2 + newList.get(i + 1).cnt / 2;
              }
          }
          if(max < len) {
              max = len;
          }
      }
      if(max == 0) {
          System.out.println(1);
      } else {
          int k = 1;
          while(Math.pow(2, k) <= max) {
              k += 1;
          }
          System.out.println((int) Math.pow(2, k - 1) * 2);
      }
  }
}