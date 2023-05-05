import java.util.*;
class Solution {
    static HashMap<String, Integer> want_map = new HashMap<>();
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        for(int i=0; i<want.length; i++) want_map.put(want[i], number[i]);
        for(int i=0; i<discount.length; i++) {
            if(i+9 == discount.length) break;
            HashMap<String, Integer> dc_map = new HashMap<>();
            for(int j=i; j<=i+9; j++) {
                if(dc_map.get(discount[j]) == null) dc_map.put(discount[j], 1);
                else dc_map.put(discount[j], dc_map.get(discount[j]) + 1);
            }
            if(check(dc_map)) answer += 1;
            
        }
        return answer;
    }
    static boolean check(HashMap<String, Integer> dc_map) {
        Iterator<String> keys = want_map.keySet().iterator();
        while(keys.hasNext()) {
            String str_key = keys.next();
            if((dc_map.get(str_key) == null) || want_map.get(str_key) != dc_map.get(str_key)) return false;
        }
        return true;
    }
}