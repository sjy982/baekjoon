class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        return start(cap, deliveries, pickups);
    }
    
    static long start(int cap, int[] deliveries, int[] pickups) {
        long result = 0;
        int lastIndD = findLastInd(deliveries);
        int lastIndP = findLastInd(pickups);
        int lastInd = Math.max(lastIndD, lastIndP);
          
        while(lastInd != -1) {
            int std = Math.max(deliveries[lastInd], pickups[lastInd]);
            int cnt = std % cap == 0 ? std / cap : std / cap + 1;
            result += (long) (((lastInd + 1) * 2) * cnt);
            
            int max = cnt * cap;
            
            lastIndD = update(deliveries, lastIndD, max);
            lastIndP = update(pickups, lastIndP, max);
            
            lastInd = Math.max(lastIndD, lastIndP);
        }
        return result;
    }
    
    static int update(int[] arr, int lastInd, int max) {
        while(lastInd != -1) {
            if(arr[lastInd] == 0) {
                lastInd -= 1;
                continue;
            }
            
            if(max == 0) {
                break;
            }
            
            if(arr[lastInd] <= max) {
                max -= arr[lastInd];
                arr[lastInd] = 0;
            } else {
                arr[lastInd] -= max;
                max = 0;
            }
        }
        return lastInd;
    }
    
    static int findLastInd(int[] arr) {
        int lastInd = arr.length - 1;
        boolean end = true;
        for(int i=lastInd; i>=0; i--) {
            if(arr[i] == 0) {
                continue;
            }
            end = false;
            lastInd = i;
            break;
        }
        
        if(end) {
            return -1;
        }
        
        return lastInd;
    }
}