#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
#include <memory.h>

using namespace std;

int dy[] = { 0,0,1,-1 };
int dx[] = { 1,-1,0,0 };

int n, m;

int** visit;
int** blanks;

vector<int> psize;

//m이 y이고 n이 x이다.
//왼쪽 아래 점이 직사각형의 기준이다.

void bfs(int x, int y, int part) {
	queue<pair<int, int>> q;
	q.push(make_pair(y, x));
	visit[y][x] = part;
	psize[part-1]++;
	while (!q.empty()) {
		y = q.front().first;
		x = q.front().second;
		q.pop();
		for (int k = 0; k<4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (blanks[ny][nx] == 0 && visit[ny][nx] == 0) {
					q.push(make_pair(ny, nx));
					visit[ny][nx] = part;
					psize[part-1]++;
				}
			}
		}
	}
}

int main() {
	int k;

	scanf("%d %d %d", &m, &n, &k);

	blanks = new int*[m];
	for (int i = 0; i < m; ++i) {
		blanks[i] = new int[n];
		memset(blanks[i], 0, sizeof(int)*n);
	}

	visit = new int*[m];
	for (int i = 0; i < m; ++i) {
		visit[i] = new int[n];
		memset(visit[i], 0, sizeof(int)*n);
	}

	while (k > 0) {

		int xb, yb, xe, ye;

		scanf("%d %d %d %d", &xb, &yb, &xe, &ye);

		for (int i = yb; i < ye; i++) {
			for (int j = xb; j < xe; j++) {
				blanks[i][j] = 1;
			}
		}
		k--;
	}

	int part = 0;

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (blanks[i][j] == 0 && visit[i][j] == 0) {
				psize.push_back(0);
				bfs(j, i, ++part);
			}
		}
	}

	printf("%d \n", part);

	sort(psize.begin(), psize.end());

	for (auto x : psize) {
		printf("%d ", x);
	}
}