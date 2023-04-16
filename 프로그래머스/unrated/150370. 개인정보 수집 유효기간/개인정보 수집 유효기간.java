import java.io.*;
import java.util.*;
class Solution {
    static ArrayList<Integer> ans_list = new ArrayList<>();
    static HashMap<String, Integer> terms_map = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] arr_today = changeInt(today.split("\\."));
        for(int i=0; i<terms.length; i++) {
            StringTokenizer st = new StringTokenizer(terms[i]);
            terms_map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i<privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            int arr_date[] = changeInt(st.nextToken().split("\\."));
            arr_date[1] += terms_map.get(st.nextToken());
            arr_date[2] -= 1;
            if(arr_date[2] == 0) {
                arr_date[2] = 28;
                arr_date[1] -= 1;
            }
            arr_date[0] += (arr_date[1] / 12 >= 1) && (arr_date[1] % 12 == 0) ? arr_date[1] / 12 - 1 : arr_date[1] / 12;
            arr_date[1] = arr_date[1] % 12 == 0 ? 12 : arr_date[1] % 12;
            for(int j=0; j<3; j++) {
                if(arr_today[j] > arr_date[j]) {
                    ans_list.add(i+1);
                    break;
                } else if(arr_today[j] < arr_date[j]) break;
            }
        }
        int[] answer = new int[ans_list.size()];
        for(int i=0; i<ans_list.size(); i++) {
            answer[i] = ans_list.get(i);
        }
        return answer;
    }
    
    static int[] changeInt(String[] arr_str) {
        int[] arr_int = new int[arr_str.length];
        for(int i=0; i<arr_str.length; i++) {
            arr_int[i] = Integer.parseInt(arr_str[i]);
        }
        return arr_int;
    }
}