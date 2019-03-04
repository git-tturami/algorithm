import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int WALL = 6;
	private static final int MAX = 4;
	
	public static int[][] office;
	public static int n, m;
	public static int min = Integer.MAX_VALUE;

	public static class CCTV {

		int x, y;
		int type;
		boolean[] directionList;

		public CCTV(int x, int y, int type) {

			this.x = x;
			this.y = y;
			this.type = type;
			this.directionList = new boolean[MAX];
			this.initialize();
		}

		public void initialize() {

			if (this.type == 1) {

				directionList[UP] = true;
			} else if (this.type == 2) {

				directionList[LEFT] = true;
				directionList[RIGHT] = true;
			} else if (this.type == 3) {

				directionList[UP] = true;
				directionList[RIGHT] = true;
			} else if (this.type == 4) {

				directionList[LEFT] = true;
				directionList[UP] = true;
				directionList[RIGHT] = true;
			} else {

				for (int i = 0; i < this.directionList.length; i++)
					directionList[i] = true;
			}
		}

		public void rotate() {

			boolean temp = this.directionList[LEFT];
			this.directionList[LEFT] = this.directionList[DOWN];
			this.directionList[DOWN] = this.directionList[RIGHT];
			this.directionList[RIGHT] = this.directionList[UP];
			this.directionList[UP] = temp;
		}

		public boolean[][] inspection(boolean[][] isInspected) {

			boolean[][] result = new boolean[n][m];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					result[i][j] = isInspected[i][j];

			if (this.directionList[0]) {
				for (int i = x; i >= 0; i--) {
					if (office[i][y] == WALL)
						break;

					result[i][y] = true;
				}
			}
			if (this.directionList[1]) {
				for (int i = y; i < m; i++) {
					if (office[x][i] == WALL)
						break;

					result[x][i] = true;
				}
			}
			if (this.directionList[2]) {
				for (int i = x; i < n; i++) {
					if (office[i][y] == WALL)
						break;

					result[i][y] = true;
				}
			}
			if (this.directionList[3]) {
				for (int i = y; i >= 0; i--) {
					if (office[x][i] == WALL)
						break;

					result[x][i] = true;
				}
			}

			return result;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		office = new int[n][m];
		ArrayList<CCTV> cctvList = new ArrayList<CCTV>();
		boolean[][] isInspected = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				int input = sc.nextInt();
				if (input == WALL) {
					isInspected[i][j] = true;
				}
				office[i][j] = input;
				if (input > 0 && input != WALL) {
					cctvList.add(new CCTV(i, j, input));
				}
			}
		}

		solution(cctvList, isInspected, 0);
		System.out.println(min);
	}

	public static void solution(ArrayList<CCTV> cctvList, boolean[][] isInspected, int index) {

		if (index == cctvList.size()) {

			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!isInspected[i][j]) {
						sum++;
					}

				}
			}

			if (sum < min) {
				min = sum;
			}
			return;
		}

		CCTV cctv = cctvList.get(index);

		for (int i = 0; i < 4; i++) {

			boolean[][] temp = cctv.inspection(isInspected);
			solution(cctvList, temp, index + 1);

			cctv.rotate();
		}

	}

}
