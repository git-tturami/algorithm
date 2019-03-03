import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int[][] Map;
    static int M,N,K;

    static class Sqaurepoint{
        int sx;
        int sy;
        int ex;
        int ey;
    }
    static Sqaurepoint[] p;

    static class point{
        int x;
        int y;
        public point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static Stack<point> stack;

    static ArrayList<Integer> areaList;
    static int area;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();

        p = new Sqaurepoint[K];

        for(int i = 0;i<K;i++){
            p[i] = new Sqaurepoint();
            p[i].sx = scanner.nextInt();
            p[i].sy = scanner.nextInt();
            p[i].ex = scanner.nextInt();
            p[i].ey = scanner.nextInt();
        }

        Map = new int[M][N];

        for(int i = 0;i<M;i++){
            for(int j = 0;j<N;j++){
                Map[i][j] = -1;
            }
        }

        for(int i = 0;i<K;i++){
            for(int j = p[i].sx;j<p[i].ex;j++){
                for(int k = p[i].sy;k<p[i].ey;k++){
                    Map[j][k] = 0;
                }
            }
        }

        stack = new Stack<point>();
        areaList = new ArrayList<Integer>();

        int num = numbering();

        System.out.println(num);
        for(int i=0;i<areaList.size();i++){
            System.out.print(areaList.get(i) + " ");
        }
    }

    public static void findSquare(int x, int y, int count) {
        if(Map[x][y]!=count) area++;
        Map[x][y] = count;
        if (y+1 < N && Map[x][y + 1] == -1) stack.push(new point(x, y + 1)); // 위
        if (x+1 < M && Map[x + 1][y] == -1) stack.push(new point(x + 1, y)); // 오른쪽
        if (y >= 1 && Map[x][y - 1] == -1) stack.push(new point(x, y - 1));  // 아래
        if (x >= 1 && Map[x - 1][y] == -1) stack.push(new point(x - 1, y));  // 왼쪽

        if (!stack.empty()) {
            point np = stack.pop(); // 스택의 가장 마지막것이 나옴
            findSquare(np.x, np.y, count);
        }
    }

    public static int numbering(){
        int count = 1;
        area = 0;
        for(int i = 0;i<M;i++){
            for(int j=0;j<N;j++){
                if(Map[i][j] == -1){
                    findSquare(i,j,count);
                    count++;
                    areaList.add(area);
                    area = 0;
                }
            }
        }

        Collections.sort(areaList);
        return count-1;
    }
}
