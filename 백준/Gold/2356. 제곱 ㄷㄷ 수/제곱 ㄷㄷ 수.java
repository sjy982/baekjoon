import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static HashMap<Character, Character> map = new HashMap<>();
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder(br.readLine());
      map.put('0', '2');
      map.put('1', '2');
      map.put('4', '5');
      map.put('5', '6');
      map.put('6', '7');
      map.put('9', '9');
      
      answer(sb.length() - 1);
      System.out.println(sb.toString());
  }
  
  static void answer(int end) {
      for(int i=0; i<=end; i++) {
          if(sb.charAt(i) == '0' || sb.charAt(i) == '1' || sb.charAt(i) == '4' || sb.charAt(i) == '9') {
              sb.setCharAt(i, map.get(sb.charAt(i)));
              for(int j=i + 1; j<=end; j++) {
                  //나머지 다 2로 변환
                  sb.setCharAt(j, '2');
              }
              
              if(sb.charAt(i) == '9') {
                  //전에 숫자가 9일 때
                  
                  long next = Long.parseLong(sb.substring(0, i + 1)) + 1;
                  sb.replace(0, i+1, String.valueOf(next));
                  int nextEnd = i;
                  if(i + 1 != String.valueOf(next).length()) {
                      //자리수가 하나 늘어남 ex) 999 -> 1000
                      nextEnd += 1;
                  }
                  answer(nextEnd);
              } else if(sb.charAt(i) == '5') {
                  //다음값이 5가 됨
                  answer(i);
              }
              break;
          } else if(sb.charAt(i) == '5' || sb.charAt(i) == '6') {
              if(check(i)) {
                  //변환될 필요가 있음
                  sb.setCharAt(i, map.get(sb.charAt(i)));
                  for(int j=i+1; j<=end; j++) {
                      //나머지 다 2로 변환
                      sb.setCharAt(j, '2');
                  }
                  answer(i);
                  break;
              }
          }
      }
  }
  
  static boolean check(int end) {
      StringBuilder sb2 = new StringBuilder();
      sb2.append(sb.charAt(end));
      for(int i=end - 1; i>=0; i--) {
          sb2.insert(0, sb.charAt(i));
          if(isPerfectSquare(Long.parseLong(sb2.toString()))) {
              return true;
          }
      }
      return false;
  }
  
  static boolean isPerfectSquare(long num) {
      long sqrt = (long) Math.sqrt(num);
      return (sqrt * sqrt == num);
  }
}