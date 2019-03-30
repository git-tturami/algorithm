import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] arr = new int[N][M];
        for(int i = 0 ; i<N;i++)
        {
            for(int j = 0; j<M;j++)
            {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.print(solution(arr,N,M));
    }
    public static int solution(int arr[][],int N,int M) {
        int answer = 0;
        int[][] temp = new int[N][M];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0 ;i<N;i++)
        {
            for(int j = 0;j<M;j++)
            {
                if(arr[i][j]==0)
                {
                    arrayList.add(i*10+j);
                }
            }
        }
        for(int i = 0 ; i<arrayList.size()-2;i++)
        {
            for(int j = i+1; j<arrayList.size()-1;j++)
            {
                for(int k = j+1; k<arrayList.size();k++)
                {
                    for(int a = 0;a<N;a++)
                    {
                        for(int b=0;b<M;b++)
                            temp[a][b]=arr[a][b];
                    }
                    temp[arrayList.get(i)/10][arrayList.get(i)%10] = 1;
                    temp[arrayList.get(j)/10][arrayList.get(j)%10] = 1;
                    temp[arrayList.get(k)/10][arrayList.get(k)%10] = 1;

                    if(virus(temp,N,M)>answer) {
                        answer = virus(temp, N, M);
                    }
                }
            }
        }
        return answer;
    }
    public static int virus(int arr[][],int N,int M)
    {
        int count = 0 ;
        for(int k = 0 ;k<N*M/2;k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 2) {
                        if (i > 0){
                            if(arr[i - 1][j] == 0) {
                                arr[i - 1][j] = 2;
                            }
                        }
                        if (i < N - 1){
                            if(arr[i + 1][j] == 0) {
                                arr[i + 1][j] = 2;
                            }
                        }
                        if (j > 0) {
                            if (arr[i][j - 1] == 0) {
                                arr[i][j - 1] = 2;
                            }
                        }
                        if (j < M - 1) {
                            if (arr[i][j + 1] == 0) {
                                arr[i][j + 1] = 2;
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0 ; i<N;i++)
        {
            for(int j = 0 ;j <M; j++)
            {
                if(arr[i][j]==0)
                    count++;
            }
        }
        return count;
    }
}
