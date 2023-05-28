import java.util.*;
class Solution {
    static int[] view = new int[100*60*60 + 1];
    static long[] cmt_sum = new long[100*60*60 + 1]; //time 누적합 -> 시청시간
    public String solution(String play_time, String adv_time, String[] logs) {
       String answer = "";
        for(int i=0; i<logs.length; i++) {
            String[] time = logs[i].split("-");
            int start_sec = conversion_sec(time[0]);
            int end_sec = conversion_sec(time[1]);
            view[start_sec] += 1;
            view[end_sec] += -1;
        }
        for(int i=1; i<view.length; i++) view[i] += view[i-1];
        cmt_sum[0] = view[0];
        for(int i=1; i<cmt_sum.length; i++) cmt_sum[i] = cmt_sum[i-1] + view[i]; //누적합 계산
        // System.out.println(cmt_sum[conversion_sec("01:00:00")]);
        // System.out.println(conversion_sec("20:00:00") * 2);
        int play_time_sec = conversion_sec(play_time);
        int adv_time_sec = conversion_sec(adv_time);
        long max_time = cmt_sum[adv_time_sec-1];
        int adv_start_time = 0;
        for(int i=1; i<=play_time_sec; i++) {
            int adv_time_end = i + adv_time_sec-1;
            if(adv_time_end > play_time_sec) break;
            if(max_time < cmt_sum[adv_time_end] - cmt_sum[i-1]) {
                max_time = cmt_sum[adv_time_end] - cmt_sum[i-1];
                adv_start_time = i;
            }
        }
        answer = conversion_time(adv_start_time);
        return answer;
    }
    
    static int conversion_sec(String time) {
        String[] split_time = time.split(":");
        return Integer.parseInt(split_time[0]) * 3600 + Integer.parseInt(split_time[1]) * 60 + Integer.parseInt(split_time[2]);
    }
    static String conversion_time(int sec) {
        String str_hour = sec / 3600 < 10 ? "0" + String.valueOf(sec / 3600) : String.valueOf(sec / 3600);
        String str_min = (sec % 3600) / 60 < 10 ? "0" + String.valueOf((sec % 3600) / 60) : String.valueOf((sec % 3600) / 60);
        String str_sec = (sec % 3600) % 60 < 10 ? "0" + String.valueOf((sec % 3600) % 60) : String.valueOf((sec % 3600) % 60);
        return str_hour + ":" +  str_min + ":" + str_sec;
    }
}