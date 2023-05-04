import java.util.*;
class Solution {
    static int[] front_sum;
    static int[] back_sum;
    static HashMap<Integer, Boolean> hashMap = new HashMap<>();
    public int solution(int[] topping) {
        int answer = 0;
        front_sum = new int[topping.length];
        back_sum = new int[topping.length];
        for(int i=0; i<topping.length; i++) {
            if(i==0) {
                front_sum[i] = 1;
                hashMap.put(topping[i], true);
            } else {
                if(hashMap.get(topping[i]) == null) {
                    hashMap.put(topping[i], true);
                    front_sum[i] = front_sum[i-1] + 1;
                } else front_sum[i] = front_sum[i-1];
            }
        }
        hashMap = new HashMap<>();
        for(int i=topping.length-1; i>=0; i--) {
            if(i==topping.length-1) {
                back_sum[i] = 1;
                hashMap.put(topping[i], true);
            } else {
                if(hashMap.get(topping[i]) == null) {
                    hashMap.put(topping[i], true);
                    back_sum[i] = back_sum[i+1] + 1;
                } else back_sum[i] = back_sum[i+1];
            }
        }
        for(int i=0; i<topping.length-1; i++) if(front_sum[i] == back_sum[i+1]) answer += 1;
        return answer;
    }
}