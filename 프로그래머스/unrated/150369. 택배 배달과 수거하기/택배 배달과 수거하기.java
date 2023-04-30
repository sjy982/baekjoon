import java.util.*;
class Node {
    int h, c;
    Node(int h, int c) {
        this.h = h;
        this.c = c;
    }
}
class Solution {
    static ArrayList < Node > d_list = new ArrayList < > ();
    static ArrayList < Node > p_list = new ArrayList < > ();
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) d_list.add(new Node(i + 1, deliveries[i]));
            if (pickups[i] != 0) p_list.add(new Node(i + 1, pickups[i]));
        }
        while (d_list.size() != 0 || p_list.size() != 0) {
            int house = 0;
            if(d_list.size() == 0) house = p_list.get(p_list.size()-1).h;
            else if(p_list.size() == 0) house = d_list.get(d_list.size()-1).h;
            else house = Math.max(d_list.get(d_list.size()-1).h, p_list.get(p_list.size()-1).h);
            delivery_pickup(d_list, cap);
            delivery_pickup(p_list, cap);
            answer += house * 2;
        }
        return answer;
    }
    static void delivery_pickup(ArrayList < Node > list, int box) {
        while (box != 0 && list.size() != 0) {
            if (list.get(list.size() - 1).c > box) {
                list.get(list.size() - 1).c -= box;
                box = 0;
            } else {
                box -= list.get(list.size() - 1).c;
                list.remove(list.size() - 1);
            }
        }
    }
}