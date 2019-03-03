import java.util.Scanner;

public class Main {

	static int[][] point;
	static Dragon[] dragon;
	static int N;
	static int number;

	static class Dragon {
		int x;
		int y;
		int d;
		int g;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		point = new int[101][101];
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				point[i][j] = 0;
			}
		}

		N = scan.nextInt();
		dragon = new Dragon[N];

		for (int i = 0; i < N; i++) {
			dragon[i] = new Dragon();
			dragon[i].x = scan.nextInt();
			dragon[i].y = scan.nextInt();
			dragon[i].d = scan.nextInt();
			dragon[i].g = scan.nextInt();
		}

		for (int i = 0; i < N; i++) {
			point[dragon[i].x][dragon[i].y] = 1;
			numDragons(i);
		}

		int resultnum = numResult();

		System.out.println(resultnum);
	}

	static void numDragons(int i) {
		int start = 0;
		int end = 0;
		int x = dragon[i].x;
		int y = dragon[i].y;

		int[] result = new int[(int) Math.pow(2, dragon[i].g)];
		for (int j = 0; j < result.length; j++) {
			result[j] = -1;
		}

		result[0] = dragon[i].d;

		for (int j = 0; j < dragon[i].g; j++) {
			int[] tmp = new int[(int) Math.pow(2, j + 1)];

			for (int k = 0; k < tmp.length; k++) {
				tmp[k] = result[k];
			}
			x = dragon[i].x;
			y = dragon[i].y;

			start = 0;
			end = tmp.length - 1;

			while (tmp[start] != -1) {
				switch (tmp[start]) {
				case 0:
					x += 1;
					result[end - start] = 1;
					start++;
					break;

				case 1:
					y -= 1;
					result[end - start] = 2;
					start++;
					break;

				case 2:
					x -= 1;
					result[end - start] = 3;
					start++;
					break;

				case 3:
					y += 1;
					result[end - start] = 0;
					start++;
					break;
				}
			}
		}
		
		x = dragon[i].x;
		y = dragon[i].y;
		
		for(int k=0;k<result.length;k++) {
			switch (result[k]) {
			case 0:
				x += 1;
				if(x<=100 && y<=100) {
					point[x][y] = 1;
				}
				break;

			case 1:
				y -= 1;
				if(x<=100 && y<=100) {
					point[x][y] = 1;
				}
				break;

			case 2:
				x -= 1;
				if(x<=100 && y<=100) {
					point[x][y] = 1;
				}
				break;

			case 3:
				y += 1;
				if(x<=100 && y<=100) {
					point[x][y] = 1;
				}
				break;
			}
		}
		

	}

	static int numResult() {
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (point[i][j] == 1 && point[i + 1][j] == 1 && point[i][j + 1] == 1 && point[i + 1][j + 1] == 1) {
					count++;
				}
			}
		}

		return count;

	}
}
