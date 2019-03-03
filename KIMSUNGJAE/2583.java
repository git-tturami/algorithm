import java.util.*;

public class problem2583 {
    static int M;
    static int N;
    static int K;
    static boolean[][] map;
    static int x1, y1, x2, y2;

    static int area;
    static List areas = new ArrayList();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        M = scan.nextInt();
        N = scan.nextInt();
        K = scan.nextInt();

        map = new boolean[N][M];

        for(int i = 0 ; i < K; i ++) {
            x1 = scan.nextInt();
            y1 = scan.nextInt();
            x2 = scan.nextInt();
            y2 = scan.nextInt();

            for(int j = x1 ; j < x2 ; j++){   // 1 2
                for(int z = y1 ; z < y2 ; z++){ // 1 5
                    map[j][z] = true;
                }
            }
        }

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++) {
                if(map[i][j] == false) {
                    area = 0;
                    area = Search(i, j);
                    areas.add(area);
                }
            }
        }

        Collections.sort(areas);
        System.out.println(areas.size());
        for(int i = 0 ; i < areas.size() ; i++){
            System.out.print(areas.get(i) + " ");
        }

    }

    public static int Search(int x, int y) {
        if(map[x][y] == true) {
            return 0;
        }
        map[x][y] = true; // visited
        area++;

            if(x + 1 < N) {
                if(map[x+1][y] != true) {
                    Search(x + 1, y);
                }
            }

            if(x - 1 >= 0) {
                if(map[x-1][y] != true) {
                    Search(x - 1, y);
                }
            }

            if(y + 1 < M) {
                if(map[x][y+1] != true) {
                    Search(x, y + 1);
                }
            }

            if(y - 1 >= 0) {
                if(map[x][y-1] != true) {
                    Search(x, y - 1);
                }
            }

        return area;
    }
}

