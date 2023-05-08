import java.util.*;

class Solution {
    static String[] table = new String[2501];
    static int[] group = new int[2501];
    static ArrayList<String> answer_list = new ArrayList<>();
    public String[] solution(String[] commands) {
        String[] answer = {};
        for(int i=1; i<=2500; i++) group[i] = i;
        for(int i=0; i<commands.length; i++) {
            String[] input = commands[i].split(" ");
            if(input[0].equals("UPDATE")){
                if(input.length == 4) {
                    int index = conversion(Integer.parseInt(input[2]), Integer.parseInt(input[1]));
                    update(index, input[3]);
                } else {
                    update(input[1], input[2]);
                }
            } else if(input[0].equals("MERGE")) {
                int index1 = conversion(Integer.parseInt(input[2]), Integer.parseInt(input[1]));
                int index2 = conversion(Integer.parseInt(input[4]), Integer.parseInt(input[3]));
                merge(index1, index2);
                                    
            } else if(input[0].equals("UNMERGE")) {
                int index = conversion(Integer.parseInt(input[2]), Integer.parseInt(input[1]));
                unmerge(index);
            } else if(input[0].equals("PRINT")) {
                int index = conversion(Integer.parseInt(input[2]), Integer.parseInt(input[1]));
                answer_list.add(print(index));
            }
        }
        answer = new String[answer_list.size()];
        for(int i=0; i<answer_list.size(); i++) answer[i] = answer_list.get(i);
        return answer;
    }
    static void update(int index, String value) {
        int root = find(index);
        table[root] = value;
    }
    
    static void update(String value1, String value2) {
        for(int i=1; i<table.length; i++) {
            if(table[i] != null && table[i].equals(value1)) table[i] = value2;
        }
    }
    static void merge(int index1, int index2) {
        int root = find(index1);
        int root2 = find(index2);
        if(root == root2) return; //같은 그룹이면 return
        group[root2] = root;
        if(table[root] == null && table[root2] != null) table[root] = table[root2];
        table[root2] = null;
    }
    static void unmerge(int index) {
        int root = find(index);
        String value = table[root];
        table[root] = null;
        table[index] = value;
        ArrayList<Integer> delete_list = new ArrayList<>();
        for(int i=1; i<group.length; i++) if(find(i) == root) delete_list.add(i);
        for(int i=0; i<delete_list.size(); i++) group[delete_list.get(i)] = delete_list.get(i);
    }
    static String print(int index) {
        int root = find(index);
        if(table[root] == null) return "EMPTY";
        return table[root];
    }
    
    static int find(int n) {
        if(group[n] == n) return n;
        return group[n] = find(group[n]);
    }
    
    static int conversion(int x, int y) {
        return (y-1) * 50 + x;
    }
}