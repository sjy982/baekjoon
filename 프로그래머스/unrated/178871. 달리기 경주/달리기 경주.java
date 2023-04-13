import java.util.*;
class Solution {
    static HashMap<String, Integer> rank_hashmap = new HashMap<>();
    static HashMap<Integer, String> name_hashmap = new HashMap<>();
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        for(int i=0; i<players.length; i++) {
            rank_hashmap.put(players[i], i+1);
            name_hashmap.put(i+1, players[i]);
        }
        for(int i=0; i<callings.length; i++) {
            int rank = rank_hashmap.get(callings[i]);
            String front_name = name_hashmap.get(rank-1);
            rank_hashmap.put(callings[i], rank-1);
            rank_hashmap.put(front_name, rank);
            name_hashmap.put(rank-1, callings[i]);
            name_hashmap.put(rank, front_name);
        }
        for(int i=1; i<=players.length; i++) {
            answer[i-1] = name_hashmap.get(i);
        }
        return answer;
    }
}