import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class point{
        int tL;
        int tR;
        int tC;
        int count;

        point(int l, int r, int c, int num){
            tL = l;
            tR = r;
            tC = c;
            count = num;
        }
    }
    static char[][][] map;
    static Queue<point> queue;
    static int[][][] visit;
    static int L;
    static int R;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            L = sc.nextInt();
            if(L == 0){
                break;
            }
            R = sc.nextInt();
            C = sc.nextInt();

            map = new char[L][R][C];
            visit = new int[L][R][C];

            int startL = 0;
            int startR = 0;
            int startC = 0;
            int endL = 0;
            int endR = 0;
            int endC = 0;

            for(int i=0;i<L;i++){
                for(int j=0;j<R;j++){
                    String str = sc.next();
                    for(int k=0;k<C;k++){
                        map[i][j][k] = str.charAt(k);
                        visit[i][j][k] = 0;
                        if(str.charAt(k) == 'S'){
                            startL = i;
                            startR = j;
                            startC = k;
                        }
                        else if(str.charAt(k) == 'E') {
                            endL = i;
                            endR = j;
                            endC = k;
                        }
                    }
                }
            }

            queue = new LinkedList<>();
            int count = bfs(startL, startR, startC, 0, endL, endR, endC);
            if(count != 0){
                System.out.println("Escaped in "+count+" minute(s).");
            }
            else{
                System.out.println("Trapped!");
            }
        }

    }

    public static int bfs(int i,int j,int k, int count, int endL, int endR, int endC){
        queue.add(new point(i,j,k,count));
        visit[i][j][k] = 1;
        point result = new point(0,0,0,0);

        while(!queue.isEmpty()){
            point p = queue.poll();
            result = p;
            if(map[p.tL][p.tR][p.tC] == 'E'){
                break;
            }
            else{
                i = p.tL;
                j = p.tR;
                k = p.tC;

                if(i>0 && visit[i-1][j][k] == 0 && (map[i-1][j][k] == '.' || map[i-1][j][k] == 'E')){ // 위
                    queue.add(new point(i-1, j, k, p.count+1));
                    visit[i-1][j][k] = 1;
                }
                if(i<L-1 && visit[i+1][j][k] == 0 && (map[i+1][j][k] == '.' || map[i+1][j][k] == 'E')){ // 아래
                    queue.add(new point(i+1, j, k, p.count+1));
                    visit[i+1][j][k] = 1;
                }
                if(j>0 && visit[i][j-1][k] == 0 && (map[i][j-1][k] == '.' || map[i][j-1][k] == 'E')){ // 북
                    queue.add(new point(i, j-1, k, p.count+1));
                    visit[i][j-1][k] = 1;
                }
                if(j<R-1 && visit[i][j+1][k] == 0 && (map[i][j+1][k] == '.' || map[i][j+1][k] == 'E')){ // 남
                    queue.add(new point(i, j+1, k, p.count+1));
                    visit[i][j+1][k] = 1;
                }
                if(k>0 && visit[i][j][k-1] == 0 && (map[i][j][k-1] == '.' || map[i][j][k-1] == 'E')){ // 서
                    queue.add(new point(i, j, k-1, p.count+1));
                    visit[i][j][k-1] = 1;
                }
                if(k<C-1 && visit[i][j][k+1] == 0 &&(map[i][j][k+1] == '.' || map[i][j][k+1] == 'E')){ // 동
                    queue.add(new point(i, j, k+1, p.count+1));
                    visit[i][j][k+1] = 1;
                }
            }
        }

        if(result.tL == endL && result.tR == endR && result.tC == endC){
            return result.count;
        }
        return 0;
    }
}
