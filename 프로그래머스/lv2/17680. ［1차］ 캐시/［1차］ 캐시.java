import java.util.*;
class Solution {
    static Queue<String> cache_que = new LinkedList<>();
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        for(int i=0; i<cities.length; i++) {
            if(find_cities(cities[i].toLowerCase())) {
                //hit
                answer += 1;
            } else {
                //miss
                if(cache_que.size() == cacheSize) cache_que.poll();
                answer += 5;
            }
            if(cache_que.size() < cacheSize) cache_que.add(cities[i].toLowerCase());
        }
        return answer;
    }
    
    static boolean find_cities(String str) {
        if(cache_que.remove(str)) return true;
        return false;
    }
}