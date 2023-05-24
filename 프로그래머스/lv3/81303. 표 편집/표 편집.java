import java.util.*;
class Node {
    static Node cursor;
    int name;
    Node prev, next;
    Node(Node prev, int name, Node next) {
        this.prev = prev;
        this.name = name;
        this.next = next;
    }
}
class Solution {
    static Node table;
    static Stack<Node> delete_buffer = new Stack<>();
    public String solution(int n, int k, String[] cmd) {
        for(int i=0; i<n; i++) {
            if(i==0) {
                table = new Node(null, 0, null);
            } else {
                table.next = new Node(table, i, null);
                table = table.next;
            }
            if(i == k) table.cursor = table;
        }
        for(int i=0; i<cmd.length; i++) {
            String[] cmd_split = cmd[i].split(" ");
            if(cmd_split[0].equals("U")) {
                int move_cnt = Integer.parseInt(cmd_split[1]);
                for(int j=0; j<move_cnt; j++) table.cursor = table.cursor.prev; //up -> left
            } else if(cmd_split[0].equals("D")) {
                int move_cnt = Integer.parseInt(cmd_split[1]);
                for(int j=0; j<move_cnt; j++) table.cursor = table.cursor.next; //down -> right
            } else if(cmd_split[0].equals("C")) {
                delete_buffer.push(table.cursor);
                if(table.cursor.next == null) {
                    table.cursor.prev.next = null;
                    table.cursor = table.cursor.prev; //위에 행을 가리킴
                } else {
                    if(table.cursor.prev == null) {
                        table.cursor.next.prev = null;
                    } else {
                        table.cursor.prev.next = table.cursor.next;
                        table.cursor.next.prev = table.cursor.prev;
                    }
                    table.cursor = table.cursor.next; // 밑에 행을 가리킴
                }
            } else if(cmd_split[0].equals("Z")) {
                Node restore = delete_buffer.pop();
                if(restore.next == null) {
                    //복구하는 행이 마지막인 행인 경우
                    restore.prev.next = restore;
                } else if(restore.prev == null) {
                    //복구하는 행이 첫 번째 행인 경우
                    restore.next.prev = restore;
                } else {
                    restore.prev.next = restore;
                    restore.next.prev = restore;
                }
            }
        }
        //첫 노드 가리키기
        while(table.cursor.prev != null) table.cursor = table.cursor.prev;
        boolean[] result_table = new boolean[n];
        while(table.cursor != null) {
            result_table[table.cursor.name] = true;
            table.cursor = table.cursor.next;
        }
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<result_table.length; i++) {
            if(result_table[i]) answer.append('O');
            else answer.append('X');
        }
        return answer.toString();
    }
}