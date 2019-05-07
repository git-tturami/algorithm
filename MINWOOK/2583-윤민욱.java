
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static final int BLOCKED = -1;
	private static final int UNVISITED = 0;
	private static final int VISITED = 1;

	public static int m, n, k;
	public static int[][] coordinate;
	public static boolean[][] isVisited;
	public static ArrayList<Integer> areaList;
	public static int area = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		m = sc.nextInt();
		n = sc.nextInt();
		k = sc.nextInt();
		coordinate = new int[m][n];
		isVisited = new boolean[m][n];
		areaList = new ArrayList<Integer>();

		while (k > 0) {

			boolean[][] isVisited = new boolean[m][n];
			int i = sc.nextInt();
			int j = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			dfs(j, i, y, x, isVisited);
			k--;
		}

		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (coordinate[i][j] != BLOCKED && !isVisited[i][j]) {

					solution(i, j);
					areaList.add(area);
					area = 0;
					count++;
				}
			}
		}

		Collections.sort(areaList);
		System.out.println(count);
		for(int i : areaList)
			System.out.print(i + " ");
		
	}

	public static void dfs(int i, int j, int x, int y, boolean[][] isVisited) {

		coordinate[i][j] = BLOCKED;
		isVisited[i][j] = true;

		if (i + 1 < x && !isVisited[i + 1][j]) {

			dfs(i + 1, j, x, y, isVisited);
		}
		if (j + 1 < y && !isVisited[i][j + 1]) {

			dfs(i, j + 1, x, y, isVisited);
		}
	}

	public static void solution(int x, int y) {

		area++;
		isVisited[x][y] = true;
		coordinate[x][y] = VISITED;

		if (x + 1 < m && coordinate[x + 1][y] != BLOCKED && !isVisited[x + 1][y]) {

			solution(x + 1, y);
		}
		if (x - 1 >= 0 && coordinate[x - 1][y] != BLOCKED && !isVisited[x - 1][y]) {

			solution(x - 1, y);
		}
		if (y + 1 < n && coordinate[x][y + 1] != BLOCKED && !isVisited[x][y + 1]) {

			solution(x, y + 1);
		}
		if (y - 1 >= 0 && coordinate[x][y - 1] != BLOCKED && !isVisited[x][y - 1]) {

			solution(x, y - 1);
		}
	}
}
