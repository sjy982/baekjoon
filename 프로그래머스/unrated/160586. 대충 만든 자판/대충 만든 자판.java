import java.util.*;

class Solution {
    static HashMap<Character, Integer> hashmap = new HashMap<>();
    public int[] solution(String[] keymap, String[] targets) {
        for(int i=0; i<keymap.length; i++) {
            for(int j=0; j<keymap[i].length(); j++) {
                char word = keymap[i].charAt(j);
                if(hashmap.get(word)==null) hashmap.put(word, j+1);
                else {
                    if(hashmap.get(word) > j+1) hashmap.put(word, j+1);
                }
            }
        }
        int[] answer = new int[targets.length];
        for(int i=0; i<targets.length; i++) {
            int sum = 0;
            for(int j=0; j<targets[i].length(); j++) {
                char word = targets[i].charAt(j);
                if(hashmap.get(word) == null) {
                    sum = -1;
                    break;
                } else sum += hashmap.get(word);
            }
            answer[i] = sum;
        }
        return answer;
    }
}