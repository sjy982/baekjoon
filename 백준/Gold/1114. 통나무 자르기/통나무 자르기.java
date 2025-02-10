import java.io.*;
import java.util.*;

public class Main {
    static int L, K, C, answer;
    static int fc = -1;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      
      HashMap<Integer, Boolean> visited = new HashMap<>();
      ArrayList<Integer> list = new ArrayList<>();
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=0; i<K ;i++) {
          int num = Integer.parseInt(st2.nextToken());
          if(visited.get(num) == null) {
              visited.put(num, true);
              list.add(num);
          }
      }
      
      Collections.sort(list);
      
      binarySearch(list);
      
      System.out.println(answer + " " + (list.get(fc)));
  }
  
  static void binarySearch(ArrayList<Integer> list) {
      int min = 1;
      int max = L;
      while(min <= max) {
          int mid = (min + max) / 2;
          int result = isPosible(mid, list); //-1이면 불가능
          if(result != -1) {
              //가능한 경우라면
              fc = result;
              max = mid - 1;
          } else {
              //불가능한 경우라면 
              min = mid + 1;
          }
          
      }
      answer = min;
  }
  
  static int isPosible(int maxLen, ArrayList<Integer> list) {
      int result = -1; // first cut index
      //가장 오른쪽에서부터 잘라나간다.
      int end = L;//통나무 끝
      int endIndex = list.size(); //통나무 끝 인덱스
      int cnt = 0; //자른 횟수
      int cursor = list.size() - 1;
      
      while(cursor >= 0) {
          if(end - list.get(cursor) <= maxLen) {
              cursor -= 1;
          } else {
              //end - list.get(cursor) > maxLen 라면 잘라줘야 될 위치는 cursor + 1
              if(cursor + 1 >= endIndex) {
                  //잘라줘야 될 위치가 통나무 끝을 포함해서 벗어난다면 불가능한 경우임
                  return -1;
              } else {
                  //최선으로 잘라줄 수 있는 위치임
                  if(cnt + 1 > C) {
                      //전체 자를 수 있는 횟수를 넘는다면 불가능함.
                      return -1;
                  }
                  //횟수도 가능한 경우 잘라준다.
                  cnt += 1;
                  end = list.get(cursor + 1);
                  endIndex = cursor + 1;
              }
              
          }
      }
      //여기까지 왔다면 cursor = -1를 가지고 있음
      if(cnt == C) {
          //더 이상 자를 수 없다면
          if(end > maxLen) {
              return -1;
          }
          //end <= max
          result = endIndex; //가장 첫 번째로 자른 위치는 endIndex임
      } else {
          //더 자를 수 있음.
          end = list.get(0);
          endIndex = 0;
          
          if(end > maxLen) {
              return -1;
          }
          //end <= max
          result = endIndex;
      }
      return result;
  }
}