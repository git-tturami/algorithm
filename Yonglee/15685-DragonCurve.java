package DragonCurve;

import java.util.Scanner;

public class DragonCurve {

    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};
    // 조건 순서에 맞게 dx,dy를 만들어준다

    public static void main(String[] args){

        int n =0;
        int[][] map = new int[102][102];

        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        for(int i =0; i< n; i++){
            int curve_size = 0; // 커브 사이즈 초기화
            int[] curve = new int[1024];

            int x,y,d,g;
            x = scan.nextInt();
            y = scan.nextInt();
            d = scan.nextInt();
            g = scan.nextInt();
            //x,y,세대 수 입력을 받는다.

            curve[curve_size++] = d; //커브 초기값은 입력받은 d
            map[y][x] = 1; // 해당 위치에 라인을 그려준다

            for(int j= 0; j<g; j++){ // 세대수 만큼 반복을 해준다
                for(int k = curve_size-1;k>=0;k--){ //역순으로 돌면서 드래곤 커브를 90씩 회전시켜준다
                    curve[curve_size++] = (curve[k]+1)%4; // 기존에서 한칸을 더한게 90도 회전, 크기가 넘을경우 %4
                }
            }

            for (int j = 0 ; j<curve_size ; j++){ // 커브를 모두 순회하면서 x,y,에 해당 방향대로 값을 저장해주고 map 에 그려준다
                y += dy[curve[j]];
                x += dx[curve[j]];
                if(y< 0 || y>= 101 || x<0 || x>101){
                    continue;
                }
                map[y][x] = 1;
            }
        }

        int result =  0;

        for(int y= 0; y<100; y++){
            for(int x = 0; x<100; x++){ //조건에 맞는 사각형을 찾아서 일치할 경우 result의 값을 더해준다.
                if(map[y][x] == 1 && map[y][x+1] == 1 && map[y+1][x] == 1 && map[y+1][x+1] == 1){
                        result++;
                }
            }
        }
        System.out.println(result);
    }
}
