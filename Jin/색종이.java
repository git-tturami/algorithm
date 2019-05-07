import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static class point {
		int x;
		int y;
		
		point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static int[][] buf;
	static int onenum;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		map = new int[10][10];
		buf = new int[10][10];

		Scanner in = new Scanner(System.in);
		onenum = 0;
		
		for(int i=0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				buf[i][j] = in.nextInt();
				if(buf[i][j] == 1) onenum++;
			}
		}
		
		for(int i=0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				map[i][j] = buf[i][j];
			}
		}
		in.close();
		
		int fnum=5, snum=5, tnum=5, fonum = 5, finum = 5;
		int result = 0;
		
		while(onenum > 0) {
			int count = findArea();
			
			if(count == 1) fnum--;
			else if(count == 2) snum--;
			else if(count == 3) tnum--;
			else if(count == 4) fonum--;
			else if(count == 5) finum--;
			
			if(fnum<0||snum<0||tnum<0||fonum<0||finum<0) {
				result = -1;
				break;
			}
			onenum = onenum - (count * count);
			result++;
		}
		
		System.out.println(result);
		
		
	}


	public static int findArea() {
		int max = 0;
		int num = 0;
		
		point p = new point(0,0);
		
		for(int i = 0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(map[i][j] == 1) {
					num = check(i,j);
					if(num > 5) {
						num = (num+1)/2;
					}
					if(max <= num) {
						max = num;
						p.x = i;
						p.y = j;
					}
				}
			}
		}
		
		
		for(int i=p.x;i<p.x+max;i++) {
			for(int j=p.y;j<p.y+max;j++) {
				map[i][j] = 0;
			}
		}
		
		return max;
	}
	
	public static int check(int i, int j) {
		int width = 0;
		int height = 0;
		int num = 0;
		int tmp = 0;
		
		for (int a=i;a<10;a++) {
			if(map[a][j] == 1) {
				width++;
			}
			else {
				break;
			}
		}
		
		for (int a=j;a<10;a++) {
			if(map[i][a] == 1) {
				height++;
			}
			else {
				break;
			}
		}
		
		if(width < height) num = width;
		else num=height;
		
		for(int a = i;a<i+num;a++) {
			for (int b=j;b<j+num;b++) {
				if(map[a][b] == 0) {
					tmp = b-j;
					break;
				}
			}
		}
		
		if(tmp != 0) {
			num = tmp;
		}
	
		return num;
	}
}
