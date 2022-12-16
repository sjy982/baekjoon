import java.io.*;
import java.util.*;

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static ArrayList<Coordinate> coordinate = new ArrayList<>();
    static int min_dist = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          coordinate.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
      }
      Collections.sort(coordinate, (a,b) -> a.x - b.x);
      find_closesPair(0, coordinate.size() - 1);
      System.out.println(min_dist);
    }
    
    static int cal_dist(Coordinate a, Coordinate b) {
        return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
    }
    
    static void find_closesPair(int s, int e) {
        if(e - s + 1 <= 3) {
            //점이 3개 이하라면 그 중 짧은 선을 찾는다.
            for(int i=s; i<=e; i++) {
                for(int j= i+1; j<=e; j++) {
                    min_dist = Math.min(min_dist, cal_dist(coordinate.get(i), coordinate.get(j)));
                }
            }
            return;
        }
        //분할
        int mid = (s + e) / 2;
        find_closesPair(s, mid);
        find_closesPair(mid+1, e);
        
        //병합
        ArrayList<Coordinate> posi_cord =  new ArrayList<>();
        for(int i=s; i<=e; i++) {
            int xd = (coordinate.get(mid).x - coordinate.get(i).x) * (coordinate.get(mid).x - coordinate.get(i).x);
            if(xd < min_dist) posi_cord.add(coordinate.get(i));
        }
        
        //가능성있는 좌표들을 y좌표 기준으로 오름차순 정렬.
        Collections.sort(posi_cord, (a,b) -> a.y - b.y);
        
        //현재 y좌표에서 y좌표가 같거나 큰 좌표들을 check한다. 중복을 피하기 위해서
        for(int i=0; i<posi_cord.size()-1; i++) {
            for(int j=i+1; j<posi_cord.size(); j++) {
                int yd = (posi_cord.get(i).y - posi_cord.get(j).y) * (posi_cord.get(i).y - posi_cord.get(j).y);
                
                //yd값이 min_dist보다 크거나 같으면 break; 이후 값은 min_dist보다 무조건 큼
                if(yd >= min_dist) break;
                
                //yd값이 min_dist보다 작다면 두 점의 거리를 구해서 min_dist와 비교
                min_dist = Math.min(min_dist, cal_dist(posi_cord.get(i), posi_cord.get(j)));
            }
        }
    }
}