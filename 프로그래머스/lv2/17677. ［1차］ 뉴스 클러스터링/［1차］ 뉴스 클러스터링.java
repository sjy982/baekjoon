import java.util.*;
class Solution {
    static ArrayList<String> set1 = new ArrayList<>();
    static ArrayList<String> set2 = new ArrayList<>();
    public int solution(String str1, String str2) {
        int answer = 0;
        make_multiset(set1, str1.toLowerCase());
        make_multiset(set2, str2.toLowerCase());
        if(set1.size() == 0 && set2.size() == 0) return 65536;
        else {
            int intersection = 0;
            for(int i=0; i<set1.size(); i++) {
                for(int j=0; j<set2.size(); j++) {
                    if(set1.get(i).equals(set2.get(j))) {
                        set2.remove(j);
                        intersection += 1;
                        break;
                    }
                }
            }
            answer = (int)((intersection / (double)(set1.size() + set2.size())) * 65536);
        }
        return answer;
    }
    
    static void make_multiset(ArrayList<String> set, String str) {
        for(int i=0; i<str.length() - 1; i++) {
            if((97 <= str.charAt(i) && str.charAt(i) <= 122) && (97 <= str.charAt(i+1) && str.charAt(i+1) <= 122)) {
                set.add(String.valueOf(str.charAt(i)) + String.valueOf(str.charAt(i+1)));
            }
        }
    }
}