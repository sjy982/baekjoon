import java.io.*;
import java.util.*;

class Job implements Comparable<Job> {
    int time, coreInd;
    Job(int time, int coreInd) {
        this.time = time;
        this.coreInd = coreInd;
    }
    
    @Override
    public int compareTo(Job o) {
        if(this.time < o.time) {
            return -1;
        } else if(this.time > o.time) {
            return 1;
        } else {
            //같다면
            if(this.coreInd < o.coreInd) {
                return -1;
            } else if(this.coreInd > o.coreInd) {
                return 1;
            }
        }
        return 0;
    }
}

class Solution {
    public int solution(int n, int[] cores) {
        if(n <= cores.length) {
            return n;
        }
        
        int targetTime = binarySearch(n - 1, cores);
        long leftN = n - findProcessedJob(cores, targetTime); //남은 작업의 수를 구할거임.
        
        //leftN은 0일 수 없다.
        int cnt = 0;
        int answer = 0;
        for(int i=0; i<cores.length; i++) {
            if(targetTime % cores[i] == 0) {
                //targetTime에 새로운 작업을 진행할 수 있는 코어임.
                cnt += 1;
                if(leftN == cnt) {
                    //마지막 작업이 부여된 경우
                    answer = i + 1;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    static int binarySearch(int targetN, int[] cores) {
        int min = 1;
        int max = 10000 * 50000;
        
        while(min <= max) {
            int mid = (min + max) / 2;
            long pj = findProcessedJob(cores, mid);
            
            if(pj <= targetN) {
                min = mid + 1;
            } else if(pj > targetN){
                max = mid - 1;
            }
        }
        return max;
    }
    
    static long findProcessedJob(int[] cores, int mid) {
        long result = 0;
        for(int i=0; i<cores.length; i++) {
            result += (mid / cores[i]);
            if(mid % cores[i] != 0) {
                result += 1;
            }
        }
        return result;
    }
}