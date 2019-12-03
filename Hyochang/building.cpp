#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <queue>

using namespace std;

struct position {

	int z, y, x;
	int cnt;

}typedef p;

queue<p> q;

char visited[30][30][31];

int main()
{
	int l, r, c;
	char map[30][30][31];
	int dx[] = { 1,-1,0,0,0,0 };
	int dy[] = { 0,0,1,-1,0,0 };
	int dz[] = { 0,0,0,0,1,-1 };

	while (1) {
		scanf("%d %d %d", &l, &r, &c);
		if (l == 0 && r == 0 && c == 0) break;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				scanf("%s", map[i][j]);

			}
		}

		int flag = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (map[i][j][k] == 'S') {
						p start = { i,j,k,0 };
						q.push(start);
						while (!q.empty() && flag == 0) {
							p now = q.front();
							q.pop();
							for (int m = 0; m < 6; m++) {
								p next = { now.z + dz[m],now.y + dy[m],now.x + dx[m],now.cnt + 1 };
								if (0 <= next.z && next.z < l && 0 <= next.y && next.y < r && 0 <= next.x && next.x < c) {
									if (visited[next.z][next.y][next.x] == 0 && map[next.z][next.y][next.x] != '#') {
										visited[next.z][next.y][next.x] = 1;
										if (map[next.z][next.y][next.x] == 'E') {
											printf("Escaped in %d minute(s).\n", next.cnt);
											flag = 1;
										}
										q.push(next);
									}
								}
							}
						}
					}
				}
			}
		}
		while (!q.empty()) q.pop();
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++)
					visited[i][j][k] = 0;
			}
		}
		if (flag == 0) printf("Trapped!\n");
	}
	return 0;
}