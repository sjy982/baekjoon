import java.util.*;
class Solution {
    static HashMap<String, Integer> hashmap = new HashMap<>();
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        for(int i=0; i<name.length; i++) {
            hashmap.put(name[i], yearning[i]);
        }
        for(int i=0; i<photo.length; i++) {
            for(int j=0; j<photo[i].length; j++) {
                if(hashmap.get(photo[i][j]) != null) answer[i] += hashmap.get(photo[i][j]);
            }
        }
        return answer;
    }
}