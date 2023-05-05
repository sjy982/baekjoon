import java.util.*;
import java.io.*;
class Solution {
    static HashMap<String, Integer> pTime_map = new HashMap<>();
    static HashMap<String, Integer> in_map = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        for(int i=0; i<records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i], " ");
            StringTokenizer time_st = new StringTokenizer(st.nextToken(), ":");
            int time = Integer.parseInt(time_st.nextToken()) * 60 + Integer.parseInt(time_st.nextToken());
            String car_number = st.nextToken();
            if(in_map.get(car_number) == null) in_map.put(car_number, time); //in
            else { //out
                int parking_time = time - in_map.get(car_number);
                if(pTime_map.get(car_number) == null) pTime_map.put(car_number, parking_time);
                else pTime_map.put(car_number, pTime_map.get(car_number) + parking_time);
                in_map.remove(car_number);
            }
        }
        in_map.forEach((key, value) -> {
            int parking_time = (23 * 60 + 59) - value;
            if(pTime_map.get(key) == null) pTime_map.put(key, parking_time);
            else pTime_map.put(key, pTime_map.get(key) + parking_time);
        });
        List<String> pKey_list = new ArrayList<>(pTime_map.keySet());
        pKey_list.sort((a,b) -> {
            for(int i=0; i<4; i++) {
                if(a.charAt(i) < b.charAt(i)) return -1;
                else if(a.charAt(i) > b.charAt(i)) return 1;
            }
            return -1;
        });
        answer = new int[pKey_list.size()];
        for(int i=0; i<pKey_list.size(); i++) {
            String car_number = pKey_list.get(i);
            if(fees[0] < pTime_map.get(car_number)) {
                double over_fee = ((pTime_map.get(car_number) - fees[0]) / (double) fees[2]);
                if(over_fee % 1 != 0) over_fee += 1;
                answer[i] = fees[1] + (int) over_fee * fees[3];
            } else answer[i] = fees[1];
        }
        return answer;
    }
}