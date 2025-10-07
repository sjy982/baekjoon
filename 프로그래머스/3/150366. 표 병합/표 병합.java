import java.util.*;

class Cell {
    String value;
    ArrayList<Cell> mergeList;
    Cell(String value) {
        this.value = value;
        mergeList = new ArrayList<>();
        mergeList.add(this);
    }
    
    void init() {
        this.value = null;
        mergeList = new ArrayList<>();
        mergeList.add(this);
    }
}

class Solution {
    public String[] solution(String[] commands) {
        Cell[][] table = new Cell[51][51];
        for(int i=1; i<=50; i++) {
            for(int j=1; j<=50; j++) {
                table[i][j] = new Cell(null);
            }
        }
        ArrayList<String> answerList = new ArrayList<>();
        for(int i=0; i<commands.length; i++) {
            String[] split = commands[i].split(" ");
            
            if(split[0].equals("UPDATE")) {
                if(split.length == 4) {
                    update(table, Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3]);
                } else {
                    update2(table, split[1], split[2]);
                }
            } else if(split[0].equals("MERGE")) {
                merge(table, Integer.parseInt(split[1]), Integer.parseInt(split[2])
                            , Integer.parseInt(split[3]), Integer.parseInt(split[4]));
            } else if(split[0].equals("UNMERGE")) {
                unmerge(table, Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            } else if(split[0].equals("PRINT")) {
                answerList.add(print(table, Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }
        }
        
        String[] answer = new String[answerList.size()];
        for(int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    static void unmerge(Cell[][] table, int r, int c) {
        String value = table[r][c].value;
        ArrayList<Cell> std = table[r][c].mergeList;
        for(int i=0; i<std.size(); i++) {
            std.get(i).init();
        }
        table[r][c].value  = value;
    }
    
    static String print(Cell[][] table, int r, int c) {
        if(table[r][c].value == null) {
            return "EMPTY";
        }
        return table[r][c].value;
    }
    
    static void merge(Cell[][] table, int r1, int c1, int r2, int c2) {
        if(table[r1][c1].mergeList == table[r2][c2].mergeList) {
            return;
        }
        //셀 병합
        ArrayList<Cell> std;
        ArrayList<Cell> sub;
        if(table[r1][c1].mergeList.size() >= table[r2][c2].mergeList.size()) {
            std = table[r1][c1].mergeList;
            sub = table[r2][c2].mergeList;
        } else {
            std = table[r2][c2].mergeList;
            sub = table[r1][c1].mergeList;
        }
        for(int i=0; i<sub.size(); i++) {
            std.add(sub.get(i));
            sub.get(i).mergeList = std;
        }
        
        //대입값 찾기
        if(table[r1][c1].value == null && table[r2][c2].value == null) {
            return;
        } 
        
        String value;
        if(table[r1][c1].value == null && table[r2][c2].value != null) {
            value = table[r2][c2].value;
        } else {
            value = table[r1][c1].value;
        }
        
        for(int i=0; i<std.size(); i++) {
            std.get(i).value = value;
        }
    }
    
    static void update2(Cell[][] table, String value1, String value2) {
        for(int i=1; i<=50; i++) {
            for(int j=1; j<=50; j++) {
                if(table[i][j].value != null && table[i][j].value.equals(value1)) {
                    update(table, i, j, value2);
                }
            }
        }
    }
    
    static void update(Cell[][] table, int r, int c, String value) {
        if(table[r][c].value != null && table[r][c].value.equals(value)) {
            return;
        }
        
         for (int i=0; i<table[r][c].mergeList.size(); i++) {
            table[r][c].mergeList.get(i).value = value;
        }
        return;
    }
}