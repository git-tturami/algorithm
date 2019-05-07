#pragma warning (disable:4996)
#include <stdio.h>
using namespace std;

int n, m, d, max = -1;
int nEnemy = 0;

int archers[3];
int willKill_r[3];
int willKill_c[3];
int map[16][16];

int simulate() {
	int buf[16][16];
	for (int r = 0; r < 16; r++) {
		for (int c = 0; c < 16; c++) {
			buf[r][c] = map[r][c];
		}
	}

	int turn = 0;
	int died = 0;
	int killed = 0;
	while (killed + died < nEnemy) {
		willKill_r[0] = willKill_r[1] = willKill_r[2] = -1;
		willKill_c[0] = willKill_c[1] = willKill_c[2] = -1;
		for (int i = 0; i<3; i++) {
			int minDist = 99;
			for (int r = 0;r < n;r++) {
				for (int c = 0;c < m;c++) {
					if (buf[r][c] == 1) {

						int dr = n - r;
						int dc = archers[i] - c;
						if (dc < 0) dc = -dc;

						int dist = dr + dc;
						if (dist <= d && dist <= minDist) {
							if (dist == minDist) {
								if (c < willKill_c[i]) {
									willKill_r[i] = r;
									willKill_c[i] = c;
									minDist = dist;
								}
							}
							else {
								willKill_r[i] = r;
								willKill_c[i] = c;
								minDist = dist;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (willKill_r[i] != -1 && buf[willKill_r[i]][willKill_c[i]] == 1) {
				killed++;
				buf[willKill_r[i]][willKill_c[i]] = 0;
			}
		}


		for (int r = n - 1;r >= 0;r--) {
			for (int c = 0; c < m; c++) {
				if (buf[r][c] == 1) {
					buf[r + 1][c] = 1;
					buf[r][c] = 0;
					if (r + 1 == n) {
						buf[r + 1][c] = 0;
						died++;
					}
				}
			}
		}
	}
	return killed;
}

void dfs(int idx, int cnt) {
	if (idx >= m) {
		return;
	}

	archers[cnt] = idx;
	if (cnt == 2) {
		int ans = simulate();
		if (max <= ans) {
			max = ans;
		}
		return;
	}
	for (int i = idx + 1; i<m; i++) {
		dfs(i, cnt + 1);
	}
}

int main() {
	scanf("%d%d%d", &n, &m, &d);
	int t;
	for (int i = 0; i<n; i++) {
		for (int j = 0; j<m; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 1) {
				nEnemy++;
			}
		}
	}

	for (int i = 0; i<m; i++) {
		dfs(i, 0);
	}
	printf("%d", max);
}
