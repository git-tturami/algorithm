import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 입력부
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        ArrayList<char[][][]> map = new ArrayList<>();
        ArrayList<mapPoint> Spoint = new ArrayList<>();
        ArrayList<mapPoint> Epoint = new ArrayList<>();

        while(true){
            int l = input.nextInt();
            int r = input.nextInt();
            int c = input.nextInt();

            if(l==0 && r ==0 && c ==0){
                break;
            }

            L.add(l);
            R.add(r);
            C.add(c);

            char [][][] building = new char[l][r][c];

            for(int i = 0 ; i < l ; i ++){
                for(int j = 0 ; j < r; j ++){
                    String strline = input.next();
                    for(int k = 0 ; k < c ; k ++){
                        building[i][j][k] = strline.charAt(k);
                        if(strline.charAt(k)=='S'){
                            mapPoint mp = new mapPoint(i,j,k);
                            Spoint.add(mp);
                        }
                        else if(strline.charAt(k)=='E'){
                            mapPoint mp = new mapPoint(i,j,k);
                            Epoint.add(mp);
                        }
                    }
                }
            }
            map.add(building);
        }

        //BFS
        for(int i = 0 ; i < L.size(); i++){
            System.out.println(BFS(L.get(i), R.get(i), C.get(i), map.get(i), Spoint.get(i), Epoint.get(i)));

        }


    }

    static String BFS(int l, int r, int c, char[][][] building, mapPoint Spoint, mapPoint Epoint){
        int count=0;
        Queue<mapPoint> tree = new LinkedList<mapPoint>();
        tree.offer(Spoint);
        int[][][] visit = new int[l][r][c];

        for(int i = 0 ; i < l; i++){
            for(int j = 0 ; j < r; j ++){
                for(int k = 0 ; k < c; k++){
                    visit[i][j][k]=0;
                }
            }
        }

        while(true) {
            if(tree.isEmpty()){
                return "Trapped!";
            }
            mapPoint polledPoint = tree.poll();
            int x, y, z;
                x = polledPoint.i;
                y = polledPoint.j;
                z = polledPoint.k;

                for (int i = 1; i <= 6; i++) {
                    //좌, 우, 상, 하 , 위, 아래
                    mapPoint point = new mapPoint(-1,-1,-1);
                    if (i == 1) { //좌
                        point = new mapPoint(x, y, z - 1);
                    } else if (i == 2) { //우
                        point = new mapPoint(x, y, z + 1);
                    } else if (i == 3) { //상
                        point = new mapPoint(x, y -1, z );
                    } else if (i == 4) { //하
                        point = new mapPoint(x, y+1, z );
                    } else if (i == 5) { //위
                        point = new mapPoint(x-1, y, z);
                    } else if (i == 6) { //아래
                        point = new mapPoint(x+1, y, z);
                    }

                    if(!point.isEmpty() && point.i!=l && point.j!=r && point.k!=c){
                            if(building[point.i][point.j][point.k]=='.' && visit[point.i][point.j][point.k]==0){
                                point.count = polledPoint.count +1 ;
                                tree.offer(point);
                                visit[point.i][point.j][point.k]=1;
                            }
                            else if(building[point.i][point.j][point.k]=='E'){
                                count = polledPoint.count +1 ;
                                break;
                        }
                    }

                }


            if(count !=0){
                break;
            }
        }

        String time = "Escaped in " + count + " minute(s).";
        return time;
    }

    private static class mapPoint {
        int i;
        int j;
        int k;
        int count=0;
        mapPoint(int i, int j, int k){
            this.i = i;
            this.j = j;
            this.k = k;

        }

        boolean isEmpty(){
            if(i==-1 || j ==-1 || k==-1){
                return true;
            }
            return false;
        }

    }
}
