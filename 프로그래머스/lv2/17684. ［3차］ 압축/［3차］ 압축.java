import java.util.*;
class Solution {
    static HashMap<String, Integer> dictionary = new HashMap<>();
    public int[] solution(String msg) {
        for(int i=0; i<26; i++) dictionary.put(String.valueOf((char)(65 + i)), i + 1);
        ArrayList<Integer> index_list = new ArrayList<>();
        int p = 0;
        while(p < msg.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(p));
            while(dictionary.get(sb.toString()) != null) {
                p += 1;
                if(p == msg.length()) break;
                sb.append(msg.charAt(p));
            }
            String str = sb.toString();
            if(p == msg.length()) index_list.add(dictionary.get(str)); 
            else {
                index_list.add(dictionary.get(str.substring(0, str.length() - 1)));
                dictionary.put(str, dictionary.size() + 1);
            }
        }
        int[] answer = new int[index_list.size()];
        for(int i=0; i<index_list.size(); i++) answer[i] = index_list.get(i);
        return answer;
    }
}