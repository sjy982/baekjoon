import java.util.*;
class Music implements Comparable<Music> {
    String title;
    int play_time;
    Music(String title, int play_time) {
        this.title = title;
        this.play_time = play_time;
    }
    
    public int compareTo(Music m) {
        int dif = this.play_time - m.play_time;
        if(dif < 0) return 1;
        else if(dif > 0) return -1;
        return 0;
    }
}
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        ArrayList<String> m_list = convert_sheetMusic(m);
        ArrayList<Music> answer_list = new ArrayList<>();
        for(int i=0; i<musicinfos.length; i++) {
            String[] split_info = musicinfos[i].split(",");
            int play_time = convert_min(split_info[1]) - convert_min(split_info[0]);
            ArrayList<String> melody = find_melody(convert_sheetMusic(split_info[3]), play_time);
            if((m_list.size() <= melody.size()) && check_melody(m_list, melody)) {
                answer_list.add(new Music(split_info[2], play_time));
            }
        }
        if(answer_list.size() == 0) answer = "(None)";
        else {
            Collections.sort(answer_list);
            answer = answer_list.get(0).title;
        }
        return answer;
    }
    
    static boolean check_melody(ArrayList<String> m, ArrayList<String> melody) {
        for(int i=0; i<melody.size(); i++) {
            if(i + m.size() - 1 == melody.size()) break;
            boolean same = true;
            for(int j = i; j < i + m.size(); j++) {
                if(!m.get(j - i).equals(melody.get(j))) {
                    same = false;
                    break;
                }
            }
            if(same) return true;
        }
        return false;
    }
    
    static ArrayList<String> convert_sheetMusic(String m) {
        ArrayList<String> sheet_music = new ArrayList<>();
        for(int i=0; i<m.length(); i++) {
            if(m.charAt(i) == '#') {
                sheet_music.add(String.valueOf(m.charAt(i-1)) + String.valueOf(m.charAt(i)));
            } else {
                if(i == m.length() - 1) sheet_music.add(String.valueOf(m.charAt(i)));
                else if(m.charAt(i+1) != '#') sheet_music.add(String.valueOf(m.charAt(i)));
            }
        }
        return sheet_music;
    }
    
    static ArrayList<String> find_melody(ArrayList<String> sm, int time) {
        ArrayList<String> melody = new ArrayList<>();
        for(int i=0; i<time; i++) melody.add(sm.get(i % sm.size()));
        return melody;
    }
    
    static int convert_min(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
}