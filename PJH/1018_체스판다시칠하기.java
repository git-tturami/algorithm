import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int M = input.nextInt();
        int N = input.nextInt();
        int Min = 9999;

        String input_str[] = new String[M];
        char map[][] = new char[M][N];
        for(int i = 0; i < M ; i ++){
            input_str[i] = input.next();
        }

        for(int i = 0 ; i < M ; i ++){
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = input_str[i].charAt(j);
            }
        }

        for(int i = 0 ; i <= M -8; i ++){
            for(int j = 0 ; j <= N-8; j ++){
                int countA = 0;
                int countB = 0;
                for(int k = i ; k < i+8; k ++){
                    for(int z = j ; z < j+8; z++){
                        if((k+z)%2 == 0 && map[k][z] != 'W'){
                            countA++;
                        }
                        else if((k+z)%2 == 1 && map[k][z] != 'B'){
                            countA++;
                        }
                    }
                    if(countA>Min){
                        break;
                    }
                }
                for(int k = i ; k < i+8; k ++){
                    for(int z = j ; z < j+8; z++){
                        if((k+z)%2 == 0 && map[k][z] != 'B'){
                            countB++;
                        }
                        else if((k+z)%2 == 1 && map[k][z] != 'W'){
                            countB++;
                        }
                    }
                    if(countB>Min){
                        break;
                    }
                }

                if(countA >= countB && Min > countB){
                    Min = countB;
                }
                else if(countB > countA && Min > countA){
                    Min = countA;
                }
            }
        }

        System.out.print(Min);
    }
}
