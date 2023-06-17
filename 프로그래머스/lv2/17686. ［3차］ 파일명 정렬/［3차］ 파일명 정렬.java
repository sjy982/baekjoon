import java.util.*;
class File implements Comparable<File> {
    String head, number, tail, std_head;
    File(String head, String number, String tail, String std_head) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.std_head = std_head; //정렬 기준
    }
    
    public int compareTo(File o) {
        int dif = this.std_head.compareTo(o.std_head);
        if(dif < 0) return -1;
        else if(dif > 0) return 1;
        else {
            int difN = Integer.parseInt(this.number) - Integer.parseInt(o.number);
            if(difN < 0) return -1;
            else if(difN > 0) return 1;
        }
        return 0;
    }
    
}
class Solution {
    static ArrayList<File> file_list = new ArrayList<>();
    public String[] solution(String[] files) {
        for(int i=0; i<files.length; i++) {
            StringBuilder head_sb = new StringBuilder();
            StringBuilder num_sb = new StringBuilder();
            StringBuilder tail_sb = new StringBuilder();
            boolean isHead = true;
            boolean isNum = false;
            for(int j=0; j<files[i].length(); j++) {
                if(isHead) {
                    if(48 <= files[i].charAt(j) && files[i].charAt(j) <= 57) {
                        isHead = false;
                        isNum = true;
                    } else head_sb.append(files[i].charAt(j));
                }
                if(isNum) {
                    if(48 <= files[i].charAt(j) && files[i].charAt(j) <= 57) num_sb.append(files[i].charAt(j));
                    else isNum = false;
                }
                if(!isHead && !isNum) tail_sb.append(files[i].charAt(j));
            }
            // System.out.println(num_sb.toString());
            file_list.add(new File(head_sb.toString(), num_sb.toString(), tail_sb.toString(), head_sb.toString().toLowerCase()));
        }
        Collections.sort(file_list);
        String[] answer = new String[file_list.size()];
        for(int i=0; i<file_list.size(); i++) answer[i] = file_list.get(i).head + file_list.get(i).number + file_list.get(i).tail;
        return answer;
    }
}