import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int H = sc.nextInt();
        int M = sc.nextInt();
        int answer = -1;
        ArrayList<Integer> list= new ArrayList<>();
        int[][] ladder = new int[N][M];
        for(int i = 0;i<N;i++)
        {
            for(int j = 0; j<M;j++)
                ladder[i][j] = 0;
        }
        if(H>0)
        {
            for(int i = 0 ;i<H;i++)
            {
                int pointY = sc.nextInt();
                int pointX = sc.nextInt();
                ladder[pointX-1][pointY-1] = 1;
                ladder[pointX][pointY-1] = -1;
            }
        }
        if(role(N,M,ladder)==true)
            System.out.print(0);
        else {
            if (operation1(N, M, ladder) == 1)
                System.out.println("1");
            else {
                if (operation2(N, M, ladder) == 2)
                    System.out.println("2");
                else {
                    if (operation3(N, M, ladder) == 3)
                        System.out.println("3");
                    else
                        System.out.print("-1");
                }
            }
        }
    }
    public static boolean role(int N,int M,int ladder[][])
    {
        int flag = 0;
        int pointY = 0;
        int pointX = 0;
        for(int i =0;i<N;i++)
        {
            pointX = i;
            for(int j = 0;j<M;j++)
            {
                if(ladder[pointX][j]==1)
                    pointX++;
                else if(ladder[pointX][j]==-1)
                    pointX--;
            }
            if(pointX!=i)
                flag = 1;
        }
        if(flag==0)
            return true;
        else
            return false;
    }
    public static int operation1(int N,int M, int ladder[][])
    {
        for(int i = 0;i<N;i++)
        {
            for(int j = 0; j<M;j++) {
                if(ladder[i][j] ==0 &&i<N-1)
                {
                    if(ladder[i+1][j]==0) {
                        ladder[i][j] = 1;
                        ladder[i + 1][j] = -1;
                        if(role(N,M,ladder)==true)
                        {
                            return 1;
                        }
                        ladder[i][j] = 0;
                        ladder[i + 1][j] = 0;
                    }
                }
            }
        }
        return -1;
    }
    public static int operation2(int N,int M, int ladder[][])
    {
        for(int i = 0;i<N;i++)
        {
            for(int j = 0; j<M;j++) {
                if(ladder[i][j] ==0 &&i<N-1)
                {
                    if(ladder[i+1][j]==0) {
                        ladder[i][j] = 1;
                        ladder[i + 1][j] = -1;
                        for(int k = 0;k<N;k++)
                        {
                            for(int l = 0;l<M;l++) {
                                if(ladder[k][l]==0 &&k<N-1) {
                                    if (ladder[k + 1][l] == 0) {
                                        ladder[k][l] = 1;
                                        ladder[k + 1][l] = -1;
                                        /*for (int b = 0; b < M; b++) {
                                            for (int a = 0; a < N; a++)
                                                System.out.print(ladder[a][b] + " ");
                                            System.out.println();
                                        }*/
                                        if (role(N, M, ladder) == true) {
                                            return 2;
                                        }
                                        ladder[k][l] = 0;
                                        ladder[k + 1][l] = 0;
                                    }
                                }
                            }
                        }

                        ladder[i][j] = 0;
                        ladder[i + 1][j] = 0;
                    }
                }
            }
        }
        return -1;
    }
    public static int operation3(int N,int M, int ladder[][])
    {
        for(int i = 0;i<N;i++)
        {
            for(int j = 0; j<M;j++) {
                if(ladder[i][j] ==0 &&i<N-1)
                {
                    if(ladder[i+1][j]==0) {
                        ladder[i][j] = 1;
                        ladder[i + 1][j] = -1;
                        for(int k = 0;k<N;k++)
                        {
                            for(int l = 0;l<M;l++) {
                                if(ladder[k][l]==0 &&k<N-1) {
                                    if (ladder[k + 1][l] == 0) {
                                        ladder[k][l] = 1;
                                        ladder[k + 1][l] = -1;
                                        for (int m = 0; m < N; m++) {
                                            for (int n = 0; n < M; n++) {
                                                if (ladder[m][n] == 0 && m < N - 1) {
                                                    if (ladder[m + 1][n] == 0) {
                                                        ladder[m][n] = 1;
                                                        ladder[m + 1][n] = -1;
                                                        if (role(N, M, ladder) == true) {
                                                            return 3;
                                                        }
                                                        ladder[m][n] = 0;
                                                        ladder[m + 1][n] = 0;
                                                    }
                                                }
                                            }
                                        }
                                        ladder[k][l] = 0;
                                        ladder[k + 1][l] = 0;
                                    }
                                }
                            }
                        }
                        ladder[i][j] = 0;
                        ladder[i + 1][j] = 0;
                    }
                }
            }
        }
        return -1;
    }
}
