import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      ArrayList<Integer> pList = new ArrayList<>();
      ArrayList<Integer> nList = new ArrayList<>();
      for(int i=0; i<N; i++) {
          int v = Integer.parseInt(st.nextToken());
          if(0 <= v) {
              pList.add(v);
          } else {
              nList.add(v);
          }
      }
      Collections.sort(pList, Collections.reverseOrder());
      Collections.sort(nList);
      
      Deque<Integer> pDeque = new ArrayDeque<>();
      Deque<Integer> nDeque = new ArrayDeque<>();
      
      for(int i=0; i<pList.size(); i++) {
          if(i % 2 == 0) {
              pDeque.addLast(pList.get(i));
          } else {
              pDeque.addFirst(pList.get(i));
          }
      }
      
      for(int i=0; i<nList.size(); i++) {
          if(i % 2 == 0) {
              nDeque.addLast(nList.get(i));
          } else {
              nDeque.addFirst(nList.get(i));
          }
      }
      
      if(!nDeque.isEmpty()) {
          boolean back = false;
          if(nDeque.peekLast() * pDeque.peekFirst() + nDeque.peekFirst() * pDeque.peekLast() <= nDeque.peekFirst() * pDeque.peekLast() + nDeque.peekLast() * pDeque.peekFirst()) {
              back = true;
          }
          
          while(!nDeque.isEmpty()) {
              if(back) {
                  pDeque.addLast(nDeque.pollFirst());
              } else {
                  pDeque.addFirst(nDeque.pollLast());
              }
          }
      }
    
      int[] arr = new int[N];
      for(int i=0; i<N; i++) {
          arr[i] = pDeque.pollFirst();
      }
      StringBuilder sb = new StringBuilder();
      int answer = 0;
      for(int i=0; i<N; i++) {
          sb.append(arr[i]).append(" ");
          if(i == N - 1) {
            answer += arr[i] * arr[0];
            break;  
          }
          answer += arr[i] * arr[i + 1];
      }
      System.out.println(answer);
      System.out.println(sb.toString().trim());
  }
}