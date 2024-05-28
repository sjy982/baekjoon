import java.io.*;
import java.util.*;

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, minSize;
    static Coordinate fstCn;
    static Coordinate secCn;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Coordinate[] list = new Coordinate[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        binarySearch(list);
        System.out.println(minSize);
        System.out.println(fstCn.x + " " + fstCn.y);
        System.out.println(secCn.x + " " + secCn.y);
    }

    static void binarySearch(Coordinate[] list) {
        int max = 30000;
        int min = 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (isPosible(list, mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
    }

    static boolean isPosible(Coordinate[] list, int size) {
        Coordinate fDown = find(list, 1, new Coordinate(-30001, -30001), size); //아래이면서 가장 왼쪽에 있는 점
        
        for(int i=0; i<=size; i++) {
            Coordinate fCorner = new Coordinate(fDown.x - i, fDown.y);
            Coordinate sLeft = find(list, 0, fCorner, size);
            Coordinate sDown = find(list, 1, fCorner, size);
            
            Coordinate sCorner = combine(sLeft, sDown);
            if(check(fCorner, sCorner, size, list)) {
                fstCn = fCorner;
                secCn = sCorner;
                minSize = size;
                return true;
            }
        }
        
        return false;
    }

    static boolean check(Coordinate fCorner, Coordinate sCorner, int size, Coordinate[] list) {
        for (int i = 0; i < list.length; i++) {
            if (!checkRange(list[i], fCorner, size) && !checkRange(list[i], sCorner, size)) {
                return false;
            }
        }
        return true;
    }

    static Coordinate combine(Coordinate left, Coordinate down) {
        return new Coordinate(left.x, down.y);
    }

    static Coordinate find(Coordinate[] list, int dir, Coordinate cn, int size) {
        Coordinate result = new Coordinate(30001, 30001);
        if (dir == 0) {
            //왼쪽이면서 가장 아래
            for (int i = 0; i < list.length; i++) {
                if (checkRange(list[i], cn, size)) {
                    continue;
                }

                if (result.x > list[i].x || (result.x == list[i].x && result.y > list[i].y)) {
                    result = list[i];
                }
            }
        } else {
            //아래이면서 가장 왼쪽
            for (int i = 0; i < list.length; i++) {
                if (checkRange(list[i], cn, size)) {
                    continue;
                }

                if (result.y > list[i].y || (result.y == list[i].y && result.x > list[i].x)) {
                    result = list[i];
                }
            }
        }
        if (result.x == 30001 && result.y == 30001) {
            //이미 다 포함되어 있다면 겹친다.
            return new Coordinate(cn.x, cn.y);
        }
        return result;
    }

    static boolean checkRange(Coordinate p, Coordinate cn, int size) {
        if (((cn.x <= p.x) && (p.x <= cn.x + size)) && ((cn.y <= p.y) && (p.y <= cn.y + size))) {
            return true;
        }
        return false;
    }
}