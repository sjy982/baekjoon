import java.io.*;
import java.util.*;

class Heap {
    ArrayList<Integer> arr;
    Heap() {
        arr = new ArrayList<>();
        arr.add(-1); //인덱스 1부터 시작
    }
    
    public int get(int index) {
        return arr.get(index);
    }
    
    public int size() {
        return arr.size()-1;
    }
    
    public void insert(int value) {
        arr.add(value);
        int v_ind = this.size();
        while(v_ind != 1) {
            //현재 값이 루트 노드에 있지 않다면 부모 노드와 비교해준다.
            int pv_ind = v_ind/2; //자식 노드의 인덱스 값 / 2 -> 정수형 나눗셈
            int p_value = this.get(pv_ind);
            if(value > p_value) {
                arr.set(pv_ind, value);
                arr.set(v_ind, p_value);
                v_ind = pv_ind;
            } else break;
        }
    } 
    
    public int delete() {
        if(this.size() == 0) return 0;
        int root_value = this.get(1);
        int p_value = this.get(this.size()); //정렬 대상
        int p_ind = 1;
        arr.set(1, p_value);
        arr.remove(this.size());
        int lc_ind = p_ind * 2;
        while(lc_ind<=this.size()) {
            int rc_ind = lc_ind + 1;
            if(rc_ind<=this.size()) {
                //오른쪽 자식이 존재한다면
                if(this.get(lc_ind) <= this.get(rc_ind)) {
                    //오른쪽 자식의 우선순위가 더 높다면
                    int c_value = this.get(rc_ind);
                    if(p_value < c_value) {
                        arr.set(p_ind, c_value);
                        arr.set(rc_ind, p_value);
                        p_ind = rc_ind;
                    } else break;
                } else {
                    //왼쪽 자식의 우선순위가 더 높다면
                    int c_value = this.get(lc_ind);
                    if(p_value < c_value) {
                        arr.set(p_ind, c_value);
                        arr.set(lc_ind, p_value);
                        p_ind = lc_ind;
                    } else break;
                }
            } else {
                //왼쪽만
                int c_value = this.get(lc_ind);
                if(p_value < c_value) {
                    arr.set(p_ind, c_value);
                    arr.set(lc_ind, p_value);
                    p_ind = lc_ind;
                } else break;
            }
            lc_ind = p_ind * 2;
        }
        return root_value;
    }
}

public class Main {
    static int N;
    static Heap heap = new Heap();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int op = Integer.parseInt(br.readLine());
            if(op==0) sb.append(heap.delete() + "\n");
            else heap.insert(op);
        }
        System.out.println(sb.toString().trim());
    }
}