import java.io.*;
import java.util.*;

class Cell {
    int score;
    ArrayList < Cell > nMap;
    Cell(int score, ArrayList < Cell > nMap) {
        this.score = score;
        this.nMap = nMap;
    }
}

class Horse {
    ArrayList < Cell > map;
    boolean[] visited;
    int ind;
    boolean end;
    Horse(ArrayList < Cell > map, boolean[] visited, int ind) {
        this.map = map;
        this.ind = ind;
        this.visited = visited;
        end = false;
    }
}

public class Main {
    static int answer = -1;
    static ArrayList < Cell > map = new ArrayList < > ();
    static ArrayList < Integer > orderList = new ArrayList < >() ;
    static boolean[] visited = new boolean[39];
    static boolean[] nVisited = new boolean[39];
    static boolean visited40 = false;
    public static void main(String args[]) throws IOException {
        makeMap();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            orderList.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList < Horse > horseList = new ArrayList < > ();
        for (int i = 0; i < 4; i++) {
            horseList.add(new Horse(map, visited, 0));
        }
        
        dfs(orderList, horseList, 0, 4, 0);
        System.out.println(answer);
    }

    static void dfs(ArrayList < Integer > oList, ArrayList < Horse > hList, int oCursor, int live, int score) {
        if (live == 0 || oCursor == 10) {
            answer = Math.max(answer, score);
            return;
        }

        for (int i = 0; i <= 3; i++) {
            //말을 선택한다.
            if (hList.get(i).end) {
                continue;
            }

            //살아있는 말이라면 order만큼 이동
            Horse h = hList.get(i);
            Cell curCell = h.map.get(h.ind); //현재 말이 서있는 위치
            ArrayList<Cell> curMap = h.map;
            boolean[] curVisited = h.visited;
            int curInd = h.ind;
            
            if(curCell.nMap != null) {
                //다음에는 교차로로 들어간다.
                h.map = curCell.nMap; //교차로 맵
                h.visited = nVisited; //교차로 방문처리 
                h.ind = -1; //교차로 인덱스
                
            }
            
            int nextInd = h.ind + oList.get(oCursor);
            if(h.map.size() - 1 <= nextInd) {
                //도착칸이나 그 칸을 벗어난다면 
                h.end = true;
                if(curCell.score == 40) {
                    //40에 서있었다면
                    visited40 = false;
                } else {
                    curVisited[curCell.score] = false;
                }
                dfs(oList, hList, oCursor + 1, live - 1, score);
                h.end = false;
                if(curCell.score == 40) {
                    //40에 서있었다면
                    visited40 = true;
                } else {
                    curVisited[curCell.score] = true;
                }
            } else {
                //그외에 칸임. 중요한건 현재 칸은 40일수가 없음.
                Cell nextCell = h.map.get(nextInd);
                if(nextCell.score == 40) {
                    if(!visited40) {
                        curVisited[curCell.score] = false;
                        visited40 = true;
                        h.ind = nextInd;
                        dfs(oList, hList, oCursor + 1, live, score + 40);
                        curVisited[curCell.score] = true;
                        visited40 = false;
                    }
                } else {
                    if(!h.visited[nextCell.score]) {
                        curVisited[curCell.score] = false;
                        h.visited[nextCell.score] = true;
                        h.ind = nextInd;
                        dfs(oList, hList, oCursor + 1, live, score + nextCell.score);
                        curVisited[curCell.score] = true;
                        h.visited[nextCell.score] = false;
                    }
                }
            }
            
            //다시 되돌림.
            h.map = curMap;
            h.visited = curVisited;
            h.ind = curInd;
        }
    }
    

    static void makeMap() {
        map.add(new Cell(0, null)); //시작 칸

        for (int i = 1; i <= 20; i++) {
            map.add(new Cell(i * 2, null));
        }

        map.add(new Cell(0, null)); //도착 칸
        addNewMap(map.get(5));
        addNewMap(map.get(10));
        addNewMap(map.get(15));
    }


    static void addNewMap(Cell cell) {
        cell.nMap = new ArrayList < > ();
        if (cell.score == 10) {
            cell.nMap.add(new Cell(13, null));
            cell.nMap.add(new Cell(16, null));
            cell.nMap.add(new Cell(19, null));
        } else if (cell.score == 20) {
            cell.nMap.add(new Cell(22, null));
            cell.nMap.add(new Cell(24, null));
        } else {
            //30
            cell.nMap.add(new Cell(28, null));
            cell.nMap.add(new Cell(27, null));
            cell.nMap.add(new Cell(26, null));
        }

        cell.nMap.add(new Cell(25, null));
        cell.nMap.add(new Cell(30, null));
        cell.nMap.add(new Cell(35, null));
        cell.nMap.add(new Cell(40, null));
        cell.nMap.add(new Cell(0, null));
    }
}