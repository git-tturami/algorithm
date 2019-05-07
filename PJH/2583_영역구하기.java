import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static int[][] result = new int[101][101];
	static int M = 0;
	static int N = 0;
	static int K = 0;
	static int[] X1 = new int[100];
	static int[] Y1 = new int[100];
	static int[] X2 = new int[100];
	static int[] Y2 = new int[100];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int max = 0;
		int flag = 0;
		Scanner input = new Scanner(System.in);
		M = input.nextInt();
		N = input.nextInt();
		K = input.nextInt();

		for (int i = 0; i < K; i++) {
			X1[i] = input.nextInt();
			Y1[i] = input.nextInt();
			X2[i] = input.nextInt();
			Y2[i] = input.nextInt();
		}
		input.close();

		int[][] arr = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = 0;
			}
		}

		for (int i = 0; i < K; i++) {
			for (int j = X1[i]; j < X2[i]; j++) {
				for (int k = Y1[i]; k < Y2[i]; k++) {
					arr[k][j] = 101;
				}
			}
		}

		for (int i = 1; i <= 9999; i++) {
			flag = 0;
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if (arr[j][k] == 0) {
						flag++;
					}
				}
			}

			if (flag == 0) {
				break;
			}
			int brk = 0;
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if (arr[j][k] == 0) {
						arr[j][k] = i;
						max++;
						brk = 1;
						break;
					}
				}
				if (brk == 1) {
					break;
				}
			}

			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if (arr[j][k] == i) {
						if (j != M - 1) {
							if (arr[j + 1][k] == 0) {
								arr[j + 1][k] = i;
							}
						}
						if (j != 0) {
							if (arr[j - 1][k] == 0) {
								arr[j - 1][k] = i;
							}
						}
						if (k != N - 1) {
							if (arr[j][k + 1] == 0) {
								arr[j][k + 1] = i;
							}
						}
						if (k != 0) {
							if (arr[j][k - 1] == 0) {
								arr[j][k - 1] = i;
							}
						}
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					if (arr[j][k] == i) {
						if (j != M - 1) {
							if (arr[j + 1][k] == 0) {
								arr[j + 1][k] = i;
							}
						}
						if (j != 0) {
							if (arr[j - 1][k] == 0) {
								arr[j - 1][k] = i;
							}
						}
						if (k != N - 1) {
							if (arr[j][k + 1] == 0) {
								arr[j][k + 1] = i;
							}
						}
						if (k != 0) {
							if (arr[j][k - 1] == 0) {
								arr[j][k - 1] = i;
							}
						}
					}
				}
			}

			for (int k = N-1; k > 0; k--) {
				for (int j = M-1; j > 0; j--) {
					if (arr[j][k] == i) {
						if (j != M - 1) {
							if (arr[j + 1][k] == 0) {
								arr[j + 1][k] = i;
							}
						}
						if (j != 0) {
							if (arr[j - 1][k] == 0) {
								arr[j - 1][k] = i;
							}
						}
						if (k != N - 1) {
							if (arr[j][k + 1] == 0) {
								arr[j][k + 1] = i;
							}
						}
						if (k != 0) {
							if (arr[j][k - 1] == 0) {
								arr[j][k - 1] = i;
							}
						}
					}
				}
			}

			
			for (int j = M - 1; j >= 0; j--) {
				for (int k = N - 1; k >= 0; k--) {
					if (arr[j][k] == i) {
						if (j != M - 1) {
							if (arr[j + 1][k] == 0) {
								arr[j + 1][k] = i;
							}
						}
						if (j != 0) {
							if (arr[j - 1][k] == 0) {
								arr[j - 1][k] = i;
							}
						}
						if (k != N - 1) {
							if (arr[j][k + 1] == 0) {
								arr[j][k + 1] = i;
							}
						}
						if (k != 0) {
							if (arr[j][k - 1] == 0) {
								arr[j][k - 1] = i;
							}
						}
					}
				}
			}

		}
	
		int[] result = new int[max];
		for (int i = 0; i < max; i++) {
			result[i] = 0;
		}

		for (int i = 0; i < max; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if (arr[j][k] == i + 1) {
						result[i]++;
					}
				}
			}
		}

		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		for (int i = 0; i < max; i++) {
			arrlist.add(result[i]);
		}
		Collections.sort(arrlist);

		System.out.println(max);
		for (int i = 0; i < max; i++) {
			System.out.print(arrlist.get(i));
			System.out.print(" ");

		}

	}

}
