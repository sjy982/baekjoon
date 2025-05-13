import java.io.*;
import java.util.*;

class Request {
    int start, time;
    Request(int start, int time) {
        this.start = start;
        this.time = time;
    }
}

class Solution {
    static ArrayList<Integer> result = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        ArrayList<Request>[] reqList = new ArrayList[k + 1];
        for(int i=1; i<=k; i++) {
            reqList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<reqs.length; i++) {
            reqList[reqs[i][2]].add(new Request(reqs[i][0], reqs[i][1]));
        }
        
        int[][] waitingTimeArr = new int[k + 1][n + 1];
        for(int i=1; i<=k; i++) {
            //i는 type
            for(int j=1; j<=n; j++) {
                //j는 멘토인원.
                waitingTimeArr[i][j] = findWaitingTime(reqList[i], j);
            }
        }
        //n - k를 하는 이유는 type별 최소 한명씩은 반드시 보장해야되기 때문에.
        dfs(n - k, k, waitingTimeArr);
        
        return answer;
    }
    
    static void dfs(int left, int k, int[][] waitingTimeArr) {
        if(result.size() == k - 1) {
            //마지막 구간이라면.
            result.add(1 + left);
            int waitingTime = 0;
            for(int i=1; i<=k; i++) {
                //i는 type
                int mentor = result.get(i - 1);
                waitingTime += waitingTimeArr[i][mentor];
            }
            answer = Math.min(answer, waitingTime);
            result.remove(result.size() - 1);
            return;
        }
        
        //k가 5라면 1 ~ 4까지만 for문을 돈다.
        for(int i=0; i<=left; i++) {
            result.add(1 + i);
            dfs(left - i, k, waitingTimeArr);
            result.remove(result.size() - 1);
        }
    }
    
    static int findWaitingTime(ArrayList<Request> list, int mentor) {
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<list.size(); i++) {
            int leftMentor = mentor - pq.size();
            if(leftMentor >= 1) {
                //대기시간 없이 바로 가능함.
                pq.add(list.get(i).start + list.get(i).time);
            } else {
                //leftMentor가 0명이라면.
                while(!pq.isEmpty() && pq.peek() <= list.get(i).start) {
                    pq.poll();
                }
                leftMentor = mentor - pq.size();
                if(leftMentor >= 1) {
                    pq.add(list.get(i).start + list.get(i).time);
                } else {
                    //그래도 0명이라면 대기시간 필요하다.
                    int end = pq.poll();
                    int waitingTime = end - list.get(i).start;
                    pq.add(end + list.get(i).time);
                    result += waitingTime;
                }
            }
        }
        return result;
    }
}