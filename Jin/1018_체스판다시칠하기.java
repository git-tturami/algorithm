import java.util.Scanner;

public class Main {
    static int M;
    static int N;
    static char[][] map;
    static char[][] tmp;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc =  new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        map = new char[M][N];
        tmp = new char[8][8];

        int min = 99999;

        for(int i=0;i<M;i++) {
            String str = sc.next();
            for(int j=0;j<N;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0;i<M-7;i++) {
            for(int j=0;j<N-7;j++) {
                int change = 0;
                set(i, j);
                if(tmp[0][0] == 'W'){
                    change++;
                }
                tmp[0][0] = 'B';
                int count1 = checkLeft(i,j, change);
                change = 0;
                set(i, j);
                if(tmp[0][0] == 'B'){
                    change++;
                }
                tmp[0][0] = 'W';
                int count2 = checkLeft(i,j, change);
                int count = 0;
                if(count1 < count2) {
                    count = count1;
                }
                else {
                    count = count2;
                }
                if(min >= count) {
                    min = count;
                }
            }
        }
        System.out.println(min);
    }

    public static int checkLeft(int m, int n, int change) {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(i>0 && tmp[i][j] == tmp[i-1][j]) {
                    if(tmp[i-1][j] == 'W') {
                        tmp[i][j] = 'B';
                    }
                    else{
                        tmp[i][j] = 'W';
                    }
                    change++;
                }
                else if(j>0 && tmp[i][j] == tmp[i][j-1]) {
                    if(tmp[i][j-1] == 'W') {
                        tmp[i][j] = 'B';
                    }
                    else{
                        tmp[i][j] = 'W';
                    }
                    change++;
                }
            }
        }
        return change;
    }


    public static void set(int m, int n) {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                tmp[i][j] = map[i+m][j+n];
            }
        }
    }
}