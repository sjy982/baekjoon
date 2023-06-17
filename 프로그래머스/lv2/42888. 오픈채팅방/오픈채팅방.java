import java.util.*;
class Solution {
    static HashMap<String, String> user_nick = new HashMap<>();
    public String[] solution(String[] record) {
        for(int i=0; i<record.length; i++) {
            String[] split_record = record[i].split(" ");
            if(split_record[0].equals("Enter") || split_record[0].equals("Change")) user_nick.put(split_record[1], split_record[2]);
        }
        ArrayList<String> answer_list = new ArrayList<>();
        for(int i=0; i<record.length; i++) {
            String[] split_record = record[i].split(" ");
            if(split_record[0].equals("Enter")) {
                answer_list.add(user_nick.get(split_record[1]) + "님이 들어왔습니다.");
            } else if(split_record[0].equals("Leave")) {
                answer_list.add(user_nick.get(split_record[1]) + "님이 나갔습니다.");
            }
        }
        String[] answer = new String[answer_list.size()];
        for(int i=0; i<answer_list.size(); i++) answer[i] = answer_list.get(i);
        return answer;
    }
}